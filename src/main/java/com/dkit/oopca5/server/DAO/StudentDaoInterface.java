package com.dkit.oopca5.server.DAO;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.Exceptions.DaoException;
import java.util.List;

public interface StudentDaoInterface
{
    List<Student> findAllStudents() throws DaoException;

    // register and login

    boolean registerStudent(Student student) throws DaoException;
}
