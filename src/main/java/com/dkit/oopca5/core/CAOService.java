package com.dkit.oopca5.core;

/* The CAOService class has constants to define all of the messages that are sent between the Client and Server
 */

public class CAOService
{
    public static final int PORT_NUM = 50025;
    public static final String HOSTNAME = "localhost";

    public static final String CAO_NUMBER_REGEX = "^[0-9]{8}$";
    public static final String DATE_OF_BIRTH_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String COURSE_ID_REGEX = "[^\\w\\d]*(([0-9]+.*[A-Za-z]+.*)|[A-Za-z]+.*([0-9]+.*))";

    public static final String BREAKING_CHARACTER = "%%";

    public static final String REGISTER_COMMAND = "REGISTER";
    public static final String SUCCESSFUL_REGISTER = "REGISTERED";
    public static final String FAILED_REGISTER = "REG FAILED";

    public static final String LOGIN_COMMAND = "LOGIN";
    public static final String SUCCESSFUL_LOGIN = "LOGGED IN";
    public static final String FAILED_LOGIN = "LOGIN FAILED";

    public static final String LOGOUT_COMMAND = "LOGOUT";
    public static final String SUCCESSFUL_LOGOUT = "LOGGED OUT";
    public static final String FAILED_LOGOUT = "LOGGED FAILED";

    public static final String DISPLAY_COURSE_COMMAND = "DISPLAY COURSE";
    public static final String SUCCESSFUL_DISPLAY_COURSE = "SUCCESSFUL DISPLAY";
    public static final String FAILED_DISPLAY_COURSE = "DISPLAY FAILED";

    public static final String DISPLAY_ALL_COURSES_COMMAND = "DISPLAY_ALL";
    public static final String SUCCESSFUL_DISPLAY_ALL_COURSES = "SUCCESSFUL DISPLAY ALL";
    public static final String FAILED_DISPLAY_ALL_COURSES = "FAILED DISPLAY ALL";

    public static final String DISPLAY_CURRENT_COMMAND = "DISPLAY CURRENT";
    public static final String SUCCESSFUL_DISPLAY_CURRENT = "SUCCESSFUL DISPLAY CURRENT";
    public static final String FAILED_DISPLAY_CURRENT = "FAILED DISPLAY CURRENT";

    public static final String UPDATE_CURRENT_COMMAND = "UPDATE CURRENT";
    public static final String SUCCESSFUL_UPDATE_CURRENT = "SUCCESSFUL UPDATE CURRENT";
    public static final String FAILED_UPDATE_CURRENT = "FAILED UPDATE CURRENT";

}
