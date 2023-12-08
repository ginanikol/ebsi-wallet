package gr.ihu.dw.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.TimeZone;

public class Utils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static String convertKeyToHex(Key key) throws Exception {
        byte[] encodedKey = key.getEncoded();
        return bytesToHex(encodedKey);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public static LocalDateTime formatDate(LocalDateTime dateTime) {
        return LocalDateTime.parse(dateTime.format(formatter));
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Long dateToConvert) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateToConvert),
                TimeZone.getDefault().toZoneId());
    }

    public static String generateBase64QRCode(String qrCodeData, int width, int height) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(
                    new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, width, width),
                    "PNG",
                    byteArray
            );
            byte[] bytes = byteArray.toByteArray();

            Base64.Encoder encoder = Base64.getEncoder();
            byte[] base64Bytes = encoder.encode(bytes);

            return new String(base64Bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            // logger.info(String.format("Could not generate base64 QRcode: %s", e.getMessage()));
            return null;
        }
    }

}
