package com.example.patientreport;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRCodeGenerator {

    private ReportRepository repository;

    public QRCodeGenerator(ReportRepository repository) {
        this.repository = repository;
    }

    public BufferedImage BananeGenerateQRCode(String accessCode) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String githubUrl = "https://secretdol.github.io/patient-report-system/";

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(githubUrl, BarcodeFormat.QR_CODE, 400, 400);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] BananeGenerateQRCodeAsBytes(String accessCode) {
        BufferedImage qrImage = BananeGenerateQRCode(accessCode);
        if (qrImage == null) {
            return null;
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(qrImage, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
