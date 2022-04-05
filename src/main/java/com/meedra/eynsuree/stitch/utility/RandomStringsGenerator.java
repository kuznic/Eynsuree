package com.meedra.eynsuree.stitch.utility;


import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringsGenerator {

    public static String generateRandomStrings(){

        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);

    }


}
