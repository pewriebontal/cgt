/*
*   Author: Min Thu Khaing, Thet Paing Hmu

*   Date: from 02-02-2024 to 05-02-2024
*   Description: Main entry point for CGT calculation program. I know my code looks like haiku.
*   GitHub: @pewriebontal, @LinVulpes
* 	© 2024 Min Thu Khaing, Thet Paing Hmu. All rights reserved.
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
 *      Respectfully, if you're reading this, please forgive me for not commenting
 *  on all the lines of code. I find it really annoying to comment on every single
 *  line of code to explain what the functions and variables does, especially when using OOP,
 *  as it is self-explanatory already. I hope you can also relate to me.
 *  I find it really annoying to read and write code that is full of comments.
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

public class CgtInterface {
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

    /* Need to Test heavily */
    private String getValidatedInput(String prompt, Scanner console, String regex, String invalidMessage) {
        String input;
        do {
            System.out.print(prompt);
            input = console.nextLine(); // using Scanner.nextLine() to accept
            /* space in Full Name, for example. "Natalia Galileo Oreo" */
            if (!input.matches(regex)) {
                System.out.println(invalidMessage);
            }
        } while (!input.matches(regex));
        return input;
    }

    private int getValidatedNumInput(double minimum, double maximum, String prompt, Scanner console,
            String invalidMessage) {
        int value;
        do {
            System.out.print(prompt);
            while (!console.hasNextInt()) {
                System.out.println("Enter Positive Number Only!");
                System.out.print(prompt);
                console.next();
            }
            value = console.nextInt();
            if (value < minimum || (maximum != -1 && value > maximum)) {
                System.out.println(invalidMessage);
            }
        } while (value < minimum || (maximum != -1 && value > maximum));
        return value;
    }

    private String getYesOrNoInput(String prompt, Scanner console) {
        String input;
        do {
            System.out.print(prompt);
            input = console.next().toLowerCase();
        } while (!input.equals("yes") && !input.equals("no") && !input.equals("y") && !input.equals("n"));
        return input;
    }
    /* Ends here */

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
        name = getValidatedInput("What's your name? [put your name hit enter] ", console, regexName,
                "Please enter a valid name (letters only, no numbers or special characters).");
        user.setName(name);

        /* Get and Set Salary */
        int salary;
        salary = getValidatedNumInput(1, -1, "Your Annual Salary? [input number only] ", console,
                "Please enter a positive number for salary. You dont have job? Skill Issue!");
        user.setAnnualSalary(salary);

        /* Get and Set Residential Status */
        String inputResident;
        inputResident = getYesOrNoInput("Are you resident of Australia? [yes/no] ", console);
        if (inputResident.equals("yes") || inputResident.equals("y")) {
            user.setResident(true);
        } else {
            user.setResident(false);
        }
    }

    public void askIncome(User user, Scanner console) {
        /*
         * IRS is coming bro....
         * better hide ur money....
         */

        /* Get Buying Price */
        int buyingPrice;

        buyingPrice = getValidatedNumInput(1, -1, "Buying price [type in positive number] : $", console,
                "Please enter a valid positive number.");
        user.setBuyingPrice(buyingPrice);

        /* Get Selling Price */
        int sellingPrice;
        /*
         * As PDF mentions
         * For this assignment, we are
         * assuming that the selling price is always more than buying price so this
         * should be checked as well, otherwise
         * an error message is shown and ask again for selling price.
         */
        sellingPrice = getValidatedNumInput(buyingPrice + 1, -1, "Selling price [type in positive number] : $", console,
                "Please enter a value greater than Buying Price");

        user.setSellingPrice(sellingPrice);

        /* Get Years held */
        int numberOfYearsHeld;
        numberOfYearsHeld = getValidatedNumInput(1, -1, "Number of years held [type in positive number] : ", console,
                "Please enter a positive number greater than zero.");
        user.setYears(numberOfYearsHeld);
    }

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

        willInvest = getYesOrNoInput("Would you like to invest? [yes/no] ", console);
        if (willInvest.equals("yes") || willInvest.equals("y")) {
            invest = true;
        }
        return invest;
    }

    public void continueInvestment(User user, Scanner console) {
        /*
         * He's scamming bro, careful!!
         * Your Savings going to the moon but upside down.
         * see the image here:
         * https://blog.bontal.net/static/1737c560fa8633cd7ee90f1bdbaf926d/9000d/hero.webp
         */

        int firstYearDeposit = 0; // I don't know should I use year1Deposit or yearOneDeposit or firstYearDeposit?
        int secondYearDeposit = 0;
        int thirdYearDeposit = 0;

        // TODO: [Very Important AF] ask professor what should we do if the previous
        // profit is less than 0.

        firstYearDeposit = getValidatedNumInput(1, user.getActualProfit(),
                "Enter initial investment amount (must be positive number and cannot exceed $" + user.getActualProfit()
                        + "): $",
                console,
                "Invalid input. Initial investment amount cannot exceed $" + user.getActualProfit() + ".");

        // Get and validate subsequent deposits
        for (int year = 2; year <= 3; year++) {
            int deposit;

            deposit = getValidatedNumInput(0, -1,
                    "Enter investment amount after year " + (year - 1) + ": $", console,
                    "Invalid input. Please enter a positive number.");
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

        coinSelection = getValidatedNumInput(1, 3, "[type 1/2/3]: ", console,
                "Invalid input. Please enter a number between 1 and 3.");

        // Set coin selection in the user object
        user.setInvestCoinSelection(coinSelection);
        System.out.println("You Selected " + getSelectedCoinName(user, user.getInvestCoinSelection()));
    }

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

    public void printCapitalGainsTax(User user) {
        System.out.println();

        System.out.println("Capital Gains Tax:");
        System.out.println("Tax Rate : " + user.getTaxRate() * 100 + "%");
        System.out.println("Capital Gains Tax : " + user.getCgt());
        System.out.println("Profit : " + user.getActualProfit());

        System.out.println();
    }

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

    public static void main(String[] args) {
        CgtInterface calc = new CgtInterface();
        calc.run();
    }
}
