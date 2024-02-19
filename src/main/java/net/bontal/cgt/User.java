/*
 *   Author: Min Thu Khaing, Thet Paing Hmu
 
 *   Date: from 02-02-2024 to 03-02-2024
 *   Description: Fuck object oriented
 *   GitHub: @pewriebontal, @LinVulpes
 *   © 2024 Min Thu Khaing, Thet Paing Hmu. All rights reserved.
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

package net.bontal.cgt;

/**
 * The User class represents a user who is making an investment and provides
 * methods to manage user data
 * and calculate capital gains tax (CGT) based on user inputs.
 *
 * <p>
 * The class includes information such as the user's name, annual salary,
 * residency status, investment details,
 * tax rate, CGT, and actual profit after deducting CGT.
 *
 * <p>
 * Copyright © 2024 Min Thu Khaing, Thet Paing Hmu. All rights reserved.
 *
 * @version 1.0
 * @since 02-02-2024
 * @author Min Thu Khaing
 * @author Thet Paing Hmu
 * @see CgtInterface
 * @see Investment
 */
public class User {
    private String name;
    private final Investment userInvestment;
    private double annualSalary;
    private double buyingPrice;
    private double sellingPrice;
    private int years;
    private boolean resident;

    private double taxRate;
    private double cgt;
    private double actualProfit;

    /**
     * Constructs a user object.
     */
    public User() {
        userInvestment = new Investment();
    }

    /**
     * Gets the name of the user.
     * 
     * @return The name of the user.
     */
    public String getName() {
        return (name);
    }

    /**
     * Sets the name of the user.
     * 
     * @param inputName The name of the user.
     */
    public void setName(String inputName) {
        name = inputName;
    }

    /**
     * Gets the annual salary of the user.
     * 
     * @return The annual salary of the user.
     */
    public double getAnnualSalary() {
        return annualSalary;
    }

    /**
     * Sets the annual salary of the user.
     * 
     * @param inputAnnualSalary The annual salary of the user.
     */
    public void setAnnualSalary(double inputAnnualSalary) {
        annualSalary = inputAnnualSalary;
    }

    /**
     * Gets the residency status of the user.
     * 
     * @return True if the user is a resident, false otherwise.
     */
    public boolean getResident() {
        return resident;
    }

    /**
     * Sets the residency status of the user.
     * 
     * @param inputResident True if the user is a resident, false otherwise.
     */
    public void setResident(boolean inputResident) {
        resident = inputResident;
    }

    /**
     * Gets the buying price of the investment.
     * 
     * @return The buying price of the investment.
     */
    public double getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * Sets the buying price of the investment.
     * 
     * @param inputBuyingPrice The buying price of the investment.
     */
    public void setBuyingPrice(double inputBuyingPrice) {
        buyingPrice = inputBuyingPrice;
    }

    /**
     * Gets the selling price of the investment.
     * 
     * @return The selling price of the investment.
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets the selling price of the investment.
     * 
     * @param inputSellingPrice The selling price of the investment.
     */
    public void setSellingPrice(double inputSellingPrice) {
        sellingPrice = inputSellingPrice;
    }

    /**
     * Gets the number of years the investment is held.
     * 
     * @return The number of years the investment is held.
     */
    public int getYears() {
        return years;
    }

    /**
     * Sets the number of years the investment is held.
     * 
     * @param inputYears The number of years the investment is held.
     */
    public void setYears(int inputYears) {
        years = inputYears;
    }

    /**
     * Sets the selected coin for future investment.
     * 
     * @param inputCoin The selected coin for future investment.
     */
    public void setInvestCoinSelection(int inputCoin) {
        userInvestment.setCoinSelection(inputCoin);
    }

    /**
     * Gets the selected coin for future investment.
     * 
     * @return The selected coin for future investment.
     */
    public int getInvestCoinSelection() {
        return userInvestment.getCoinSelection();
    }

