package com.example.docpro.features.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordVerifier {

  public static boolean verifyPassword(String password, String hashedPassword) {

    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(password.getBytes("UTF-8"));
      String encodedHash = Base64.getEncoder().encodeToString(hash);
      return encodedHash.equals(hashedPassword);
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
      throw new RuntimeException(ex);
    }
  }
}