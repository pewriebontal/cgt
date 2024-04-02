/*
 *   Author: Min Thu Khaing, Thet Paing Hmu
 *   Date: 29-03-2024
 *   Description: The User class represents a user who is making an investment and provides methods to manage user data
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
import java.util.ArrayList;
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
 * @version 2.0
 * @since 02-02-2024
 * @author Min Thu Khaing
 * @author Thet Paing Hmu
 * @see CgtInterface
 * @see Investment
 */
public class User {
    private String name;
    private double annualSalary;
    private boolean resident;

    private double buyingPrice;
    private double sellingPrice;
    private int yearsHold;

    private double taxRate;
    private double cgt;
    private double actualProfit;

    private double availableBalance;

    private final Investment[] investmentAccount;
    private int numberOfAccounts;
    private ArrayList<Investment> investmentAccounts;
    /**
     * Default constructor for the User class.
     */
    public User() {
        this.name = "";
        this.annualSalary = 0.0;
        this.buyingPrice = 0.0;
        this.sellingPrice = 0.0;
        this.yearsHold = 0;
        this.resident = false;
        this.numberOfAccounts = 0;
        this.availableBalance = 0.0;
        this.investmentAccount = new Investment[2];
        this.investmentAccounts = new ArrayList<>();
    }

    /**
     * Parameterized constructor for the User class.
     *
     * @param inputName         the name of the user
     * @param inputSalary       the annual salary of the user
     * @param inputResident     the residency status of the user
     * @param inputBuyingPrice  the buying price of the investment
     * @param inputSellingPrice the selling price of the investment
     * @param inputYearHold     the number of years the user holds the investment
     */
    public User(String inputName, double inputSalary, boolean inputResident, double inputBuyingPrice,
            double inputSellingPrice,
            int inputYearHold) {
        this.name = inputName;
        this.annualSalary = inputSalary;
        this.resident = inputResident;

        this.buyingPrice = inputBuyingPrice;
        this.sellingPrice = inputSellingPrice;
        this.yearsHold = inputYearHold;

        this.numberOfAccounts = 0;
        this.investmentAccount = new Investment[2];
        this.investmentAccounts = new ArrayList<>();
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the user.
     *
     * @param inputName The name of the user.
     */
    public void setName(String inputName) {
        this.name = inputName;
    }

    /**
     * Gets the annual salary of the user.
     *
     * @return The annual salary of the user.
     */
    public double getAnnualSalary() {
        return this.annualSalary;
    }

    /**
     * Sets the annual salary of the user.
     *
     * @param inputAnnualSalary The annual salary of the user.
     */
    public void setAnnualSalary(double inputAnnualSalary) {
        this.annualSalary = inputAnnualSalary;
    }

    /**
     * Gets the residency status of the user.
     *
     * @return The residency status of the user. (true if resident, false if
     *         non-resident)
     */
    public boolean getResident() {
        return this.resident;
    }

    /**
     * Sets the residency status of the user.
     *
     * @param inputResident The residency status of the user. (true if resident,
     *                      false if non-resident)
     */
    public void setResident(boolean inputResident) {
        this.resident = inputResident;
    }

    /**
     * Gets the buying price of the investment.
     *
     * @return The buying price of the investment.
     */
    public double getBuyingPrice() {
        return this.buyingPrice;
    }

    /**
     * Sets the buying price of the investment.
     *
     * @param inputBuyingPrice The buying price of the investment.
     */
    public void setBuyingPrice(double inputBuyingPrice) {
        this.buyingPrice = inputBuyingPrice;
    }

    /**
     * Gets the selling price of the investment.
     *
     * @return The selling price of the investment.
     */
    public double getSellingPrice() {
        return this.sellingPrice;
    }

    /**
     * Sets the selling price of the investment.
     *
     * @param inputSellingPrice The selling price of the investment.
     */
    public void setSellingPrice(double inputSellingPrice) {
        this.sellingPrice = inputSellingPrice;
    }

    /**
     * Gets the number of years the investment is held.
     *
     * @return The number of years the investment is held.
     */
    public int getYearsHold() {
        return this.yearsHold;
    }

    /**
     * Sets the number of years the investment is held.
     *
     * @param inputYearHold The number of years the investment is held.
     */
    public void setYearsHold(int inputYearHold) {
        this.yearsHold = inputYearHold;
    }

    /**
     * Gets the tax rate applicable to the user.
     *
     * @return The tax rate applicable to the user.
     */
    public double getTaxRate() {
        return this.taxRate;
    }

    /**
     * Gets the calculated capital gains tax (CGT) applicable to the user.
     *
     * @return The calculated capital gains tax (CGT) applicable to the user.
     */
    public double getCgt() {
        return this.cgt;
    }

    /**
     * Gets the actual profit after deducting CGT.
     *
     * @return The actual profit after deducting CGT.
     */
    public double getActualProfit() {
        return this.actualProfit;
    }

    /**
     * Gets the available balance after deducting investmented amounts.
     *
     * @return The available balance after deducting investmented amounts.
     */
    public double getAvailableBalance() {
        return this.availableBalance;
    }

    /**
     * Gets the investment account at the specified index.
     * 
     * @param index The index of the investment account.
     * @return The investment account at the specified index.
     */

    public Investment getInvestmentAccount(int index) {
        return this.investmentAccounts.get(index);
    }
    /**
     * Gets the number of investment accounts.
     *
     * @return The number of investment accounts.
     */
    public int getNumberOfAccounts() {
        return this.numberOfAccounts;
    }

    /* ======================================== */

    /**
     * Gets the deposit for a specific year from the investment.
     *
     * @param year The year for which deposit is retrieved.
     * @return The deposit for the specified year.
     */
    public double getDeposit(int year, int ivIndex) {
        return this.investmentAccount[ivIndex].getDeposit(year);
    }

    /**
     * Sets the deposit for a specific year in the investment.
     *
     * @param inputDeposit The deposit amount to set.
     * @param year         The year for which the deposit is set.
     */
    public void setDeposit(double inputDeposit, int year, int ivIndex) {
        this.investmentAccount[ivIndex].setDeposit(inputDeposit, year);
    }

    /**
     * Gets the selected coin for future investment.
     *
     * @return The selected coin for future investment.
     */
    public int getInvestCoinSelection(int ivIndex) {
        return this.investmentAccount[ivIndex].getCoinSelection();
    }

    /**
     * Sets the selected coin for future investment.
     *
     * @param inputCoin The selected coin for future investment.
     */
    public void setInvestCoinSelection(int inputCoin, int ivIndex) {
        this.investmentAccount[ivIndex].setCoinSelection(inputCoin);
    }

    /**
     * Gets the predicted yearly profit for a specific year from the investment.
     *
     * @param year The year for which the predicted yearly profit is retrieved.
     * @return The predicted yearly profit for the specified year.
     */
    public double getYearlyProfit(int year, int ivIndex) {
        return this.investmentAccount[ivIndex].getYearlyProfit(year);
    }

    /**
     * Gets the predicted total profit for a specific year from the investment.
     *
     * @param year The year for which the total profit is retrieved.
     * @return The predicted total profit for the specified year.
     */
    public double getTotalProfit(int year, int ivIndex) {
        return this.investmentAccount[ivIndex].getTotalProfit(year);
    }

    //////////////////////////////////////////

    /**
     * Adds a new investment account to the user.
     * 
     * @param newInvestment The new investment account to add.
     */
    public void addNewInvestmentAccount(Investment newInvestment) {
        if (numberOfAccounts < 2) {
            this.investmentAccounts.add(newInvestment);
            this.investmentAccount[numberOfAccounts] = newInvestment;
            this.availableBalance = availableBalance - investmentAccount[numberOfAccounts].getDeposit(1);
            System.out.println("DEBUG:" + investmentAccount[numberOfAccounts].getDeposit(1));
            this.numberOfAccounts++;
        } else {
            System.out.println("Maximum number of investment accounts reached.");
        }
    }

    /**
     * Deletes an investment account from the user.
     * 
     * @param ivIndex The index of the investment account to delete.
     * @return 0 if the account is deleted successfully, -1 if the account is not
     *         found.
     */
    public int deleteInvestmentAccount(int ivIndex) {
        if (ivIndex < numberOfAccounts) {
            for (int i = ivIndex; i < numberOfAccounts - 1; i++) {
                this.investmentAccount[i] = investmentAccount[i + 1];
            }
            this.numberOfAccounts--;
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Calculates the capital gains tax (CGT) based on user's financial information.
     */
    public void calculateCgt() {

        double profit;
        double profitForCGT;
        double totalAnnualIncome;
        this.taxRate = 0.0;

        // CALCULATION
        /*
         * Profit = Selling price – Buying price
         * Profit for CGT = Profit / No of years cryptocurrency is held
         * Total Annual income = Annual salary + Profit for CGT
         */
        profit = sellingPrice - buyingPrice;
        profitForCGT = profit / yearsHold;
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
                this.taxRate = 0;
            } else if (totalAnnualIncome >= 18201 && totalAnnualIncome <= 45000) {
                this.taxRate = 0.19;
            } else if (totalAnnualIncome >= 45001 && totalAnnualIncome <= 120000) {
                this.taxRate = 0.325;
            } else if (totalAnnualIncome >= 120001 && totalAnnualIncome <= 180000) {
                this.taxRate = 0.37;
            } else if (totalAnnualIncome >= 180001) {
                this.taxRate = 0.45;
            }
        } else {
            if (totalAnnualIncome >= 0 && totalAnnualIncome <= 120000) {
                this.taxRate = 0.325;
            } else if (totalAnnualIncome >= 120001 && totalAnnualIncome <= 180000) {
                this.taxRate = 0.37;
            } else if (totalAnnualIncome >= 180001) {
                this.taxRate = 0.45;
            }

        }
        /*
         * Calculation
         * CGT = Tax rate * Profit for CGT
         * Actual Profit = Profit for CGT - CGT
         */
        this.cgt = taxRate * profitForCGT;
        this.actualProfit = profitForCGT - cgt;

        this.availableBalance = actualProfit;
    }

    /**
     * Calculates the predicted profits for the investment based on the selected
     * coin
     * and deposits.
     */
    public void calculateInvestment() {
        for (int i = 0; i < numberOfAccounts; i++) {
            this.investmentAccount[i].calculateInvestmentProfits();
        }
    }

    /**
     * Calculates the available balance after deducting investmented amounts.
     */
    public void calculateAvailableBalance() {
        double investedAmmount = 0;
        for (int i = 0; i < numberOfAccounts; i++) {
            investedAmmount += investmentAccount[i].getDeposit(1);
        }
        this.availableBalance = (actualProfit - investedAmmount);
    }

}
