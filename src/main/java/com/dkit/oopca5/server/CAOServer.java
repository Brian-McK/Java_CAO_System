package com.dkit.oopca5.server;

import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.core.DTO.StudentCourses;
import com.dkit.oopca5.server.DAO.*;
import com.dkit.oopca5.core.DTO.Course;
import com.dkit.oopca5.server.Exceptions.DaoException;

import java.util.ArrayList;
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
            System.out.println("\nCall updateCoursesForUser()");
            List<String> courses = new ArrayList<>();
            courses.add("DK121");
            courses.add("DK821");
            courses.add("MYN231");

            if(!IStudentCoursesDAO.updateCoursesForUser(12345678,courses))
            {
                System.out.println("Course choices update didn't work");
            }
            else {
                System.out.println("Course choices updated");
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }

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
            List<StudentCourses> studentCourses = IStudentCoursesDAO.findAllStudentCourses(12345678);

            if( studentCourses.isEmpty() )
                System.out.println("There are no StudentCourses");
            else
                displayStudentCourses(studentCourses);
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
                displayCourse(course);
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

    private static void displayCourse(Course course)
    {
        System.out.printf("%-10s%8s %-50s%-30s\n", "CourseID","Level","Title","Institute");
        System.out.printf("%-10s%8s %-50s%-30s\n", course.getCourseid(),course.getLevel(),course.getTitle(),course.getInstitution());
    }

    private static void displayStudents(List<Student> students)
    {
        System.out.printf("%-20s%10s %-10s\n", "CAO Number","DOB","Password");
        for (Student student: students)
        {
            System.out.printf("%-20s%10s %-10s\n", student.getCaoNumber(),student.getDob(),student.getPassword());
        }
    }

    private static void displayStudentCourses(List<StudentCourses> studentCourses)
    {
        System.out.printf("%-20s%10s %-10s\n", "CAO Number","Course Id","Choice Number");
        for (StudentCourses studentCourseChoice: studentCourses)
        {
            System.out.printf("%-20s%10s %-10s\n", studentCourseChoice.getCaoNumber(),studentCourseChoice.getCourseid(),
                studentCourseChoice.getChoiceNumber());
        }
    }
}
