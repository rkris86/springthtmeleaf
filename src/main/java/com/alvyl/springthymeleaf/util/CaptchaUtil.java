package com.alvyl.springthymeleaf.util;

public class CaptchaUtil {

    public static String generateCaptchaTextMethod(int captchaLength)   {

        String chapChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer captchaStrBuffer = new StringBuffer();
        java.util.Random rnd = new java.util.Random();
        while (captchaStrBuffer.length() < captchaLength)
        {
            int index = (int) (rnd.nextFloat() * chapChar.length());
            captchaStrBuffer.append(chapChar.substring(index, index+1));
        }

        return captchaStrBuffer.toString();

    }
}
