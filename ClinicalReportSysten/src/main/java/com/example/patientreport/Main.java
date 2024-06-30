package com.example.patientreport;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static String lastQrCodePath = ""; // Variable to keep track of the last QR code path

    public static void main(String[] args) {
        ReportRepository repository = new ReportRepository();
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator(repository);
        QRCodeScanner qrCodeScanner = new QRCodeScanner();
        GithubUpdator githubUpdator = new GithubUpdator();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=======================================");
            System.out.println("       Patient Report System");
            System.out.println("=======================================");
            System.out.println("1. Generate QR Code");
            System.out.println("2. Scan QR Code");
            System.out.println("3. Exit");
            System.out.println("=======================================");
            System.out.print("Choose an option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter access code: ");
                    String accessCode = scanner.nextLine();

                    // Check if the access code exists in the dummy data
                    Report report = repository.getReportByAccessCode(accessCode);
                    if (report != null) {
                        // Access code exists, generate QR Code
                        byte[] qrCodeBytes = qrCodeGenerator.BananeGenerateQRCodeAsBytes(accessCode);
                        if (qrCodeBytes != null) {
                            try {
                                String filePath = "D:/QR-Code/QRCode_" + accessCode + ".png";
                                File qrCodeFile = new File(filePath);
                                try (FileOutputStream fos = new FileOutputStream(qrCodeFile)) {
                                    fos.write(qrCodeBytes);
                                }
                                System.out.println("QR Code generated successfully.");
                                System.out.println("QR Code saved to: " + filePath);
                                System.out.println("QR Code link: https://secretdol.github.io/patient-report-system/qr-code/QRCode_" + accessCode + ".png");

                                // Update lastQrCodePath to the latest QR Code
                                lastQrCodePath = filePath;

                                // Update GitHub page
                                githubUpdator.updateGitHubPage(report);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        // No report found for the given access code
                        System.out.println("No report found for access code: " + accessCode);
                    }
                    break;

                case 2:
                    if (!lastQrCodePath.isEmpty()) {
                        // Open the QR Code image in the default image viewer
                        try {
                            File qrCodeImage = new File(lastQrCodePath);
                            if (qrCodeImage.exists()) {
                                Desktop desktop = Desktop.getDesktop();
                                desktop.open(qrCodeImage);
                                System.out.println("QR Code content: https://secretdol.github.io/patient-report-system/");
                            } else {
                                System.out.println("The specified QR Code image file does not exist.");
                            }
                        } catch (IOException e) {
                            System.out.println("Error opening the QR Code image.");
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("No QR Code has been generated yet.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }
}
