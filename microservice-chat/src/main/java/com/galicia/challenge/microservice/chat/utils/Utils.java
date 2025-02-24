package com.galicia.challenge.microservice.chat.utils;

import java.util.Random;

public class Utils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    private Utils() {
        throw new IllegalArgumentException("Util class");
    }

    public static String getTraceId () {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
            if (i % 3 == 2 && i != 8) {
                sb.append("-");
            }
        }
        return sb.toString();
    }
}
