package com.dkit.oopca5.core.DTO;

import java.util.Date;

public class StudentCourses
{
    private int caoNumber;
    private String courseid;

    public StudentCourses(int caoNumber, String courseid)
    {
        this.caoNumber = caoNumber;
        this.courseid = courseid;
    }

    public StudentCourses()
    {
    }

    public int getCaoNumber()
    {
        return caoNumber;
    }

    public void setCaoNumber(int caoNumber)
    {
        this.caoNumber = caoNumber;
    }

    public String getCourseid()
    {
        return courseid;
    }

    public void setCourseid(String courseid)
    {
        this.courseid = courseid;
    }

    @Override
    public String toString()
    {
        return "StudentCourses{" +
                "caoNumber=" + caoNumber +
                ", courseid='" + courseid + '\'' +
                '}';
    }
}
