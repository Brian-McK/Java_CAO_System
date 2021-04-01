package com.dkit.oopca5.core.test;

import com.dkit.oopca5.core.CAOService;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void TestDisplayCourseClientRequest()
    {
        System.out.println("Testing Display Course Client Request");
        String courseID = "DK821";
        String expectedResult = "DISPLAY COURSE%%DK821";
        String actualResult = CAOService.DISPLAY_COURSE_COMMAND + CAOService.BREAKING_CHARACTER + courseID;
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void TestDisplayCoursesClientRequest()
    {
        System.out.println("Testing Display All Courses Client Request");
        String expectedResult = "DISPLAY_ALL";
        String actualResult = CAOService.DISPLAY_ALL_COURSES_COMMAND;
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void TestDisplayCurrentCoursesClientRequest()
    {
        System.out.println("Testing Display Current Courses Client Request");
        int caoNumber = 12345678;
        String expectedResult = "DISPLAY CURRENT%%12345678";
        String actualResult = CAOService.DISPLAY_CURRENT_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber;
        assertEquals(expectedResult,actualResult);
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
