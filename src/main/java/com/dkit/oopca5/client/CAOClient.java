package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.core.Colours;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CAOClient
{
    // put in dto courseChoices - ignore?
    // public List<String> findCoursesForUser(int caoNumber) throws DAOException;
    // TODO: 26/03/2021 - below method
    // public void updateCoursesForUser(int caoNumber, List<String> courses) throws DAOException;

    // TODO: 24/03/2021 - TWO MENUS

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("*** WELCOME TO THE CAO ***");

        CAOClient client = new CAOClient();
        client.start();
    }

    private void start()
    {
        mainMenuLoop();
    }

    private void printMainMenuOptions()
    {
        System.out.println("\nEnter your choice:");
        for (int i = 1; i < MainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ") " + MainMenu.values()[i].toString() + Colours.RESET);
        }
    }

    private void mainMenuLoop()
    {
        Scanner scan = new Scanner(System.in);

        // student menu
        MainMenu selectedOption = MainMenu.CONTINUE;
        while (selectedOption != MainMenu.QUIT_APPLICATION)
        {
            try{
                printMainMenuOptions();
                selectedOption = MainMenu.values()[Integer.parseInt(scan.nextLine().trim())];

                switch (selectedOption)
                {
                    case REGISTER:
                        System.out.println("REGISTER SELECTED");
                        break;
                    case LOGIN:
                        System.out.println("LOGIN SELECTED");
                        break;
                    case QUIT_APPLICATION:
                        System.out.println("QUIT SELECTED");
                        break;
                    default:
                        System.out.println("Invalid entry, try again");
                }
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Invalid entry, try again");
            }
        }
    }
}
