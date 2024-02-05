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
import net.bontal.cgt.User;

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

    public void askUserDetails(User user, Scanner console) {
        System.out.print("What's your name? [put your name hit enter] ");

        String name = "";
        name = console.nextLine(); // using Scanner.nextLine() to accept
        /* space in Full Name, for example. "Natalia Galileo Oreo" */
        user.setName(name);

        int salary;
        System.out.print("Your Annual Salary? [input number only] ");
        salary = console.nextInt();
        user.setAnnualSalary(salary);

        String inputResident;
        System.out.print("Are you resident of Australia? [yes/no] ");

        inputResident = console.next();
        if (inputResident.equals("y") || inputResident.equals("yes")) {
            user.setResident(true);
        } else if (inputResident.equals("n") || inputResident.equals("no")) {
            user.setResident(false);
        }
    }

    public void askIncome(User user, Scanner console) {
        /*
         * IRS is coming bro....
         * better hide ur money....
         */
        int buyingPrice;
        System.out.print("Buying price [type in positive number] : $");
        buyingPrice = console.nextInt();
        user.setBuyingPrice(buyingPrice);

        int sellingPrice;
        System.out.print("Selling price [type in positive number] : $");
        sellingPrice = console.nextInt();
        user.setSellingPrice(sellingPrice);

        int numberOfYearsHeld;
        System.out.print("Number of years held [type in positive number] : ");
        numberOfYearsHeld = console.nextInt();
        user.setYears(numberOfYearsHeld);
    }

    public boolean askToInvest(User user, Scanner console) {
        /*
         * I really want to set function name ScamTheUser()
         * bro just buy the land in Third World countries
         * (like in SEA countries like Burma or African Countries.)
         * you're sure going to be rich already
         * if not ur grandchildren will.
         */
        String willInvest;
        System.out.println("Would you like to invest? [yes/no]");
        willInvest = console.next();

        if (willInvest.equals("yes") || willInvest.equals("y")) {
            return true;
        } else if (willInvest.equals("no") || willInvest.equals("n")) {
            return false;
        } else
            return false;
    }

    public void continueInvestment(User user, Scanner console) {
        /*
         * He's scamming bro careful!!
         * Your Savings going to moon but upside down.
         * see the image here :
         * https://blog.bontal.net/static/1737c560fa8633cd7ee90f1bdbaf926d/9000d/hero.webp
         */

        int firstYearDeposit; // I don't know should I use year1Deposit or yearOneDeposit or firstYearDeposit?
        int secondYearDeposit;
        int thirdYearDeposit;

        System.out.print("Initial Investment Amount (cannot be more than $" + user.getActualProfit() + "): $");
        firstYearDeposit = console.nextInt();
        user.setYearOneDeposit(firstYearDeposit);

        System.out.print("Investment Amount after First year: $");
        secondYearDeposit = console.nextInt();
        user.setYearTwoDeposit(secondYearDeposit);

        System.out.print("Investment Amount after Second year: $");
        thirdYearDeposit = console.nextInt();
        user.setYearThreeDeposit(thirdYearDeposit);

        int inputSelection;
        System.out.println("Choose the Cryptocurrency to invest in");
        System.out.println("1 for Best Coin (predicted profit rates 18%)");
        System.out.println("2 for Simple Coin (predicted profit rates 12%)");
        System.out.println("3 for Fast Coin (predicted profit rates 15%)");
        System.out.print("[type 1/2/3] ");
        inputSelection = console.nextInt();

        user.setInvestCoinSelection(inputSelection);
        System.out.println("You Selected " + user.getInvestCoinSelection());
    }

    public void printPredictedProfitForInvestment(User user) {
        /*
         * This function print Predicted Profit Table in a nice way.
         */

        String coin = switch (user.getInvestCoinSelection()) {
            case 1 -> "BestCoin";
            case 2 -> "SimpleCoin";
            case 3 -> "FastCoin";
            default -> throw new IllegalStateException("Unexpected value: " + user.getInvestCoinSelection());
        };

        System.out.println("Predicted Profit for Investment in " + coin);
        System.out.println("Years \t|\tYearlyProfit \t|\tTotalProfit");
        System.out.println("________|___________________|_______________");
        System.out.println("1 \t\t|\t$" + user.getYearOneProfit() + " \t\t\t|\t$" + user.getYearOneTotalProfit());
        System.out.println("2 \t\t|\t$" + user.getYearTwoProfit() + " \t\t\t|\t$" + user.getYearTwoTotalProfit());
        System.out.println("3 \t\t|\t$" + user.getYearThreeProfit() + " \t\t\t|\t$" + user.getYearThreeTotalProfit());

    }

    public void printCapitalGainsTax(User user) {
        System.out.println();
        System.out.println("Capital Gains Tax:");

        System.out.print("Tax Rate : ");
        System.out.println(user.getTaxRate() * 100 + "%");

        System.out.print("Capital Gains Tax : ");
        System.out.println(user.getCgt());

        System.out.print("Profit : ");
        System.out.println(user.getActualProfit());
        System.out.println();
    }

    public void printUserData(User user, boolean willInvest) {

        /* print all the available User data in the final */
        /* User Details */
        System.out.println();
        System.out.println("User Details");

        System.out.print("Name : ");
        System.out.println(user.getName());

        System.out.print("Annual Salary : ");
        System.out.println(user.getAnnualSalary());

        System.out.print("Residential Status : ");
        if (user.getResident())
            System.out.println("Yes");
        else
            System.out.println("No");
        /* END USER DETAILS */

        System.out.println();

        /* PRINT INVESTMENT */
        System.out.println("Crypto Currency");

        System.out.print("Buying Price : ");
        System.out.println(user.getBuyingPrice());

        System.out.print("Selling Price : ");
        System.out.println(user.getSellingPrice());

        System.out.print("Number of years held : ");
        System.out.println(user.getYears());

        printCapitalGainsTax(user);

        if (willInvest)
            printPredictedProfitForInvestment(user);
    }

    public static void main(String[] args) {
        CgtInterface calc = new CgtInterface();
        calc.run();
    }
}
