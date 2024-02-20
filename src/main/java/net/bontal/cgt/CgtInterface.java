/*
 *   Author: Min Thu Khaing, Thet Paing Hmu
 
 *   Date: from 02-02-2024 to 19-02-2024
 *   Description: Main entry point for CGT calculation program. I know my code looks like haiku.
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
 *      Feel my over-engineered code for capital gains tax calculator.
 *
 *  Sincerely,
 *  Min Thu Khaing
 */

/*
 * This comment goes to God or whatever/whoever created us...
 * I really hate object-oriented programming.
 * The actual Calculation is only two lines or something and then
 * the rest of the code filled with getter, setter, encapsulation and all that oop shits.
 *                                  commented by @pewriebontal on 05-02-2024.
 */

package net.bontal.cgt;

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
 * @version 1.0
 * @since 02-02-2024
 * @author Min Thu Khaing
 * @author Thet Paing Hmu
 * @see User
 * @see Investment
 */

public class CgtInterface {

    /**
     * Constructs a CgtInterface object.
     */
    CgtInterface() {

    }

    /**
     * Runs the CGT calculation program.
     */
    public void run() {
        Scanner console = new Scanner(System.in);
        User user;
        user = new User();

        askUserDetails(user, console);

        askIncome(user, console);

        user.calculateCgt();

        printCapitalGainsTax(user);

        boolean willInvest;

        willInvest = askToInvest(user, console);

        if (willInvest) {
            continueInvestment(user, console);
            user.calculateInvestment();
            printPredictedProfitForInvestment(user);
        }
        printUserData(user, willInvest);
        console.close();
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
     *                             to
     *                             ignore maximum value check)
     * @param acceptEqualToMinimum Boolean value whether going to accept equal
     *                             amount to minimum amount.
     * @param prompt               The message prompting the user for input.
     * @param console              The Scanner object used for input.
     * @param regex                The regular expression used to validate the input.
     * @param invalidMessage       The message displayed when the input is invalid.
     * @return The validated numerical input from the user.
     */
    private double getValidatedNumInput(double minimum, double maximum, boolean acceptEqualToMinimum, String prompt,
            Scanner console, String regex,
            String invalidMessage) {
        double value = 0;
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
     * Asks the user for personal details and sets them in the User object.
     *
     * @param user    The User object to set the personal details.
     * @param console The Scanner object to read user input.
     */
    public void askUserDetails(User user, Scanner console) {
        boolean isElonMusksSon;
        String regexName;
        isElonMusksSon = false; // Turn it to true if we're putting numbers into our names.

        /*
         * Sir, You mentioned earlier in class with ancient headstone example
         * we need to consider for the future.
         * I'm letting numbers in name just in case Elon's Son wants to use this
         * system.
         */

        if (isElonMusksSon)
            regexName = "[a-zA-Z0-9 ]+";
        else
            regexName = "[a-zA-Z ]+";

        /* Get and Set Name */
        String name;
        name = getValidatedInput("What's your name? [put your name hit enter] ", console, true, regexName,
                "Please enter a valid name (letters only, no numbers or special characters).");
        user.setName(name);

        /* Get and Set Salary */
        double salary;
        salary = getValidatedNumInput(0, -1, false, "Your Annual Salary? [input number only] ", console,
                "[0-9]+(\\.[0-9]+)?", "Please enter a positive number for salary. You dont have job? Skill Issue!");
        user.setAnnualSalary(salary);

        /* Get and Set Residential Status */
        String inputResident;
        inputResident = getValidatedInput("Are you resident of Australia? [yes/no] ", console, false, "^(yes|no|y|n)$",
                "Invalid input. Please enter 'yes' or 'no'.");
        user.setResident(inputResident.matches("^(yes|y)$"));
    }

    /**
     * Asks the user for income details and sets them in the User object.
     *
     * @param user    The User object to set the income details.
     * @param console The Scanner object to read user input.
     */
    public void askIncome(User user, Scanner console) {
        /*
         * IRS is coming bro....
         * better hide ur money....
         */

        /* Get Buying Price */
        double buyingPrice;

        buyingPrice = getValidatedNumInput(0, -1, false, "Buying price [type in positive number] : $", console,
                "[0-9]+(\\.[0-9]+)?", "Please enter a valid positive number.");
        user.setBuyingPrice(buyingPrice);

        /* Get Selling Price */
        double sellingPrice;
        /*
         * As PDF mentions
         * For this assignment, we are
         * assuming that the selling price is always more than buying price so this
         * should be checked as well, otherwise
         * an error message is shown and ask again for selling price.
         */
        sellingPrice = getValidatedNumInput(buyingPrice, -1, false, "Selling price [type in positive number] : $",
                console, "[0-9]+(\\.[0-9]+)?",
                "Please enter a value greater than Buying Price");

        user.setSellingPrice(sellingPrice);

        /* Get Years held */
        int numberOfYearsHeld;
        numberOfYearsHeld = (int) getValidatedNumInput(1, -1, true, "Number of years held [type in positive number] : ",
                console, "[0-9]+",
                "Please enter a positive number greater than zero.");
        user.setYears(numberOfYearsHeld);
    }

    /**
     * Asks the user if they want to invest.
     *
     * @param user    The User object representing the user.
     * @param console The Scanner object to read user input.
     * @return true if the user wants to invest, false otherwise.
     */
    public boolean askToInvest(User user, Scanner console) {
        /*
         * I really want to set function name ScamTheUser()
         * bro just buy the lands in Third World countries like in
         * (SEA countries like Burma or African Countries.)
         * you're sure going to be rich already
         * if not ur grandchildren will.
         */
        boolean invest = false;
        String willInvest;

        willInvest = getValidatedInput("Would you like to invest? [yes/no] ", console, false, "^(yes|no|y|n)$",
                "Invalid input. Please enter 'yes' or 'no'.");
        if (willInvest.equals("yes") || willInvest.equals("y")) {
            invest = true;
        }
        return invest;
    }

    /**
     * Prompts the user to continue with investment and collects investment details.
     *
     * @param user    The User object representing the user.
     * @param console The Scanner object for user input.
     */
    public void continueInvestment(User user, Scanner console) {
        /*
         * He's scamming bro, careful!!
         * Your Savings going to the moon but upside down.
         * see the image here: https://dl.bontal.net/images/to_the_moom.webp
         */

        double firstYearDeposit = 0; // I don't know should I use year1Deposit or yearOneDeposit or firstYearDeposit?
        double secondYearDeposit = 0;
        double thirdYearDeposit = 0;

        firstYearDeposit = getValidatedNumInput(0, user.getActualProfit(),
                false, "Enter initial investment amount (cannot exceed $" + user.getActualProfit() + "): $", console,
                "[0-9]+(\\.[0-9]+)?",
                "Invalid input. Initial investment amount cannot exceed $" + user.getActualProfit() + ".");

        // Get and validate subsequent deposits
        for (int year = 2; year <= 3; year++) {
            double deposit;

            deposit = getValidatedNumInput(0, -1,
                    true, "Enter investment amount after year " + (year - 1) + ": $", console,
                    "[0-9]+(\\.[0-9]+)?", "Invalid input. Please enter a positive number.");
            if (year == 2) {
                secondYearDeposit = deposit;
            } else {
                thirdYearDeposit = deposit;
            }
        }

        user.setDeposit(firstYearDeposit, 1);
        user.setDeposit(secondYearDeposit, 2);
        user.setDeposit(thirdYearDeposit, 3);

        /* Get and Set Coin Selection */
        int coinSelection;
        System.out.println("Choose the Cryptocurrency to invest in");
        System.out.println("1 for Best Coin (predicted profit rates 18%)");
        System.out.println("2 for Simple Coin (predicted profit rates 12%)");
        System.out.println("3 for Fast Coin (predicted profit rates 15%)");

        coinSelection = (int) getValidatedNumInput(1, 3, true, "[type 1/2/3]: ", console,
                "[0-9]+", "Invalid input. Please enter a number between 1 and 3.");

        // Set coin selection in the user object
        user.setInvestCoinSelection(coinSelection);
        System.out.println("You Selected " + getSelectedCoinName(user, user.getInvestCoinSelection()));
    }

    /**
     * Retrieves the name of the selected cryptocurrency based on user's choice.
     *
     * @param user     The User object representing the user.
     * @param selected The integer representing the selected cryptocurrency.
     * @return The name of the selected cryptocurrency.
     */
    public String getSelectedCoinName(User user, int selected) {
        return switch (user.getInvestCoinSelection()) {
            case 1 -> "BestCoin";
            case 2 -> "SimpleCoin";
            case 3 -> "FastCoin";
            default -> throw new IllegalStateException("Unexpected value: " + user.getInvestCoinSelection());
        };
    }

    private static void printTableRow(int year, double yearlyProfit, double totalProfit) {
        System.out.printf("%-8d|$%-21.2f|$%-14.2f\n", year, yearlyProfit, totalProfit);
    }

    /**
     * Prints the predicted profit for the investment.
     *
     * @param user The User object representing the user.
     */
    public void printPredictedProfitForInvestment(User user) {
        /*
         * This function print Predicted Profit Table in a nice way.
         */
        System.out
                .println("Predicted Profit for Investment in "
                        + getSelectedCoinName(user, user.getInvestCoinSelection()));
        System.out.printf("%-8s|%-22s|%-15s\n", "Years", "YearlyProfit", "TotalProfit");
        System.out.println("________|______________________|_______________");

        for (int year = 1; year <= 3; year++) {
            printTableRow(year, user.getYearlyProfit(year), user.getTotalProfit(year));
        }
    }

    /**
     * Prints the Capital Gains Tax the user is liable for.
     *
     * @param user The User object representing the user.
     */
    public void printCapitalGainsTax(User user) {
        System.out.println();

        System.out.println("Capital Gains Tax:");
        System.out.println("Tax Rate : " + user.getTaxRate() * 100 + "%");
        System.out.println("Capital Gains Tax : " + user.getCgt());
        System.out.println("Profit : " + user.getActualProfit());

        System.out.println();
    }

    /**
     * Prints all available user data.
     *
     * @param user       The User object representing the user.
     * @param willInvest Boolean indicating whether the user will invest for the
     *                   coins.
     */
    public void printUserData(User user, boolean willInvest) {
        /* print all the available User data in the final */

        /* User Details */
        System.out.println();
        System.out.println("User Details");

        System.out.println("Name : " + user.getName());
        System.out.println("Annual Salary : " + user.getAnnualSalary());

        System.out.print("Residential Status : ");
        if (user.getResident())
            System.out.println("Yes");
        else
            System.out.println("No");
        System.out.println();

        /* PRINT INVESTMENT */
        System.out.println("Crypto Currency");
        System.out.println("Buying Price : " + user.getBuyingPrice());
        System.out.println("Selling Price : " + user.getSellingPrice());
        System.out.println("Number of years held : " + user.getYears());

        printCapitalGainsTax(user);
        if (willInvest)
            printPredictedProfitForInvestment(user);
    }

    /**
     * The main method to start the CGT calculation program.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        CgtInterface calc = new CgtInterface();
        calc.run();
    }
}
