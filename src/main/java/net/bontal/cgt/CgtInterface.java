/*
*   Author: Min Thu Khaing, Thet Paing Hmu

*   Date: from 02-02-2024 to 03-02-2024
*   Description: Main entry point for CGT.
*   GitHub: @pewriebontal, @LinVulpes
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

// You can add extra methods if you think it is necessary
package net.bontal.cgt;

import java.util.*;
import net.bontal.cgt.User;

public class CgtInterface {
    public void run() {
        Scanner console = new Scanner(System.in);
        User user;

        user = new User();
        System.out.print("What's your name? [put your name hit enter] ");

        String name = console.next();
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

        // TODO:
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

        user.calculateCgt();

        System.out.println("Capital Gains Tax:");

        System.out.print("Tax Rate : ");
        System.out.println(user.getTaxRate() * 100 + "%");

        System.out.print("Capital Gains Tax : ");
        System.out.println(user.getCgt());

        System.out.print("Profit : ");
        System.out.println(user.getActualProfit());

        int inputSelection;
        System.out.println("Choose the Cryptocurrency to invest in");
        System.out.println("1 for Best Coin");
        System.out.println("2 for Simple Coin");
        System.out.println("3 for Fast Coin");
        System.out.print("[type 1/2/3] ");
        inputSelection = console.nextInt();
        user.setInvestCoinSelection(inputSelection);
        System.out.println("You Selected " + user.getInvestCoinSelection());

        printUserData(user);
    }

    public void printUserData(User user) {
        // PRINT USER DETAILS
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

        System.out.println();

        // Print Investment
        System.out.println("Crypto Currency");

        System.out.print("Buying Price : ");
        System.out.println(user.getBuyingPrice());

        System.out.print("Selling Price : ");
        System.out.println(user.getSellingPrice());

        System.out.print("Number of years held : ");
        System.out.println(user.getYears());

        System.out.println();

        // Print Capital Gain Tax
        System.out.println("Capital Gains Tax");

        System.out.print("TaxRate : ");
        System.out.println(user.getTaxRate() * 100 + "%");

        System.out.print("Capital Gains Tax : ");
        System.out.println(user.getCgt());

        System.out.print("Profit : ");
        System.out.println(user.getActualProfit());

        System.out.println();
    }

    public static void main(String[] args) {
        CgtInterface calc = new CgtInterface();
        calc.run();
        // System.out.println("Hello");
    }
}
