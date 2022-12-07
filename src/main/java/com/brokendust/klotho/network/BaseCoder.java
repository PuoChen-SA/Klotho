package com.brokendust.klotho.network;

import java.util.Base64;

public class BaseCoder {
    public static String byteToBase64(byte[] message) {
        byte[] encodedBytes = Base64.getEncoder().encode(message);
        return new String(encodedBytes);
    }

    public static byte[] base64ToByte(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public static String stringToBase64(String message) {
        byte[] encodedBytes = Base64.getEncoder().encode(message.getBytes());
        return new String(encodedBytes);
    }

    public static String base64toString(String base64) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64.getBytes());
        return new String(decodedBytes);
    }
}
