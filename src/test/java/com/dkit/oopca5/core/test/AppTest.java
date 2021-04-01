package com.dkit.oopca5.core.test;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

import com.dkit.oopca5.client.RegexChecker;
import com.dkit.oopca5.core.CAOService;
import org.junit.Test;

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
        assertTrue(RegexChecker.isValidCAONumber(caoNumber, CAOService.CAO_NUMBER_REGEX));
    }

    @Test
    public void TestRegexDOB()
    {
        System.out.println("Testing Regex DOB");
        String dob = "1990-12-05";
        assertTrue(RegexChecker.isValidDOB(dob, CAOService.DATE_OF_BIRTH_REGEX));
    }

    @Test
    public void TestRegexPassword()
    {
        System.out.println("Testing Regex Password");
        String password = "Brian!?123#";
        assertTrue(RegexChecker.isValidPassword(password, CAOService.PASSWORD_REGEX));
    }

    @Test
    public void TestRegexCourseID()
    {
        System.out.println("Testing Regex Course ID");
        String courseID = "DK821";
        assertTrue(RegexChecker.isValidCourseId(courseID, CAOService.COURSE_ID_REGEX));
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
}