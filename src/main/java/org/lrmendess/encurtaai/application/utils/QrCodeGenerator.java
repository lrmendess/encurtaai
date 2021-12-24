package org.lrmendess.encurtaai.application.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.util.Base64Utils;

public class QrCodeGenerator {

    private final static String IMAGE_FORMAT = "PNG";

    public static byte[] generateQrCodeByteArray(String uri) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(uri, BarcodeFormat.QR_CODE, 500, 500);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, IMAGE_FORMAT, outputStream);
        
        return outputStream.toByteArray();
    }

    public static String generateQrCodeBase64(String uri) throws WriterException, IOException {
        byte[] qrCodeByteArray = generateQrCodeByteArray(uri);
        return Base64Utils.encodeToString(qrCodeByteArray);
    }
    
    public static String generateQrCodeBase64Png(String uri) {
        String qrCodeBase64;

        try {
            qrCodeBase64 = generateQrCodeBase64(uri);
        } catch (Exception ex) {
            qrCodeBase64 = new String();
        }

        return "data:image/png;base64," + qrCodeBase64;
    }

}
