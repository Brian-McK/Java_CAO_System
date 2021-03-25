package com.dkit.oopca5.server.DAO;

import com.dkit.oopca5.core.DTO.Course;
import com.dkit.oopca5.server.Exceptions.DaoException;
import java.util.List;

public interface CourseDaoInterface
{
    List<Course> findAllCourses() throws DaoException;

    Course findCourse(String courseid) throws DaoException;
//
//    public boolean updatePassword(String uname, String pword) throws DaoException;
//
//    // BRIAN PRACTICE
//    public List<User> findAllUsersFirstNameBeginningWith(String beginsWith) throws DaoException;
//
//    public int countNumberOfRecordsInTable() throws DaoException;
//
//    public List<User> findAllUsersLastNameContains(String contains) throws DaoException;
}
