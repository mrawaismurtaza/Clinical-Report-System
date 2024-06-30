package com.example.patientreport;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class QRCodeScanner {

    public String BananeScanQRCode(String filePath) {
        try {
            File qrCodeFile = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(qrCodeFile);
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            HybridBinarizer binarizer = new HybridBinarizer(source);
            QRCodeReader qrCodeReader = new QRCodeReader();
            Result result = qrCodeReader.decode(new BinaryBitmap(binarizer));
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
