package com.lostfound.util;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHelper {

    
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = md.digest(password.getBytes());

           
            String hashText = Base64.getEncoder().encodeToString(hashBytes);
            return hashText;

        } catch (Exception e) {
            System.out.println("Hash error: " + e.getMessage());
            return null;
        }
    }
}