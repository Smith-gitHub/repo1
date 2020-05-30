package com.achp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtils {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encoder(String password){return passwordEncoder.encode(password);}

    public static void main(String[] args) {
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}
