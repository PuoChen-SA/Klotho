package com.brokendust.klotho.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPUtils {

    public static String compress(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (gzip != null) {
                try {
                    gzip.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return BaseCoder.byteToBase64(out.toByteArray());
    }

    public static String uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = BaseCoder.base64ToByte(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                }
                catch (IOException iOException) {

                }
            }
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException iOException) {

                }
            }
            try {
                out.close();
            }
            catch (IOException iOException) {

            }
        }
        return decompressed;
    }

    private static class BASE64Decoder {

        private BASE64Decoder() {

        }

    }

}
