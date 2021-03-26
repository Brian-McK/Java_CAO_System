package com.dkit.oopca5.server;

import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.core.DTO.StudentCourses;
import com.dkit.oopca5.server.DAO.*;
import com.dkit.oopca5.core.DTO.Course;
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
        StudentCoursesDaoInterface IStudentCoursesDAO = new MySQLStudentCoursesDAO();

        try
        {
            System.out.println("\nCall findAllCourses()");
            List<Course> courses = ICourseDao.findAllCourses();

            if( courses.isEmpty() )
                System.out.println("There are no Courses");
            else
                displayCourses(courses);

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
            else
                displayStudents(students);
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }

        try
        {
            System.out.println("\nCall findAllStudentCourses()");
            List<StudentCourses> studentCourses = IStudentCoursesDAO.findAllStudentCourses();

            if( studentCourses.isEmpty() )
                System.out.println("There are no StudentCourses");
            else

            // TODO: 25/03/2021 - ELSE, DISPLAY(STUDENTCOURSES) METHOD

            for( StudentCourses studentCourseChoice : studentCourses )
            {
                System.out.println("StudentCourses: " + studentCourseChoice.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }

        try
        {
            System.out.println("\nCall findCourse()");
            Course course = ICourseDao.findCourse("DK121");

            if( course == null)
                System.out.println("There is no course");
            else
                System.out.println("Course: " + course.toString());

            // TODO: 25/03/2021 - ELSE, DISPLAY(COURSE) METHOD

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    private static void displayCourses(List<Course> courses)
    {
        System.out.printf("%-10s%8s %-50s%-30s\n", "CourseID","Level","Title","Institute");
        for (Course course: courses)
        {
            System.out.printf("%-10s%8s %-50s%-30s\n", course.getCourseid(),course.getLevel(),course.getTitle(),
                    course.getInstitution());
        }
    }

    private static void displayStudents(List<Student> students)
    {
        System.out.printf("%-20s%10s %-10s\n", "CAO Number","DOB","Password");
        for (Student student: students)
        {
            System.out.printf("%-20s%10s %-10s\n", student.getCaoNumber(),student.getDob(),student.getPassword());
        }
    }
}
