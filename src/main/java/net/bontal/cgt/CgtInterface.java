/*
 *   Author: Min Thu Khaing, Thet Paing Hmu
 *   Date: 21-02-2024
 *   Description: Main entry point for CGT calculation program. I know our code looks like haiku.
 *   GitHub: @pewriebontal, @LinVulpes
 * 	 © 2024 Min Thu Khaing, Thet Paing Hmu. All rights reserved.
 */

/*
 *                       _oo0oo_
 *                      o8888888o
 *                      88" . "88
 *                      (| -_- |)
 *                      0\  =  /0
 *                    ___/`---'\___
 *                  .' \\|     |// '.
 *                 / \\|||  :  |||// \
 *                / _||||| -:- |||||- \
 *               |   | \\\  -  /// |   |
 *               | \_|  ''\---/''  |_/ |
 *               \  .-\__  '-'  ___/-. /
 *             ___'. .'  /--.--\  `. .'___
 *          ."" '<  `.___\_<|>_/___.' >' "".
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /
 *     =====`-.____`.___ \_____/___.-`___.-'=====
 *                       `=---='
 *
 *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *               佛祖保佑         永无BUG
 */

/*
 * Dear Sir,
 *
 *      Feel our over-engineered code for capital gains tax calculator.
 *
 *  Sincerely,
 *  Min Thu Khaing, Thet Paing Hmu
 */

/*
 * This comment goes to God or whatever/whoever created us...
 * I really hate object-oriented programming.
 * The actual Calculation is only two lines or something and then
 * the rest of the code filled with getter, setter, encapsulation and all that oop shits.
 *                                  commented by @pewriebontal on 05-02-2024.
 */
package net.bontal.cgt;

import java.io.File;
import java.io.Writer;
import java.util.*;

/**
 * The CgtInterface class serves as the main entry point for the Capital Gains
 * Tax (CGT) calculation program.
 * It interacts with the user to collect necessary information, performs
 * calculations, and displays results.
 *
 * <p>
 * This class includes methods to prompt the user for details such as name,
 * salary, residency status, and investment information.
 * It also calculates CGT, predicts profits for investment, and prints user
 * data.
 *
 * <p>
 * Copyright © 2024 Min Thu Khaing, Thet Paing Hmu.
 * All rights reserved.
 *
 * @version 2.0
 * @since 02-02-2024
 * @author Min Thu Khaing
 * @author Thet Paing Hmu
 * @see User
 * @see Investment
 */
public class CgtInterface {

    static Scanner console;
    private final User[] users;
    private int userCount;

    /* ========== Constructor Starts ============== */

    /**
     * Default constructor for the CgtInterface class.
     * Initializes the users array with a size of 5 and userCount to 0.
     */
    public CgtInterface() {
        this.users = new User[5];
        this.userCount = 0;
        console = new Scanner(System.in);
    }

    /**
     * Parameterized constructor for the CgtInterface class.
     * Initializes the users array with a size of 5 and userCount to 0.
     * If the devBuild parameter is true, it adds some users and investment accounts
     * for testing purposes.
     *
     * @param devBuild A boolean value to determine if the program is in development
     *                 mode.
     */
    public CgtInterface(boolean devBuild) {
        this.users = new User[5];
        this.userCount = 0;
        console = new Scanner(System.in);

        if (devBuild) {
            // Development Build: Add some users and investment accounts
            // for testing purposes.
            // This will be removed in the final build.

            users[0] = new User("Alice", 50000, true, 1000, 2000, 2);
            users[1] = new User("Bob", 60000, false, 2000, 3000, 3);
            users[2] = new User("Charlie", 70000, true, 3000, 4000, 1);
            users[3] = new User("Dave", 80000, false, 4000, 5000, 4);
            userCount = 4;

            users[0].addNewInvestmentAccount(new Investment(100, 2000, 3000, 1));
            users[0].addNewInvestmentAccount(new Investment(100, 2000, 3000, 2));
            users[1].addNewInvestmentAccount(new Investment(100, 2000, 3000, 2));
            users[2].addNewInvestmentAccount(new Investment(100, 3000, 4000, 3));
            users[3].addNewInvestmentAccount(new Investment(100, 4000, 5000, 1));
        }
    }

