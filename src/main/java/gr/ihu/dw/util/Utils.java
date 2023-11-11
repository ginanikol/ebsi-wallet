package gr.ihu.dw.util;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

}
