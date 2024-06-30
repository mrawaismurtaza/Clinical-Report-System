package com.example.patientreport;

import java.util.Date;

public class Report {
    private String patientID;
    private Date date;
    private String reportText;
    private String imageLink;
    private String accessCode;

    public Report(String patientID, Date date, String reportText, String imageLink, String accessCode) {
        this.patientID = patientID;
        this.date = date;
        this.reportText = reportText;
        this.imageLink = imageLink;
        this.accessCode = accessCode;
    }

    public String getPatientID() {
        return patientID;
    }

    public Date getDate() {
        return date;
    }

    public String getReportText() {
        return reportText;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getAccessCode() {
        return accessCode;
    }
}
