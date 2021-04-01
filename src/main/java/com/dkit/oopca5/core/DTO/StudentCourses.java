package com.dkit.oopca5.core.DTO;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

public class StudentCourses
{
    private int caoNumber;
    private String courseid;
    private int choiceNumber;

    public StudentCourses(int caoNumber, String courseid, int choiceNumber)
    {
        this.caoNumber = caoNumber;
        this.courseid = courseid;
        this.choiceNumber = choiceNumber;
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

    public int getChoiceNumber()
    {
        return choiceNumber;
    }

    public void setChoiceNumber(int choiceNumber)
    {
        this.choiceNumber = choiceNumber;
    }

    @Override
    public String toString()
    {
        return "StudentCourses{" +
                "caoNumber=" + caoNumber +
                ", courseid='" + courseid + '\'' +
                ", choiceNumber=" + choiceNumber +
                '}';
    }
}
