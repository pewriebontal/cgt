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

    private boolean isNotValidName(String name, boolean isElonMusksSon) {
        /*
         * Sir, You mentioned earlier in class with ancient headstone example
         * we need to consider for the future.
         * I'm letting numbers in name just in case.
         */
        if (isElonMusksSon)
            return !name.matches("[a-zA-Z0-9 ]+");
        else
            return !name.matches("[a-zA-Z ]+");
    }

    private boolean isValidYesOrNo(String string) {
        return (string.equalsIgnoreCase("y") || string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("n")
                || string.equalsIgnoreCase("no"));
    }

    public void askUserDetails(User user, Scanner console) {
        boolean isElonMusksSon;

        isElonMusksSon = false;
        isElonMusksSon = true;

        /* Get Name */
        String name;
        name = "";

        do {
            System.out.print("What's your name? [put your name hit enter] ");
            name = console.nextLine(); // using Scanner.nextLine() to accept
            /* space in Full Name, for example. "Natalia Galileo Oreo" */
            /*
             * also accepting numbers in name just in case Elon's Son wants to use this
             * system.
             */
            if (isNotValidName(name, isElonMusksSon)) {
                System.out.println("Please enter a valid name (letters only, no numbers or special characters).");
            }

        } while (isNotValidName(name, isElonMusksSon));
        user.setName(name);

        /* Get Salary */
        int salary;

        do {
            System.out.print("Your Annual Salary? [input number only] ");
            while (!console.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                System.out.print("Your Annual Salary? [input number only] ");
                console.next();
            }
            salary = console.nextInt();

            if (salary <= 0) {
                System.out.println("Please enter a positive number for salary. You dont have job? Skill Issue!");
            }

        } while (salary <= 0);

        user.setAnnualSalary(salary);

        /* Get Residential Status */
        String inputResident;

        do {
            System.out.print("Are you resident of Australia? [yes/no] ");
            inputResident = console.next();
            if (isValidYesOrNo(inputResident)) {
                user.setResident(inputResident.equalsIgnoreCase("y") || inputResident.equalsIgnoreCase("yes"));
            } else {
                System.out.println("Invalid input. Please enter 'yes', 'no', 'y', or 'n'.");
            }
        } while (!(isValidYesOrNo(inputResident)));
    }

    public void askIncome(User user, Scanner console) {
        /*
         * IRS is coming bro....
         * better hide ur money....
         */

        /* Get Buying Price */
        int buyingPrice;
        do {
            System.out.print("Buying price [type in positive number] : $");
            while (!console.hasNextInt()) {
                System.out.println("Please enter a valid positive number.");
                System.out.print("Buying price [type in positive number] : $");
                console.next();
            }
            buyingPrice = console.nextInt();

            if (buyingPrice <= 0) {
                System.out.println("Please enter a valid positive number.");
            }
        } while (buyingPrice <= 0);
        user.setBuyingPrice(buyingPrice);

        /* Get Selling Price */
        int sellingPrice;
        do {
            System.out.print("Selling price [type in positive number] : $");
            while (!console.hasNextInt()) {
                System.out.println("Please enter a valid positive number.");
                System.out.print("Selling price [type in positive number] : $");
                console.next();
            }
            sellingPrice = console.nextInt();
            if (sellingPrice <= buyingPrice) {
                /*
                 * As PDF mentions
                 * For this assignment, we are
                 * assuming that the selling price is always more than buying price so this
                 * should be checked as well, otherwise
                 * an error message is shown and ask again for selling price.
                 */
                System.out.println("Please enter a value greater than Buying Price");
            }
        } while (sellingPrice <= buyingPrice);
        user.setSellingPrice(sellingPrice);

        /* Get Years held */
        int numberOfYearsHeld;
        do {
            System.out.print("Number of years held [type in positive number] : ");
            while (!console.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                System.out.print("Number of years held [type in positive number] :");
                console.next();
            }
            numberOfYearsHeld = console.nextInt();

            if (numberOfYearsHeld <= 0) {
                System.out.println("Please enter a positive number.");
            }
        } while (numberOfYearsHeld <= 0);
        user.setYears(numberOfYearsHeld);
    }

    public boolean askToInvest(User user, Scanner console) {
        /*
         * I really want to set function name ScamTheUser()
         * bro just buy the land in Third World countries like in
         * (SEA countries like Burma or African Countries.)
         * you're sure going to be rich already
         * if not ur grandchildren will.
         */
        boolean invest = false;
        String willInvest;
        do {
            System.out.println("Would you like to invest? [yes/no]");
            willInvest = console.next();
            if (isValidYesOrNo(willInvest)) {
                invest = willInvest.equalsIgnoreCase("yes") || willInvest.equalsIgnoreCase("y");
            } else {
                System.out.println("Invalid input. Please enter 'yes', 'no', 'y', or 'n'.");
            }
        } while (!isValidYesOrNo(willInvest));
        return invest;
    }

    public void continueInvestment(User user, Scanner console) {
        /*
         * He's scamming bro, careful!!
         * Your Savings going to the moon but upside down.
         * see the image here:
         * https://blog.bontal.net/static/1737c560fa8633cd7ee90f1bdbaf926d/9000d/hero.webp
         */

        int firstYearDeposit; // I don't know should I use year1Deposit or yearOneDeposit or firstYearDeposit?
        int secondYearDeposit;
        int thirdYearDeposit;

        /* Get and Set First Year Deposit */
        do {
            System.out.print("Initial Investment Amount (cannot be more than $" + user.getActualProfit() + "): $");
            while (!console.hasNextInt()) {
                System.out.println("Please enter valid positive number.");
                console.next();
            }
            firstYearDeposit = console.nextInt();
            if (firstYearDeposit > user.getActualProfit()) {
                System.out.println("Amount cannot be more than" + user.getActualProfit() + "): $");
            }
        } while (firstYearDeposit > user.getActualProfit());
        user.setDeposit(firstYearDeposit, 1);

        /* Get and Set Second Year Deposit */
        do {
            System.out.print("Investment Amount after First year: $");
            while (!console.hasNextInt()) {
                System.out.println("Please enter valid positive number.");
                console.next();
            }
            secondYearDeposit = console.nextInt();
            if (secondYearDeposit <= 0) {
                System.out.println("Please enter valid positive number.");
            }
        } while (secondYearDeposit <= 0);
        user.setDeposit(secondYearDeposit, 2);

        /* Get and Set Third Year Deposit */
        do {
            System.out.print("Investment Amount after Second year: $");
            while (!console.hasNextInt()) {
                System.out.println("Please enter valid positive number.");
                console.next();
            }
            thirdYearDeposit = console.nextInt();
            if (thirdYearDeposit <= 0) {
                System.out.println("Please enter valid positive number.");
            }
        } while (thirdYearDeposit <= 0);
        user.setDeposit(thirdYearDeposit, 3);

        /* Get and Set Coin Selection */
        int inputSelection;
        System.out.println("Choose the Cryptocurrency to invest in");
        System.out.println("1 for Best Coin (predicted profit rates 18%)");
        System.out.println("2 for Simple Coin (predicted profit rates 12%)");
        System.out.println("3 for Fast Coin (predicted profit rates 15%)");
        do {
            System.out.print("[type 1/2/3] ");
            while (!console.hasNextInt()) {
                System.out.println("Please enter valid number.");
                System.out.print("[type 1/2/3] ");
                console.next();
            }
            inputSelection = console.nextInt();
            if (!(inputSelection > 0 && inputSelection <= 3)) {
                System.out.println("Please choose number between 1 to 3");
            }
        } while (!(inputSelection > 0 && inputSelection <= 3));
        user.setInvestCoinSelection(inputSelection);
        System.out.println("You Selected " + getSelectedCoin(user, user.getInvestCoinSelection()));
    }

    public String getSelectedCoin(User user, int selected) {
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
                .println("Predicted Profit for Investment in " + getSelectedCoin(user, user.getInvestCoinSelection()));
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
