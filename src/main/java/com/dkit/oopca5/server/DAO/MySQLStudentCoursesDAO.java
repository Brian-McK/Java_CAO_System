package com.dkit.oopca5.server.DAO;

import com.dkit.oopca5.core.DTO.StudentCourses;
import com.dkit.oopca5.server.Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLStudentCoursesDAO extends MySqlDAO implements StudentCoursesDaoInterface
{
    public List<StudentCourses> findAllStudentCourses(int studentCaoNumber) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<StudentCourses> studentCourseChoices = new ArrayList<>();

        try
        {
            // Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM student_courses WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, studentCaoNumber);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                String courseid = rs.getString("courseid");
                int choiceNumber = rs.getInt("choiceNumber");
                StudentCourses studentCourses = new StudentCourses(caoNumber, courseid, choiceNumber);
                studentCourseChoices.add(studentCourses);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllStudentCourses() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllStudentCourses() " + e.getMessage());
            }
        }
        return studentCourseChoices;
    }

    public boolean updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        int choiceNumber = 1;

        try
        {
            con = this.getConnection();

            String deleteQuery = "DELETE FROM student_courses WHERE caoNumber = ?";
            ps = con.prepareStatement(deleteQuery);
            ps.setInt(1, caoNumber);
            count = ps.executeUpdate();

            for (int i = 0; i < courses.size(); i++)
            {
                String addQuery = "INSERT INTO student_courses(`caoNumber`,`courseid`,`choiceNumber`) VALUES (?,?,?)";
                ps = con.prepareStatement(addQuery);
                ps.setInt(1, caoNumber);
                ps.setString(2, courses.get(i));
                ps.setInt(3, choiceNumber++);
                count = ps.executeUpdate();
            }

        } catch (SQLException e)
        {
            throw new DaoException("updateCoursesForUser() " + e.getMessage());
        } finally
        {
            try
            {
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("updateCoursesForUser() " + e.getMessage());
            }
        }
        return (count == 1);
    }
}