    /* ========== Constructor Ends ============== */

    /* ========== Main Menu Functions Starts ============== */

    private void mainMenuAddUser() {
        String name;
        double salary;
        boolean isResident;

        double buyingPrice;
        double sellingPrice;
        int numberOfYearsHeld;

        name = null;
        salary = 0;
        isResident = false;

        buyingPrice = 0;
        sellingPrice = 0;
        numberOfYearsHeld = 0;

        if (userCount == 5) {
            this.displayMessage("Maximum number of users reached.", "red");
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
            return;
        }

        name = this.getValidatedInput("What's your name? [put your name hit enter] ", console, true, "[a-zA-Z ]+",
                "Please enter a valid name (letters only, no numbers or special characters).");

        // check if user already exists
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                this.displayMessage("ERROR: User already exists", "red");
                this.addDelay(1377);
                this.pressAnyKeyToContinue();
                return;
            }
        }

        salary = this.getValidatedNumInput(0, -1, false, "Your Annual Salary? [input number only] ", console,
                "[0-9]+(\\.[0-9]+)?", "Please enter a positive number for salary. You dont have job? Skill Issue!");

        isResident = this
                .getValidatedInput("Are you resident of Australia? [yes/no] ", console, false, "^(yes|no|y|n)$",
                        "Invalid input. Please enter 'yes' or 'no'.")
                .matches("^(yes|y)$");

        buyingPrice = this.getValidatedNumInput(0, -1, false, "Buying price [type in positive number] : $", console,
                "[0-9]+(\\.[0-9]+)?", "Please enter a valid positive number.");

        sellingPrice = this.getValidatedNumInput(buyingPrice, -1, false, "Selling price [type in positive number] : $",
                console, "[0-9]+(\\.[0-9]+)?",
                "Please enter a value greater than Buying Price");

        numberOfYearsHeld = (int) this.getValidatedNumInput(1, -1, true,
                "Number of years held [type in positive number] : ",
                console, "[0-9]+",
                "Please enter a positive number greater than zero.");

        this.showDotAnimation("Adding User", "blue");
        this.functionAddUser(name, salary, isResident, buyingPrice, sellingPrice, numberOfYearsHeld);
        this.displayMessage("User added successfully.", "green");
        this.addDelay(1377);
        this.pressAnyKeyToContinue();
    }

    private void MainMenuDeleteUser() {
        User user;
        user = null;

        user = this.findUserByName(getValidatedInput("Enter name: ", console, true, "[a-zA-Z ]+", "Invalid name."));
        this.showDotAnimation("🔎 Searching User", "blue");
        if (user == null) {
            this.displayMessage("User not found", "red");
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
            return;
        } else {
            this.showDotAnimation("Deleting User", "blue");
            this.functionDeleteUser(user);
            this.displayMessage("User deleted successfully.", "green");
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
        }
    }

    private void mainMenuDisplayUser() {
        User user;
        user = null;

        user = this.findUserByName(getValidatedInput("Enter name: ", console, true, "[a-zA-Z ]+", "Invalid name."));

        this.showDotAnimation("🔎 Searching User", "blue");
        if (user == null) {
            this.displayMessage("User not found.", "red");
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
            return;
        } else {
            this.functionDisplayUser(user);
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
        }
    }

    private void mainMenuDisplayAllUsers() {
        this.functionDisplayAllUsers();
        this.pressAnyKeyToContinue();
    }

    private void mainMenuAddInvestment() {
        User user;

        user = null;
        user = this.findUserByName(getValidatedInput("Enter name: ", console, true, "[a-zA-Z ]+", "Invalid name."));
        this.showDotAnimation("🔎 Searching User", "blue");
        if (user == null) {
            this.displayMessage("User not found", "red");
            this.addDelay(500);
            this.pressAnyKeyToContinue();
            return;
        } else {
            this.displayMessage("User found", "green");
            if (user.getAvailableBalance() == 0) {
                this.displayMessage("Bro! you're too broke to invest, go get a job first", "red");
                this.addDelay(1337);
                this.pressAnyKeyToContinue();
                return;
            } else if (user.getNumberOfAccounts() == 2) {
                this.displayMessage("Maximum number of investment accounts reached.", "red");
                this.addDelay(1337);
                this.pressAnyKeyToContinue();
                return;
            }
            this.showDotAnimation("Adding Investment", "blue");
            user.addNewInvestmentAccount(this.createInvestmentAccount(user.getAvailableBalance()));
            this.displayMessage("Investment added successfully.", "green");
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
        }
    }

    private void mainMenuDisplayInvestment() {
        User user;

        user = null;

        user = this.findUserByName(getValidatedInput("Enter name: ", console, true, "[a-zA-Z ]+", "Invalid name."));
        if (user == null) {
            this.displayMessage("User not found.", "red");
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
            return;
        } else {

            if (user.getNumberOfAccounts() == 0) {
                this.displayMessage("No investment account found for the user: " + user.getName(), "red");
                this.addDelay(1337);
                this.pressAnyKeyToContinue();
                return;
            }

            int accountNumber;
            accountNumber = (int) this.getValidatedNumInput(1, 2, true, "Enter account number: ", console, "[0-2]+",
                    "Invalid account number.");
            this.functionDisplayInvestment(user.getInvestmentAccount(accountNumber - 1));
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
        }

    }

    private void mainMenuDeleteInvestment() {

        User user;
        user = null;

        user = this.findUserByName(getValidatedInput("Enter name: ", console, true, "[a-zA-Z ]+", "Invalid name."));
        if (user == null) {
            this.displayMessage("User not found.", "red");
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
            return;
        } else {
            if (user.getNumberOfAccounts() == 0) {
                this.displayMessage("No investment account found for the user: " + user.getName(), "red");
                this.addDelay(1337);
                this.pressAnyKeyToContinue();
                return;
            } else {
                int accountNumber;
                accountNumber = (int) this.getValidatedNumInput(1, 2, true, "Enter account number: ", console, "[0-2]+",
                        "Invalid account number.");
                this.showDotAnimation("Deleting Investment Account", "blue");
                if (user.deleteInvestmentAccount(accountNumber - 1) == 0) {
                    this.displayMessage("Investment account deleted successfully.", "green");
                    this.addDelay(1337);
                    this.pressAnyKeyToContinue();
                } else {
                    this.displayMessage("Account not found.", "red");
                    this.addDelay(1337);
                    this.pressAnyKeyToContinue();
                }
            }
        }

    }

    private void mainMenuSaveToFile() {
        this.showDotAnimation("Saving Data", "blue");
        this.functionSaveToFile();
        this.addDelay(1337);
        this.displayMessage("Data saved successfully.", "green");
        this.pressAnyKeyToContinue();
    }

    private void mainMenuExitProgram() {
        this.displayMessage("Exiting Program...", "red");
        this.addDelay(1337);
        this.functionExitProgram();
    }

    /* ========== Main Menu Functions Ends ============== */

    /* ========== Level 1 Helper Functions Starts ============== */

    // NOTE TO SELF : MAIN PROGRAM REQUREMENT NO.1
    private void functionAddUser(String name, double salary, boolean isResident, double buyingPrice,
            double sellingPrice,
            int numberOfYearsHeld) {
        if (userCount < 5) {
            users[userCount] = new User(name, salary, isResident, buyingPrice, sellingPrice, numberOfYearsHeld);
            userCount++;
        } else {
            this.displayMessage("Maximum number of users reached.", "red");
        }
    }

    // NOTE TO SELF : MAIN PROGRAM REQUREMENT NO.2
    // WHILE LOOP IS CLEANER THAN FOR LOOP
    // AND EASIER TO READ AND UNDERSTAND

    private void functionDeleteUser(User user) {
        int i = 0;
        boolean userFound = false;
        while (i < userCount && !userFound) {
            if (users[i].equals(user)) {
                userFound = true;
                while (i < userCount - 1) {
                    users[i] = users[i + 1];
                    i++;
                }
                userCount--;
            }
            i++;
        }
    }

    // NOTE TO SELF : MAIN PROGRAM REQUREMENT NO.3
    private void functionDisplayUser(User user) {
        this.printUserData(user);
    }

    // NOTE TO SELF : MAIN PROGRAM REQUREMENT NO.4
    private void functionDisplayAllUsers() {

        if (userCount == 0) {
            this.showDotAnimation("Loading", "blue");
            this.displayMessage("No users found.", "red");
            this.addDelay(1337);
            this.pressAnyKeyToContinue();
            return;
        }

        for (int i = 0; i < userCount; i++) {
            this.displayMessage("User " + (i + 1), "cyan");
            this.functionDisplayUser(users[i]);
        }
    }

    // NOTE TO SELF : MAIN PROGRAM REQUREMENT NO.5
    private void functionDisplayInvestment(Investment investment) {
        /*
         * This function print Investedment ammount and Predicted Profit Table in a nice
         * way.
         */

        System.out.println(); // Spacer
        this.displayMessage("Investment Details", "green");
        System.out.println(); // Spacer
        for (int year = 1; year <= 3; year++) {
            this.displayMessage("Year " + year + " Deposit : $" + investment.getDeposit(year), "white");
        }
        System.out.println(); // Spacer
        this.displayMessage("Predicted Profit for Investment in "
                + getSelectedCoinName(investment), "green");
        System.out.println(); // Spacer
        System.out.printf("%-8s|%-22s|%-15s\n", "Years", "YearlyProfit", "TotalProfit");
        System.out.println("________|______________________|_______________");

        for (int year = 1; year <= 3; year++) {
            this.printTableRow(year, investment.getYearlyProfit(year), investment.getTotalProfit(year));
        }
    }

    // NOTE TO SELF : MAIN PROGRAM REQUREMENT NO.6
    private void functionSaveToFile() {
        // Real function to Save all the user data to a file will be implemented here.

        File file = new File("user_data.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists. Overwriting...");
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

        // Writing to the file

    }

    // NOTE TO SELF : MAIN PROGRAM REQUREMENT NO.7
    private void functionExitProgram() {
        System.exit(0);
    }

    /* ========== Level 1 Helper Functions Ends ============== */

    /* ========== Level 2 Helper Functions Starts ============== */

    /**
     * Finds a user by name in the users array.
     *
     * @param name The name of the user to find.
     * @return The User object if found, otherwise null.
     */
    private User findUserByName(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                return users[i];
            }
        }
        return null;
    }

    /**
     * Displays a message prompting the user to press any key to continue.
     */
    private void pressAnyKeyToContinue() {

        System.out.println();
        this.displayMessage("Press any key to continue...", "cyan");

        try {
            System.in.read();
        } catch (Exception e) {
            throw new RuntimeException("Error reading from console");
        }
    }

    /**
     * Displays a message with the specified color.
     * 
     * @param message The message to display.
     * @param color   The color of the message.
     */
    private void displayMessage(String message, String color) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        switch (color) {
            case "black":
                System.out.println(ANSI_BLACK + message + ANSI_RESET);
                break;
            case "red":
                System.out.println(ANSI_RED + message + ANSI_RESET);
                break;
            case "green":
                System.out.println(ANSI_GREEN + message + ANSI_RESET);
                break;
            case "yellow":
                System.out.println(ANSI_YELLOW + message + ANSI_RESET);
                break;
            case "blue":
                System.out.println(ANSI_BLUE + message + ANSI_RESET);
                break;
            case "purple":
                System.out.println(ANSI_PURPLE + message + ANSI_RESET);
                break;
            case "cyan":
                System.out.println(ANSI_CYAN + message + ANSI_RESET);
                break;
            case "white":
                System.out.println(ANSI_WHITE + message + ANSI_RESET);
                break;
            default:
                System.out.println(message);
        }
    }

    /**
     * Retrieves and validate input from the user.
     *
     * @param prompt         The message prompting the user for input.
     * @param console        The Scanner object used for input.
     * @param isName         Boolean value whether it's name or not.
     * @param regex          The regular expression used to validate the input.
     * @param invalidMessage The message displayed when the input does not match the
     *                       regex.
     * @return The validated input from the user.
     */
    private String getValidatedInput(String prompt, Scanner console, boolean isName, String regex,
            String invalidMessage) {
        String input;
        do {
            System.out.print(prompt);
            if (isName)
                input = console.nextLine();
            else
                input = console.nextLine().toLowerCase();
            if (!input.matches(regex)) {
                System.out.println(invalidMessage);
            }
        } while (!input.matches(regex));
        return input;
    }

    /**
     * Retrieves and validate numerical input from the user.
     *
     * @param minimum              The minimum allowed value for the input.
     * @param maximum              The maximum allowed value for the input. (put -1
     *                             to ignore maximum value check)
     * @param acceptEqualToMinimum Boolean value whether going to accept equal
     *                             amount to minimum amount.
     * @param prompt               The message prompting the user for input.
     * @param console              The Scanner object used for input.
     * @param regex                The regular expression used to validate the
     *                             input.
     * @param invalidMessage       The message displayed when the input is invalid.
     * @return The validated numerical input from the user.
     */
    private double getValidatedNumInput(double minimum, double maximum, boolean acceptEqualToMinimum, String prompt,
            Scanner console, String regex,
            String invalidMessage) {
        double value = -1; // Setting value to -1 to pass the minimum value check, also compiler check,
                           // never set it to 0,
        String input;
        do {
            System.out.print(prompt);
            input = console.nextLine();
            if (!input.matches(regex)) {
                System.out.println("Please Enter a valid number in correct format!");
                continue;
            }
            value = Double.parseDouble(input);
            if (value <= minimum && !acceptEqualToMinimum || value < minimum && acceptEqualToMinimum
                    || (maximum != -1 && value > maximum)) {
                System.out.println(invalidMessage);
            }
        } while (value <= minimum && !acceptEqualToMinimum || value < minimum && acceptEqualToMinimum
                || (maximum != -1 && value > maximum));
        return value;
    }

    /**
     * Prints all available user data in a formatted way.
     * 
     * @param user The User object representing the user.
     */
    private void printUserData(User user) {

        /* User Details */
        this.displayMessage("_______________________________________________", "green");
        System.out.println();
        this.displayMessage("User Details", "green");

        this.displayMessage("Name : " + user.getName(), "white");
        this.displayMessage("Annual Salary : " + user.getAnnualSalary(), "white");

        System.out.print("Residential Status : ");
        if (user.getResident()) {
            this.displayMessage("Yes", "green");
        } else {
            this.displayMessage("No", "yellow");
        }

        System.out.println(); // Spacer

        /* PRINT INVESTMENT */
        this.displayMessage("Crypto Currency", "green");
        this.displayMessage("Buying Price : " + user.getBuyingPrice(), "white");
        this.displayMessage("Selling Price : " + user.getSellingPrice(), "white");
        this.displayMessage("Number of years held : " + user.getYearsHold(), "white");

        /* PRINT CGT */
        System.out.println(); // Spacer

        this.displayMessage("Capital Gains Tax:", "green");
        this.displayMessage("Tax Rate : " + user.getTaxRate() * 100 + "%", "white");
        this.displayMessage("Capital Gains Tax : " + user.getCgt(), "white");
        this.displayMessage("Profit : " + user.getActualProfit(), "white");

        System.out.println(); // Spacer

        /* Print Avaliable Balance */
        this.displayMessage("Avaliable Balance : " + user.getAvailableBalance(), "yellow");

        System.out.println(); // Spacer

        /* Print Investment Accounts */

        if (user.getNumberOfAccounts() > 0) {
            this.displayMessage("Investment Accounts for " + user.getName(), "green");
            for (int i = 0; i < user.getNumberOfAccounts(); i++) {
                System.out.println(); // Spacer
                System.out.println("Account Number : " + (i + 1));
                this.functionDisplayInvestment(user.getInvestmentAccount(i));
                System.out.println(); // Spacer
            }
        } else {
            this.displayMessage("No investment account found for the user: " + user.getName(), "red");
        }

    }

    /**
     * Prints a row in a table format. In this case, it prints the year, yearly
     * profit, and total profit.
     * 
     * @param num1 The integer value for the first column.
     * @param num2 The double value for the second column.
     * @param num3 The double value for the third column.
     */
    private void printTableRow(int num1, double num2, double num3) {
        System.out.printf("%-8d|$%-21.2f|$%-14.2f\n", num1, num2, num3);
    }

    /**
     * Retrieves the name of the selected cryptocurrency based on user's choice.
     *
     * @param user     The User object representing the user.
     * @param selected The integer representing the selected cryptocurrency.
     * @return The name of the selected cryptocurrency.
     */
    private String getSelectedCoinName(Investment investment) {
        return switch (investment.getCoinSelection()) {
            case 1 -> "BestCoin";
            case 2 -> "SimpleCoin";
            case 3 -> "FastCoin";
            default -> throw new IllegalStateException("Unexpected value: " + investment.getCoinSelection());
        };
    }

    /* ========== Level 2 Helper Functions Ends ============== */

    /**
     * This method creates an Investment object by prompting the user for the
     * initial
     * investment amount, subsequent deposits, and the cryptocurrency to invest in.
     * The method validates the user input and returns the Investment object.
     * 
     * @param availableAmount The available balance for the user to invest.
     * @return The Investment object created with the user input.
     */
    private Investment createInvestmentAccount(double availableAmount) {
        /*
         * He's scamming bro, careful!!
         * Your Savings going to the moon but upside down.
         * see the image here: https://dl.bontal.net/images/to_the_moom.webp
         */
        double year1Deposit;
        double year2Deposit;
        double year3Deposit;

        int coinSelection;

        coinSelection = 0;
        year1Deposit = 0;
        year2Deposit = 0;
        year3Deposit = 0;

        year1Deposit = this.getValidatedNumInput(0, availableAmount,
                false, "Enter initial investment amount (cannot exceed $" + availableAmount + "): $", console,
                "[0-9]+(\\.[0-9]+)?",
                "Invalid input. Initial investment amount cannot exceed $" + availableAmount + ".");

        // Get and validate subsequent deposits
        for (int year = 2; year <= 3; year++) {
            double deposit;

            deposit = this.getValidatedNumInput(0, -1,
                    true, "Enter investment amount after year " + (year - 1) + ": $", console,
                    "[0-9]+(\\.[0-9]+)?", "Invalid input. Please enter a positive number.");
            if (year == 2) {
                year2Deposit = deposit;
            } else {
                year3Deposit = deposit;
            }
        }

        this.displayMessage("Choose the Cryptocurrency to invest in", "cyan");
        this.displayMessage("1 for Best Coin (predicted profit rates 18%)", "cyan");
        this.displayMessage("2 for Simple Coin (predicted profit rates 12%)", "cyan");
        this.displayMessage("3 for Fast Coin (predicted profit rates 15%)", "cyan");

        coinSelection = (int) this.getValidatedNumInput(1, 3, true, "[type 1/2/3]: ", console,
                "[0-9]+", "Invalid input. Please enter a number between 1 and 3.");

        return new Investment(year1Deposit, year2Deposit, year3Deposit, coinSelection);
    }

    /**
     * This method clears the console screen and reloads the screen by recalculating
     * the user's CGT, investment, and available balance.
     * It also adds a delay to make the program more interactive.
     */
    private void reloadScreen() {
        /*
         * Every time the screen is reloaded,
         * the user's CGT, investment, and available
         * balance are recalculated.
         */
        for (int i = 0; i < userCount; i++) {
            users[i].calculateCgt();
            users[i].calculateInvestment();
            users[i].calculateAvailableBalance();
        }
        this.addDelay(2000);
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear the console
        System.out.flush();
    }

    /**
     * Artificial delay to make the program more interactive.
     * I tried with different delay times and found that
     * 1337 milliseconds is an ideal delay time.
     * 
     * @param delay The delay time in milliseconds.
     */
    private void addDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * This method displays the main menu to the user.
     * The menu includes options to add, delete, and display users,
     * add, delete, and display investments, save data to a file,
     * and exit the program.
     */
    private void displayMenu() {
        this.displayMessage("Welcome to CGT Calculation Program", "green");
        this.displayMessage("============================================", "yellow");
        this.displayMessage("Please select an option from the menu below:", "cyan");
        this.displayMessage("============================================", "yellow");
        this.displayMessage("1. Add User", "cyan");
        this.displayMessage("2. Delete User", "cyan");
        this.displayMessage("3. Display User", "cyan");
        this.displayMessage("4. Display All Users", "cyan");
        this.displayMessage("5. Add Investment", "cyan");
        this.displayMessage("6. Display Investment", "cyan");
        this.displayMessage("7. Delete Investment", "cyan");
        this.displayMessage("8. Save to File", "cyan");
        this.displayMessage("9. Exit", "cyan");
        System.out.println(); // Spacer
    }

    /**
     * This method displays a loading animation with dots(example: Loading...).
     *
     * @param message The message to display before the dots.
     * @param color   The color of the message.
     */
    private void showDotAnimation(String message, String color) {
        for (int i = 0; i < 15; i++) {
            StringBuilder loadingMessage = new StringBuilder(message);
            for (int j = 0; j <= i % 4; j++) {
                loadingMessage.append(".");
            }
            System.out.print("\033[H\033[2J"); // Clear the console
            this.displayMessage(loadingMessage.toString(), color);
            this.addDelay(137);
        }
    }

    /**
     * This method displays a loading animation (Loading...)
     */
    private void loadingScreen() {
        this.showDotAnimation("🧠 Loading", "blue");
    }

    /**
     * The main menu loop that displays the menu and processes user input.
     * The loop continues until the user chooses to exit the program.
     * The user's choice is validated to ensure it is within the range of menu
     * options.
     * The appropriate method is called based on the user's choice.
     */
    private void mainMenuLoop() {
        int choice;
        choice = 0;

        do {
            this.loadingScreen();
            this.reloadScreen();
            this.displayMenu();

            choice = (int) this.getValidatedNumInput(1, 9, true, "Enter Your choice: ", console, "[1-9]",
                    "Invalid choice.");

            switch (choice) {
                case 1:
                    this.mainMenuAddUser();
                    break;
                case 2:
                    this.MainMenuDeleteUser();
                    break;
                case 3:
                    this.mainMenuDisplayUser();
                    break;
                case 4:
                    this.mainMenuDisplayAllUsers();
                    break;
                case 5:
                    this.mainMenuAddInvestment();
                    break;
                case 6:
                    this.mainMenuDisplayInvestment();
                    break;
                case 7:
                    this.mainMenuDeleteInvestment();
                    break;
                case 8:
                    this.mainMenuSaveToFile();
                    break;
                case 9:
                    this.mainMenuExitProgram();
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 9);

    }

    /**
     * Runs the CGT calculation program.
     */
    private void run() {
        this.mainMenuLoop();
    }

    /**
     * The main method to start the CGT calculation program.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        CgtInterface calc = new CgtInterface(true);
        calc.run();
    }
}
