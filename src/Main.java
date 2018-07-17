
/**
 * Names and IDs:
 * 		Myriam Tayah (40074762)
 * 		Olivier Fradette-Roy (40074024)
 * COMP 249
 * Assignment #1
 * Due Date : Sunday July 15th 2018
 */

//--------------------------------------------------------------------
// Assignment 1
// Part 2
// Written by: Olivier Fradette-Roy 40074024 and Myriam Tayah 40074762
//--------------------------------------------------------------------

import java.util.Scanner;
import static java.lang.System.out;

/**
 * The Driver class will allow the telephone company to run the software application which they asked for.
 * A welcome message will first be used to introduce the markers to the assignment while a second will mark the beginning of the program.
 *
 * @author Myriam Tayah and Olivier Fradette-roy
 */
public class Main {

    /**
     * Initialization of a private Scanner named keyboard.
     */
    private static Scanner keyboard = new Scanner(System.in);

    /**
     * Initialization of a private integer variable named choice.
     * This variable will hold the menu choice made by the user.
     */
    private static int choice;

    /**
     * Initialization of the final password.
     * The password can not be modified.
     */
    private final static String PASSWORD = "password";

    /**
     * The following lines will prompt the user to enter the maximum number of customers possible.
     * This number will be used to create an array which will hold the information about each customer.
     */
    private static int maxNumberOfCustomers;

    /**
     * This method will ask the company clerk for the maximum number of customers that it can handle.
     */
    private static void promptMaxNumberOfCustomers() {
        out.print("Please enter the maximum number of customers the company can handle: ");
        maxNumberOfCustomers = keyboard.nextInt();
    }

    /**
     * The userDatabase array will hold Customer objects.
     * The size of the array is determined by the users.
     * @see promptMaxNumberOfCustomers().
     */
    private static Customer[] userDatabase = new Customer[];

    /**
     * This method will display the menu choices to the users.
     * The display is repeated through a loop for as long as the user's choice is not held between 1 and 5.
     */
    private static void promptMenu() {
        out.println();
        out.println("What do you want to do?");
        out.println("     1. Enter new customer (password required)");
        out.println("     2. Change information of a customer (password required)");
        out.println("     3. Display all customers resdiding on the same street");
        out.println("     4. Display all customers residing in the same city");
        out.println("     5. Quit");
        out.println();

        do {
            out.print("Please enter your choice > ");
            choice = keyboard.nextInt();
        }
        while (choice < 1 || choice > 5);
    }

    /**
     * This method requests the user to enter the password.
     * The repetition is limited to 3 false tries.
     * @return A boolean value is returned: true if the user entered the correct password but false otherwise.
     */
    private static boolean passwordEntry() {
        int tries = 0;
        String passEntered;
        do {
            out.print("Please enter your password: ");
            passEntered = keyboard.next();
            if (passEntered.equals(PASSWORD)) {
                return true;
            } else {
                tries = tries + 1;
                out.println("Wrong Password");
            }
        } while (tries < 3);
        return false;
    }

    /**
     * This method allows the user to add new customer objects to the userDatabase array.
     * Of course, the method checks for space availability before adding new Customers and will otherwise display a message of error.
     */
    private static void enterNewCustomer() {
        out.print("How many customers do you wish to enter? ");
        int numberNewCustomers = keyboard.nextInt();
        int customerSpace = maxNumberOfCustomers - Customer.numberOfCreatedCustomers;
        if (customerSpace >= numberNewCustomers) {
            for (int i = 0; i < numberNewCustomers; i++) {
                out.println("\nNew customer");
                out.print("Customer name: ");
                String cName = keyboard.next();
                int cStreetNum;
                while (true) {
                    try {
                        out.print("Street Number: ");
                        cStreetNum = keyboard.nextInt();
                        break;
                    } catch (java.util.InputMismatchException e) {
                        out.println("You entered a String instead of a numerical value, please try again");
                        keyboard.next();
                    }
                }
                out.print("Street name: ");
                String cStreetName = keyboard.next();
                out.print("City: ");
                String cCity = keyboard.next();
                out.println(Customer.numberOfCreatedCustomers);
                userDatabase[Customer.numberOfCreatedCustomers] = new Customer(cCity, cName, cStreetName, cStreetNum);
            }
        }
        else {
            out.println("You may add a maximum of " + customerSpace + " customer(s) a this time.");
        }
    }

