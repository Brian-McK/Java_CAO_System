package com.dkit.oopca5.server.DAO;

import com.dkit.oopca5.core.DTO.StudentCourses;
import com.dkit.oopca5.server.Exceptions.DaoException;

import java.util.List;

public interface StudentCoursesDaoInterface
{
    // TODO: 26/03/2021 - add argument caoNumber for findAllStudentCourses() - list of my choices
    // make an update method - change course choices for a caonumber
    List<StudentCourses> findAllStudentCourses() throws DaoException;
}
