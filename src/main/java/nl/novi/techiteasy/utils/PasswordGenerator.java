package nl.novi.techiteasy.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static String generateEncryptedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(password);
    }

}
