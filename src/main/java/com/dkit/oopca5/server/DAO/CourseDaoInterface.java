package com.dkit.oopca5.server.DAO;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

import com.dkit.oopca5.core.DTO.Course;
import com.dkit.oopca5.server.Exceptions.DaoException;
import java.util.List;

public interface CourseDaoInterface
{
    List<Course> findAllCourses() throws DaoException;

    Course findCourse(String courseid) throws DaoException;
}
