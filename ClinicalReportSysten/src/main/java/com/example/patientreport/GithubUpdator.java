package com.example.patientreport;

import org.json.JSONObject;

import com.example.patientreport.Report;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Collectors;

public class GithubUpdator {

    private static final String REPO_API_URL = "your github repository api link";
    private static final String PERSONAL_ACCESS_TOKEN = System.getenv("GITHUB_TOKEN");  // Updated to use an environment variable

    public void updateGitHubPage(Report report) {
        try {
            // Load the HTML template with placeholders
            InputStream templateStream = getClass().getClassLoader().getResourceAsStream("html file location");
            if (templateStream == null) {
                throw new IllegalStateException("index.html not found in resources");
            }
            String templateContent = new BufferedReader(new InputStreamReader(templateStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            // Update the content of index.html with the new report data
            String updatedContent = templateContent
                    .replace("[PATIENT_ID]", report.getPatientID())
                    .replace("[DATE]", report.getDate().toString())
                    .replace("[REPORT_TEXT]", report.getReportText())
                    .replace("[IMAGE_LINK]", report.getImageLink())
                    .replace("[ACCESS_CODE]", report.getAccessCode())
                    .replace("[QR_CODE_URL]", "https://username.github.io/repositoryname/" + report.getAccessCode() + ".png");

            // Fetch the current index.html file from GitHub to get the SHA
            URL url = new URL(REPO_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "token " + PERSONAL_ACCESS_TOKEN);
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            String jsonResponse = response.toString();
            JSONObject json = new JSONObject(jsonResponse);
            String sha = json.getString("sha");

            // Send a PUT request to GitHub API to update index.html
            URL urlUpdate = new URL(REPO_API_URL);
            HttpURLConnection connUpdate = (HttpURLConnection) urlUpdate.openConnection();
            connUpdate.setRequestMethod("PUT");
            connUpdate.setRequestProperty("Authorization", "token " + PERSONAL_ACCESS_TOKEN);
            connUpdate.setRequestProperty("Accept", "application/vnd.github.v3+json");
            connUpdate.setRequestProperty("Content-Type", "application/json");
            connUpdate.setDoOutput(true);

            JSONObject payloadJson = new JSONObject();
            payloadJson.put("message", "Update index.html with patient report");
            payloadJson.put("content", Base64.getEncoder().encodeToString(updatedContent.getBytes(StandardCharsets.UTF_8)));
            payloadJson.put("sha", sha);

            try (OutputStream os = connUpdate.getOutputStream()) {
                os.write(payloadJson.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            int responseCode = connUpdate.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("GitHub Pages updated successfully.");
            } else {
                System.out.println("Failed to update GitHub Pages. Response Code: " + responseCode);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connUpdate.getErrorStream()))) {
                    String line;
                    StringBuilder errorResponse = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.out.println("Error Response: " + errorResponse.toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
