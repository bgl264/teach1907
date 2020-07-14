package com.teach.frame.secret;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    private static MessageDigest digest = null;
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static final String getFileMd5(byte[] byt) {
        if (digest == null) {
            return "";
        } else {
            String md5hash = null;
            int read = 0;
            try {
                digest.update(byt, 0, read);
                byte[] md5sum = digest.digest();
                BigInteger bigInt = new BigInteger(1, md5sum);
                md5hash = bigInt.toString(16);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (md5hash != null) {
                final int length = md5hash.length();
                if (length != 33) {
                    String tmp = "";
                    for (int i = 1; i < (33 - length); i++) {
                        tmp = tmp.concat("0");
                    }
                    md5hash = tmp.concat(md5hash);
                }

                return md5hash.toUpperCase();
            } else {
                return "";
            }
        }
    }

    public static final String getStringMd5(String source) {
        if (digest == null) {
            return null;
        } else {
            digest.update(source.getBytes());
            return byteArrayToHexString(digest.digest());
        }
    }

    private static final String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(hexDigits[(0xF0 & aB) >>> 4]);
            resultSb.append(hexDigits[(0xF & aB)]);
        }
        return resultSb.toString();
    }
}
