package com.dkit.oopca5.server.DAO;

import com.dkit.oopca5.core.DTO.StudentCourses;
import com.dkit.oopca5.server.Exceptions.DaoException;

import java.util.List;

public interface StudentCoursesDaoInterface
{
    // make an update method - change course choices for a caoNumber
    List<StudentCourses> findAllStudentCourses(int caoNumber) throws DaoException;

    // TODO: 26/03/2021 - Make a method that updates a students course choice
}
