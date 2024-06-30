package com.example.patientreport;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReportRepository {
    private Map<String, Report> reportDatabase = new HashMap<>();

    public void storeReport(String patientID, Date date, String reportText, String imageLink, String accessCode) {
        Report report = new Report(patientID, date, reportText, imageLink, accessCode);
        reportDatabase.put(accessCode, report);
    }

    public Report getReport(String accessCode) {
        return reportDatabase.get(accessCode);
    }

    public Report getReportByAccessCode(String accessCode) {
        return getReport(accessCode); // Alias for backward compatibility
    }

    public String generateAccessCode() {
        return "Affe" + UUID.randomUUID().toString();
    }

    public ReportRepository() {
        storeReport("P001", new Date(), "Clinical Report: \nPatient ID: P001\nDate: " + new Date() + "\nReport: The patient presents with mild symptoms of asthma. Recommend a follow-up visit in 2 months.\nDoctor: Dr. Smith\nContact: dr.smith@example.com", "https://static.vecteezy.com/system/resources/thumbnails/001/849/553/small_2x/modern-gold-background-free-vector.jpg", "Affe1234-5678-90AB-CDEF-1234567890AB");
        storeReport("P002", new Date(), "Clinical Report: \nPatient ID: P002\nDate: " + new Date() + "\nReport: The patient is experiencing chronic back pain. Recommend physical therapy sessions for the next 6 weeks.\nDoctor: Dr. Johnson\nContact: dr.johnson@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe2345-6789-01BC-DEF1-234567890ABC");
        storeReport("P003", new Date(), "Clinical Report: \nPatient ID: P003\nDate: " + new Date() + "\nReport: The patient has a history of hypertension. Recommend a diet plan and regular blood pressure monitoring.\nDoctor: Dr. Lee\nContact: dr.lee@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe3456-7890-12CD-EF34-567890ABCDEF");
        storeReport("P004", new Date(), "Clinical Report: \nPatient ID: P004\nDate: " + new Date() + "\nReport: The patient shows signs of early-stage diabetes. Recommend lifestyle changes and glucose monitoring.\nDoctor: Dr. Williams\nContact: dr.williams@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe4567-8901-23DE-FG45-678901234567");
        storeReport("P005", new Date(), "Clinical Report: \nPatient ID: P005\nDate: " + new Date() + "\nReport: The patient is recovering well from recent surgery. Recommend follow-up check-up in one month.\nDoctor: Dr. Brown\nContact: dr.brown@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe5678-9012-34EF-GH56-789012345678");
        storeReport("P006", new Date(), "Clinical Report: \nPatient ID: P006\nDate: " + new Date() + "\nReport: The patient has been diagnosed with a respiratory infection. Recommend a course of antibiotics and rest.\nDoctor: Dr. Davis\nContact: dr.davis@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe6789-0123-45FG-HI67-890123456789");
        storeReport("P007", new Date(), "Clinical Report: \nPatient ID: P007\nDate: " + new Date() + "\nReport: The patient needs a vaccination booster. Schedule an appointment for the vaccination.\nDoctor: Dr. Miller\nContact: dr.miller@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe7890-1234-56GH-IJ78-901234567890");
        storeReport("P008", new Date(), "Clinical Report: \nPatient ID: P008\nDate: " + new Date() + "\nReport: The patient is suffering from migraines. Recommend consultation with a neurologist.\nDoctor: Dr. Wilson\nContact: dr.wilson@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe8901-2345-67IJ-KL89-012345678901");
        storeReport("P009", new Date(), "Clinical Report: \nPatient ID: P009\nDate: " + new Date() + "\nReport: The patient is showing symptoms of a food allergy. Recommend an allergy test and dietary adjustments.\nDoctor: Dr. Moore\nContact: dr.moore@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe9012-3456-78JK-LM90-123456789012");
        storeReport("P010", new Date(), "Clinical Report: \nPatient ID: P010\nDate: " + new Date() + "\nReport: The patient has been advised to follow a cardiac rehabilitation program. Schedule regular check-ups.\nDoctor: Dr. Taylor\nContact: dr.taylor@example.com", "https://png.pngtree.com/thumb_back/fh260/background/20230408/pngtree-rainbow-curves-abstract-colorful-background-image_2164067.jpg", "Affe0123-4567-89KL-MN01-234567890123");
    }
}
