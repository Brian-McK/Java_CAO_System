package com.dkit.oopca5.client;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

/* This class should contain static methods to verify input in the application
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker
{
    public static boolean isValidCAONumber(String caoNumber, String caoNumberRegex)
    {
        Pattern p = Pattern.compile(caoNumberRegex);
        Matcher m = p.matcher(caoNumber);

        return m.matches();
    }

    public static boolean isValidDOB(String dobStr, String dobRegex)
    {
        Pattern p = Pattern.compile(dobRegex);
        Matcher m = p.matcher(dobStr);

        return m.matches();
    }

    public static boolean isValidPassword(String password, String passwordRegex)
    {
        Pattern p = Pattern.compile(passwordRegex);
        Matcher m = p.matcher(password);

        return m.matches();
    }

    public static boolean isValidCourseId(String courseId, String courseIdRegex)
    {
        Pattern p = Pattern.compile(courseIdRegex);
        Matcher m = p.matcher(courseId);

        return m.matches();
    }
}
