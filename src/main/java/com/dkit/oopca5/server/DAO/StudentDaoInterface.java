package com.dkit.oopca5.server.DAO;

import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.Exceptions.DaoException;
import java.util.List;

public interface StudentDaoInterface
{
    List<Student> findAllStudents() throws DaoException;
}
