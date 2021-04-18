package com.dkit.oopca5.server.DAO;

// Brian McKenna - SD2B - Github: https://github.com/Brian-McK/BrianMcKenna_CA5/

import com.dkit.oopca5.core.DTO.Student;
import com.dkit.oopca5.server.Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLStudentDAO extends MySqlDAO implements StudentDaoInterface
{
    public List<Student> findAllStudents() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();

        try
        {
            // Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM student";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int caoNumber = rs.getInt("caoNumber");
                Date dob = rs.getDate("dob");
                String password = rs.getString("password");
                Student student = new Student(caoNumber, dob, password);
                students.add(student);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllStudents() " + e.getMessage());
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
                throw new DaoException("findAllStudents() " + e.getMessage());
            }
        }
        return students;     // may be empty
    }

    public boolean registerStudent(Student student) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean registeredSuccessfully = false;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "INSERT INTO STUDENT VALUES (?,?,?)";
            ps = con.prepareStatement(query);

            ps.setInt(1, student.getCaoNumber());
            ps.setString(2, String.valueOf(student.getDob()));
            ps.setString(3, student.getPassword());


            //Using a PreparedStatement to execute SQL - UPDATE...
            registeredSuccessfully = (ps.executeUpdate() == 1);

        } catch (SQLException e)
        {
            throw new DaoException("registerStudent() " + e.getMessage());
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
                throw new DaoException("registerStudent() " + e.getMessage());
            }
        }
        return registeredSuccessfully;
    }

    public boolean checkIfRegistered(Student student) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isRegistered = false;

        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM Student WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, student.getCaoNumber());  // search based on the cao number

            rs = ps.executeQuery();

            if (rs.next())
            {
                isRegistered = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("checkIfRegistered() " + e.getMessage());
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
                throw new DaoException("checkIfRegistered() " + e.getMessage());
            }
        }
        return isRegistered;
    }

    @Override
    public boolean loginStudent(Student student) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isRegistered = checkIfRegistered(student);
        boolean loggedInSuccessfully = false;

        if(isRegistered)
        {
            try
            {
                con = this.getConnection();

                String query = "SELECT * FROM Student WHERE caoNumber = ? AND dob = ? AND password = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, student.getCaoNumber());
                ps.setDate(2, (java.sql.Date) student.getDob());
                ps.setString(3, student.getPassword());

                rs = ps.executeQuery();

                if (rs.next())
                {
                    loggedInSuccessfully = true;
                }

            } catch (SQLException e)
            {
                throw new DaoException("loginStudent() " + e.getMessage());
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
                    throw new DaoException("loginStudent() " + e.getMessage());
                }
            }
        }

        return loggedInSuccessfully;
    }
}
