package com.dkit.oopca5.server;

import java.util.Date;

public class Student
{
    private int caoNumber;
    private Date dob;
    private String password;

    public Student(int caoNumber, Date dob, String password)
    {
        this.caoNumber = caoNumber;
        this.dob = dob;
        this.password = password;
    }

    public Student()
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

    public Date getDob()
    {
        return dob;
    }

    public void setDob(Date dob)
    {
        this.dob = dob;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "caoNumber=" + caoNumber +
                ", dob=" + dob +
                ", password='" + password + '\'' +
                '}';
    }
}