    /**
     * Calculates the capital gains tax (CGT) based on user's financial information.
     */
    public void calculateCgt() {

        double profit;
        double profitForCGT;
        double totalAnnualIncome;
        taxRate = 0.0;

        // CALCULATION
        /*
         * Profit = Selling price – Buying price
         * Profit for CGT = Profit / No of years cryptocurrency is held
         * Total Annual income = Annual salary + Profit for CGT
         */

        profit = sellingPrice - buyingPrice;
        profitForCGT = profit / years;
        totalAnnualIncome = annualSalary + profitForCGT;

        /*
         * Find the tax rate for Total Annual income as Tax rate.
         * 
         * Tax rates – residents
         * $0 – $18,200 0%
         * $18,201 – $45,000 19%
         * $45,001 – $120,000 32.5%
         * $120,001 – $180,000 37%
         * Over $180,001 45%
         *
         * Tax rates – non-residents
         * $0 – $120,000 32.5%
         * $120,001 – $180,000 37%
         * Over $180,001 45%
         */
        if (resident) {
            if (totalAnnualIncome >= 0 && totalAnnualIncome <= 18200) {
                taxRate = 0;
            } else if (totalAnnualIncome >= 18201 && totalAnnualIncome <= 45000) {
                taxRate = 0.19;
            } else if (totalAnnualIncome >= 45001 && totalAnnualIncome <= 120000) {
                taxRate = 0.325;
            } else if (totalAnnualIncome >= 120001 && totalAnnualIncome <= 180000) {
                taxRate = 0.37;
            } else if (totalAnnualIncome >= 180001) {
                taxRate = 0.45;
            }
        } else {
            if (totalAnnualIncome >= 0 && totalAnnualIncome <= 120000) {
                taxRate = 0.325;
            } else if (totalAnnualIncome >= 120001 && totalAnnualIncome <= 180000) {
                taxRate = 0.37;
            } else if (totalAnnualIncome >= 180001) {
                taxRate = 0.45;
            }

        }
        /*
         * Calculation
         * CGT = Tax rate * Profit for CGT
         * Actual Profit = Profit for CGT - CGT
         */
        cgt = taxRate * profitForCGT;
        actualProfit = profitForCGT - cgt;
    }

    /**
     * Gets the deposit for a specific year from the investment.
     * 
     * @param year The year for which deposit is retrieved.
     * @return The deposit for the specified year.
     */
    public double getDeposit(int year) {
        return userInvestment.getDeposit(year);
    }

    /**
     * Sets the deposit for a specific year in the investment.
     * 
     * @param inputDeposit The deposit amount to set.
     * @param year         The year for which the deposit is set.
     */
    public void setDeposit(double inputDeposit, int year) {
        userInvestment.setDeposit(inputDeposit, year);
    }

    /**
     * Gets the tax rate applicable to the user.
     * 
     * @return The tax rate applicable to the user.
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * Gets the calculated capital gains tax (CGT) applicable to the user.
     * 
     * @return The calculated capital gains tax (CGT) applicable to the user.
     */
    public double getCgt() {
        return cgt;
    }

    /**
     * Gets the actual profit after deducting CGT.
     * 
     * @return The actual profit after deducting CGT.
     */
    public double getActualProfit() {
        return actualProfit;
    }

    /**
     * Calculates the investment based on user's inputs.
     */
    public void calculateInvestment() {
        userInvestment.calculateInvestment();
    }

    /**
     * Gets the predicted yearly profit for a specific year from the investment.
     * 
     * @param year The year for which the predicted yearly profit is retrieved.
     * @return The predicted yearly profit for the specified year.
     */
    public double getYearlyProfit(int year) {
        return userInvestment.getYearlyProfit(year);
    }

    /**
     * Gets the predicted total profit for a specific year from the investment.
     * 
     * @param year The year for which the total profit is retrieved.
     * @return The predicted total profit for the specified year.
     */
    public double getTotalProfit(int year) {
        return userInvestment.getTotalProfit(year);
    }
}
