package com.epam.spring.core;

import org.joda.time.DateTimeComparator;

import java.util.Random;

public class Util {



    public static Long generateId() {
        Random random = new Random();
        return random.nextLong();
    }
}
