/*
 *   Author: Min Thu Khaing, Thet Paing Hmu
 *   Date: 21-02-2024
 *   Description: Investment Class represents an investment that the user plans to make over three years.
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

package net.bontal.cgt;

/**
 * The Investment class represents an investment that the user plans to make
 * over three years.
 * It includes information about the deposits made each year and methods for
 * calculating predicted profits
 * based on the selected coin and deposits.
 *
 * <p>
 * This class allows setting the selected coin for investment, retrieving and
 * setting deposits for each year,
 * calculating predicted profits, and getting predicted yearly and total profits
 * for each year.
 *
 * <p>
 * Copyright © 2024 Min Thu Khaing, Thet Paing Hmu. All rights reserved.
 *
 * @version 1.0
 * @since 02-02-2024
 * @author Min Thu Khaing
 * @author Thet Paing Hmu
 * @see User
 * @see CgtInterface
 */

public class Investment {
	private double year1Deposit;
	private double year2Deposit;
	private double year3Deposit;

	private int coinSelection;

	private double yearOneProfit;
	private double yearTwoProfit;
	private double yearThreeProfit;

	private double yearOneTotalProfit;
	private double yearTwoTotalProfit;
	private double yearThreeTotalProfit;

	/**
	 * Sets the selected coin for investment.
	 *
	 * @param inputSelection The selected coin for investment.
	 */
	public void setCoinSelection(int inputSelection) {
		coinSelection = inputSelection;
	}

	/**
	 * Gets the selected coin for investment.
	 *
	 * @return The selected coin for investment.
	 */
	public int getCoinSelection() {
		return (coinSelection);
	}

	/**
	 * Gets the deposit for a specific year.
	 *
	 * @param year The year for which deposit is retrieved.
	 * @return The deposit for the specified year.
	 */
	public double getDeposit(int year) {
		return switch (year) {
			case 1 -> year1Deposit;
			case 2 -> year2Deposit;
			case 3 -> year3Deposit;
			default -> throw new IllegalStateException("Unexpected value: " + year);
		};
	}

	/**
	 * Sets the deposit for a specific year.
	 *
	 * @param inputDeposit The deposit amount to set.
	 * @param year         The year for which the deposit is set.
	 */
	public void setDeposit(double inputDeposit, int year) {
		switch (year) {
			case 1 -> year1Deposit = inputDeposit;
			case 2 -> year2Deposit = inputDeposit;
			case 3 -> year3Deposit = inputDeposit;
			default -> throw new IllegalStateException("Unexpected value: " + year);
		}
	}

	/**
	 * Calculates the predicted profits for the investment based on the selected
	 * coin and deposits.
	 */
	public void calculateInvestment() {
		/*
		 * Years Yearly profit Total Profit
		 * 1 | $500* 0.15 = $75 | $75
		 * 2 | ($500 + $1000) * 0.15 = $225 | $75 + $225 = $300
		 * 3 | ($500 + $1000 + $500) * 0.15 = $300 | $75 + $225 + $300 = $600
		 */

		/*
		 * Predicted Profit for Investment in Fast Coin
		 * Years | YearlyProfit | TotalProfit
		 * ______|______________|_______________
		 * 1 | $75 | $75
		 * 2 | $225 | $300
		 * 3 | $300 | $600
		 */

		double predictedProfitRate;

		predictedProfitRate = switch (coinSelection) {
			case 1 -> 0.18;
			case 2 -> 0.12;
			case 3 -> 0.15;
			default -> throw new IllegalStateException("Unexpected value: " + coinSelection);
		};

		yearOneProfit = (year1Deposit) * predictedProfitRate;
		yearTwoProfit = (year1Deposit + year2Deposit) * predictedProfitRate;
		yearThreeProfit = (year1Deposit + year2Deposit + year3Deposit) * predictedProfitRate;

		yearOneTotalProfit = yearOneProfit;
		yearTwoTotalProfit = yearOneProfit + yearTwoProfit;
		yearThreeTotalProfit = yearOneProfit + yearTwoProfit + yearThreeProfit;
	}

	/**
	 * Gets the predicted yearly profit for a specific year.
	 *
	 * @param year The year for which the yearly profit is retrieved.
	 * @return The predicted yearly profit for the specified year.
	 */
	public double getYearlyProfit(int year) {
		return switch (year) {
			case 1 -> yearOneProfit;
			case 2 -> yearTwoProfit;
			case 3 -> yearThreeProfit;
			default -> 0.0;
		};
	}

	/**
	 * Gets the predicted total profit for a specific year.
	 *
	 * @param year The year for which the total profit is retrieved.
	 * @return The predicted total profit for the specified year.
	 */
	public double getTotalProfit(int year) {
		return switch (year) {
			case 1 -> yearOneTotalProfit;
			case 2 -> yearTwoTotalProfit;
			case 3 -> yearThreeTotalProfit;
			default -> 0.0;
		};
	}
}