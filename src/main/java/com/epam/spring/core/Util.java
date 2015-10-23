package com.epam.spring.core;

import java.util.Random;

public class Util {
    public static Long generateId() {
        Random random = new Random();
        return random.nextLong();
    }
}
