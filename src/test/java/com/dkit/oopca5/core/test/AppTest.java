package com.dkit.oopca5.core.test;

import com.dkit.oopca5.core.CAOService;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void TestRegexCAONumber()
    {
        System.out.println("Testing Regex CAO Number");
        String caoNumber = "12345678";
        assertTrue(isValidCAONumber(caoNumber, CAOService.CAO_NUMBER_REGEX));
    }

    @Test
    public void TestRegexDOB()
    {
        System.out.println("Testing Regex DOB");
        String dob = "1990-12-05";
        assertTrue(isValidDOB(dob, CAOService.DATE_OF_BIRTH_REGEX));
    }

    @Test
    public void TestRegexPassword()
    {
        System.out.println("Testing Regex Password");
        String password = "Brian!?123#";
        assertTrue(isValidPassword(password, CAOService.PASSWORD_REGEX));
    }

    @Test
    public void TestRegexCourseID()
    {
        System.out.println("Testing Regex Course ID");
        String courseID = "DK821";
        assertTrue(isValidCourseId(courseID, CAOService.COURSE_ID_REGEX));
    }

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
