package com.dkit.oopca5.server;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

/*
The CAOClientHandler will run as a thread. It should listen for messages from the Client and respond to them.There should be one CAOClientHandler per Client.
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.DTO.Course;
import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.core.DTO.StudentCourses;
import com.dkit.oopca5.server.DAO.*;
import com.dkit.oopca5.server.Exceptions.DaoException;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CAOClientHandler implements Runnable
{
    BufferedReader socketReader;
    PrintWriter socketWriter;
    Socket socket;
    int clientNumber;

    CourseDaoInterface ICourseDao = new MySQLCourseDAO();
    StudentDaoInterface IStudentDao = new MySQLStudentDAO();
    StudentCoursesDaoInterface IStudentCoursesDAO = new MySQLStudentCoursesDAO();

    public CAOClientHandler(Socket clientSocket, int clientNumber)
    {
        try
        {
            InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
            this.socketReader = new BufferedReader(isReader);

            OutputStream os = clientSocket.getOutputStream();
            this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

            this.clientNumber = clientNumber;  // ID number that we are assigning to this client

            this.socket = clientSocket;  // store socket ref for closing

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        String message = "";
        try
        {
            // this only reads one message, need to read all
            while ((message = socketReader.readLine()) != null)
            {
                System.out.println(message + "......");

                if (message.startsWith(CAOService.REGISTER_COMMAND))
                {
                    // split the message into components
                    // call dao to register the student
                    // check if successful or not

                    String response = CAOService.FAILED_REGISTER;
                    String [] components = message.split(CAOService.BREAKING_CHARACTER);

                    int caoNumber = Integer.parseInt(components[1]);
                    Date dobObj = Date.valueOf(components[2]);
                    String password = components[3];

                    Student student = new Student(caoNumber,dobObj,password);

                    if(!IStudentDao.checkIfRegistered(student))
                    {
                        if(IStudentDao.registerStudent(student))
                        {
                            response = CAOService.SUCCESSFUL_REGISTER;
                        }
                    }
                    socketWriter.println(response);
                }
                else if(message.startsWith(CAOService.LOGIN_COMMAND))
                {
                    String response = CAOService.FAILED_LOGIN;
                    String [] components = message.split(CAOService.BREAKING_CHARACTER);

                    int caoNumber = Integer.parseInt(components[1]);
                    Date dobObj = Date.valueOf(components[2]);
                    String password = components[3];

                    Student student = new Student(caoNumber,dobObj,password);

                    if(IStudentDao.loginStudent(student))
                    {
                        response = CAOService.SUCCESSFUL_LOGIN;
                    }
                    socketWriter.println(response);
                }
                else if(message.startsWith(CAOService.LOGOUT_COMMAND))
                {
                    String response = CAOService.SUCCESSFUL_LOGOUT;
                    socketWriter.println(response);
                }
                else if(message.startsWith(CAOService.DISPLAY_COURSE_COMMAND))
                {
                    String response = CAOService.FAILED_DISPLAY_COURSE;
                    String [] components = message.split(CAOService.BREAKING_CHARACTER);

                    String courseId = components[1];

                    Course course = ICourseDao.findCourse(courseId);

                    if(course != null)
                    {
                        response =
                                course.getCourseid().toUpperCase() + CAOService.BREAKING_CHARACTER +
                                        course.getLevel() + CAOService.BREAKING_CHARACTER + course.getTitle() +
                                        CAOService.BREAKING_CHARACTER + course.getInstitution();
                    }
                    socketWriter.println(response);
                }
                else if(message.startsWith(CAOService.DISPLAY_ALL_COURSES_COMMAND))
                {
                    String response = CAOService.FAILED_DISPLAY_ALL_COURSES;

                    List<Course> allCourses = ICourseDao.findAllCourses();

                    String courseId, level, title, institution;

                    if (allCourses != null)
                    {
                        response = CAOService.SUCCESSFUL_DISPLAY_ALL_COURSES;

                        for (int i = 0; i < allCourses.size(); i++)
                        {
                            courseId = allCourses.get(i).getCourseid();
                            level = String.valueOf(allCourses.get(i).getLevel());
                            title = allCourses.get(i).getTitle();
                            institution = allCourses.get(i).getInstitution();

                            response += CAOService.BREAKING_CHARACTER + courseId +
                                    CAOService.BREAKING_CHARACTER + level + CAOService.BREAKING_CHARACTER + title +
                                    CAOService.BREAKING_CHARACTER + institution;
                        }
                    }
                    socketWriter.println(response);
                }
                else if(message.startsWith(CAOService.DISPLAY_CURRENT_COMMAND))
                {
                    String response = CAOService.FAILED_DISPLAY_CURRENT;
                    String [] components = message.split(CAOService.BREAKING_CHARACTER);

                    String caoNumber = components[1];

                    List<StudentCourses> studentCourses = IStudentCoursesDAO.findAllStudentCourses(Integer.parseInt(caoNumber));
                    String courseId;

                    if(!studentCourses.isEmpty())
                    {
                        response = CAOService.SUCCESSFUL_DISPLAY_CURRENT;

                        for (int i = 0; i < studentCourses.size(); i++)
                        {
                            courseId = studentCourses.get(i).getCourseid();

                            response += CAOService.BREAKING_CHARACTER + courseId;
                        }
                    }
                    socketWriter.println(response);
                }
                else if(message.startsWith(CAOService.UPDATE_CURRENT_COMMAND))
                {
                    String response = CAOService.FAILED_UPDATE_CURRENT;
                    String [] components = message.split(CAOService.BREAKING_CHARACTER);
                    int caoNumber = Integer.parseInt(components[1]);

                    List<String> courses = new ArrayList<>();
                    for (int i = 2; i < components.length; i++)
                    {
                        courses.add(components[i]);
                    }

                    if(IStudentCoursesDAO.updateCoursesForUser(caoNumber,courses)) {
                        response = CAOService.SUCCESSFUL_UPDATE_CURRENT;
                    }
                    socketWriter.println(response);
                }
                else
                {
                    socketWriter.println("I'm sorry I don't understand :(");
                }
            }

            socket.close();

        } catch (IOException | DaoException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Server: (CAOClientHandler): Handler for Client " + clientNumber + " is terminating .....");
    }
}