    /**
     * This method allows the user to modify the attributes of a certain Customer object.
     * It will repeat for as long as the user requests it through a do-while loop and a boolean variable.
     */
    private static void updateCustomer() {
        boolean reenterCustNumber = true;
        do {
            out.print("Please enter the number of the customer you wish to update (index in the array): ");
            int custNum = keyboard.nextInt();

            if (custNum <= (Customer.numberOfCreatedCustomers-1) && custNum >= 0) {
                reenterCustNumber = false;
                out.println("Customer: # " + custNum);
                try {
                    out.println(userDatabase[custNum].toString());
                } catch (ArrayIndexOutOfBoundsException a) {
                    out.println("This customer does not exist");
                } catch (NullPointerException n) {
                    out.println("This customer does not exist");
                }

                // part for updating information
                int choiceForUpdate;
                boolean keepUpdate = true;
                do {
                    do {
                        out.println("What information would you like to change?");
                        out.println("     1. Customer name");
                        out.println("     2. Street number");
                        out.println("     3. Street name");
                        out.println("     4. City");
                        out.println("     5. Quit");
                        out.print("Please enter your choice> ");

                        choiceForUpdate = keyboard.nextInt();
                    } while (choiceForUpdate < 1 || choiceForUpdate > 5);

                    switch(choiceForUpdate) {
                        case 1: {
                            out.print("Please enter new customer name: ");
                            String newName = keyboard.next();
                            userDatabase[custNum].setName(newName);
                            out.println(userDatabase[custNum].toString());
                            break;
                        }
                        case 2: {
                            out.print("Please enter new street number: ");
                            int newNum = keyboard.nextInt();
                            userDatabase[custNum].setStreetNumber(newNum);
                            out.println(userDatabase[custNum].toString());
                            break;
                        }
                        case 3: {
                            out.print("Please enter new street name: ");
                            String newStreet = keyboard.next();
                            userDatabase[custNum].setStreetName(newStreet);
                            out.println(userDatabase[custNum].toString());
                            break;
                        }
                        case 4: {
                            out.print("Please enter new city: ");
                            String newCity = keyboard.next();
                            userDatabase[custNum].setCity(newCity);
                            out.println(userDatabase[custNum].toString());
                            break;
                        }
                        case 5: {
                            keepUpdate = false;
                            break;
                        }
                    }
                } while(keepUpdate);
            }
            else {
                char tryNewCustNum;
                out.print("Error! Do you wish to try another customer number (Y capital for yes, anything else for no): ");
                tryNewCustNum = keyboard.next().charAt(0);
                if (tryNewCustNum == 'Y') {
                    reenterCustNumber = true;
                } else {
                    break;
                }
            }
        } while (reenterCustNumber);
    }

    /**
     * This method allows the display of all Customer objects who share the same content for the Street Name attribute.
     */
    private static void findCustomersByStreet() {
        out.print("What street are you looking for? ");
        String streetMenu = keyboard.next();

        for (int i = 0; i < Customer.numberOfCreatedCustomers; i++) {
            if (userDatabase[i].getStreetName().equalsIgnoreCase(streetMenu)) {
                out.println(userDatabase[i].toString());
            }
        }
    }

    /**
     * This method allows the display of all Customer objects' names who share the same content for the City attribute.
     */
    private static void findCustomersFromCity() {

        out.print("What city are you looking for ? ");
        String cityMenu = keyboard.next();
        out.println();

        for (int i = 0; i < Customer.numberOfCreatedCustomers; i++) {
            if (userDatabase[i].getCity().equalsIgnoreCase(cityMenu)) {
                out.println(userDatabase[i].getName());
            }
        }
        out.println();
    }

    /**
     * This method is a boolean method that asks the user if they wish to perform another action from the original menu.
     * @return It returns true if they wish to continue and false otherwise.
     */
    private static boolean keepGoingMenu() {
        out.println();
        out.print("Do you want to do another action in the menu ? (yes/no) ");
        String keepGoing = keyboard.next().toLowerCase();

        if (keepGoing.equals("yes")) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method allows the display of a closing message at the end of the program.
     */
    private static void closingMessage() {
        out.println("This is the end of the program. Have a good day!");
    }

    /**
     * This method allows the display of an opening message at the beginning of the program.
     * First an opening message for the assignment is displayed, then is one for the program itself.
     */
    private static void openingMessage() {
        // Assignment welcome message
        out.println("Welcome to the first assignment of Olivier and Myriam!");
        out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");

        // Program welcome message
        out.println("Welcome to the customer repertory!");
        out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    /**
     * This static integer variable is used to detect the number of false tries for the password.
     */
    private static int suspiciousActivities;



    /**
     * The following method is the main method of the program which commands the execution of different methods and statements.
     * @param args This is the string of arguments that the program will go through.
     */
    public static void main(String[] args) {

        openingMessage();
        promptMaxNumberOfCustomers();

        /**
         * The following do-while loop allows the execution of the different options that can be selected on the menu.
         * @see promptMenu().
         */
        do {
            promptMenu();

            switch (choice) {
                case 1: {
                    /**
                     * The following lines of code check for the number of tries for the password.
                     * In fact, when selecting option 1, the users have up to 12 tries in total for the password before a message
                     * for suspicious activities is displayed and the program is brought to an end.
                     */
                    suspiciousActivities = 0;
                    do {
                        if (suspiciousActivities > 0) {
                            promptMenu();
                        }
                        suspiciousActivities = suspiciousActivities + 1;
                    } while (!passwordEntry() && suspiciousActivities != 3);

                    if (suspiciousActivities == 3) {
                        out.println("Program detected suspicious activities and will terminate immediately");
                        System.exit(0);
                    }
                    enterNewCustomer();
                    break;
                }
                case 2: {
                    passwordEntry();
                    updateCustomer();
                }
                break;
                case 3: {
                    findCustomersByStreet();
                }
                break;
                case 4:
                    findCustomersFromCity();
                    break;
                case 5: {
                    closingMessage();
                    System.exit(0);
                }
            }
        } while (keepGoingMenu());
        closingMessage();
    }
}









