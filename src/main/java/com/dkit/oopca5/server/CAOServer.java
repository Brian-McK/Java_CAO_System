package com.dkit.oopca5.server;

import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.DAO.MySQLCourseDAO;
import com.dkit.oopca5.server.DAO.CourseDaoInterface;
import com.dkit.oopca5.core.DTO.Course;
import com.dkit.oopca5.server.DAO.MySQLStudentDAO;
import com.dkit.oopca5.server.DAO.StudentDaoInterface;
import com.dkit.oopca5.server.Exceptions.DaoException;
import java.util.List;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler thread to deal with that connection. The server then returns to listening
 */

public class CAOServer
{
    public static void main(String[] args)
    {
        CourseDaoInterface ICourseDao = new MySQLCourseDAO();
        StudentDaoInterface IStudentDao = new MySQLStudentDAO();

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

        try
        {
            System.out.println("\nCall findAllStudents()");
            List<Student> students = IStudentDao.findAllStudents();

            if( students.isEmpty() )
                System.out.println("There are no Students");

            for( Student student : students )
            {
                System.out.println("Student: " + student.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
