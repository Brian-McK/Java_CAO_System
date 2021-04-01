package com.dkit.oopca5.server.DAO;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

import com.dkit.oopca5.core.DTO.StudentCourses;
import com.dkit.oopca5.server.Exceptions.DaoException;

import java.util.List;

public interface StudentCoursesDaoInterface
{
    List<StudentCourses> findAllStudentCourses(int caoNumber) throws DaoException;

    boolean updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException;
}
