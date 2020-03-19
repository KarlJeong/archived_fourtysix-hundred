package com.karljeong.fourtysix.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

        public static String encryptWithSha256(String msg) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-256");
                md.update(msg.getBytes());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return byteToHexString(md.digest());

        }

        private static String byteToHexString(byte[] data) {
            StringBuilder sb = new StringBuilder();
            for (byte b : data) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }

    }
