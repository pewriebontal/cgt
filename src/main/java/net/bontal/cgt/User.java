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

    public User() {
        userInvestment = new Investment();
    }

    public String getName() {
        return (name);
    }

    public void setName(String inputName) {
        name = inputName;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double inputAnnualSalary) {
        annualSalary = inputAnnualSalary;
    }

    public boolean getResident() {
        return resident;
    }

    public void setResident(boolean inputResident) {
        resident = inputResident;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double inputBuyingPrice) {
        buyingPrice = inputBuyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double inputSellingPrice) {
        sellingPrice = inputSellingPrice;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int inputYears) {
        years = inputYears;
    }

    public void setInvestCoinSelection(int inputCoin) {
        userInvestment.setCoinSelection(inputCoin);
    }

    public int getInvestCoinSelection() {
        return userInvestment.getCoinSelection();
    }

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
         * Tax rates -- residents
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

    public double getDeposit(int year) {
        return userInvestment.getDeposit(year);
    }

    public void setDeposit(double inputDeposit, int year) {
        userInvestment.setDeposit(inputDeposit, year);
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getCgt() {
        return cgt;
    }

    public double getActualProfit() {
        return actualProfit;
    }

    public void calculateInvestment() {
        userInvestment.calculateInvestment();
    }

    public double getYearlyProfit(int year) {
        return userInvestment.getYearlyProfit(year);
    }

    public double getTotalProfit(int year) {
        return userInvestment.getTotalProfit(year);
    }
}
