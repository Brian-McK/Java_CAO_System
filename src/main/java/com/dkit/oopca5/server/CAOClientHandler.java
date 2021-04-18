package com.dkit.oopca5.server;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

/*
The CAOClientHandler will run as a thread. It should listen for messages from the Client and respond to them.There should be one CAOClientHandler per Client.
 */

import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.DAO.*;
import com.dkit.oopca5.server.Exceptions.DaoException;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
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
//                if (message.startsWith("Time"))
//                {
//                    LocalTime time =  LocalTime.now();
//                    socketWriter.println(time);  // sends response to client
//                }
//                else if (message.startsWith("Echo"))
//                {
//                    message = message.substring(5); // strip off the 'Echo ' part
//                    socketWriter.println(message);  // send message to client
//                }
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
