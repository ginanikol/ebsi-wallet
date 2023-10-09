package gr.ihu.dw.util;

import java.security.Key;

public class Utils {

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

}
