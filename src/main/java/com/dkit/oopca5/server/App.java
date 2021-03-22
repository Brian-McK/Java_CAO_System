package com.dkit.oopca5.server;

import com.dkit.oopca5.server.DAO.MySQLCourseDAO;
import com.dkit.oopca5.server.DAO.CourseDaoInterface;
import com.dkit.oopca5.server.DTO.Course;
import com.dkit.oopca5.server.Exceptions.DaoException;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        CourseDaoInterface ICourseDao = new MySQLCourseDAO();

        try
        {
            System.out.println("\nCall findAllUsers()");
            List<Course> courses = ICourseDao.findAllCourses();

            if( courses.isEmpty() )
                System.out.println("There are no Courses");

            for( Course course : courses )
            {
                System.out.println("Course: " + course.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
