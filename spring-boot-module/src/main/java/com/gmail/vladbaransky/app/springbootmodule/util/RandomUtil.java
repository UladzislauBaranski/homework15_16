package com.gmail.vladbaransky.app.springbootmodule.util;

import java.util.Random;

public class RandomUtil {

        private static final Random RANDOM = new Random();

        public static int getNumberInRange(int min, int max) {

            return RANDOM.nextInt((max - min) + 1) + min;

        }

}
