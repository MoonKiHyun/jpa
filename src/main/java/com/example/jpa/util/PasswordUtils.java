package com.example.jpa.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCrypt;

@UtilityClass
public class PasswordUtils {

    public static boolean equalPassword(String password, String encryptedPassword) {

        try {
            return BCrypt.checkpw(password, encryptedPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }

    }
}
