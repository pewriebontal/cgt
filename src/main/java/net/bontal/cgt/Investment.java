/*
 *   Author: Min Thu Khaing, Thet Paing Hmu
 
 *   Date: from 02-02-2024 to 03-02-2024
 *   Description: Investment Class.
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

// You can add extra methods if you think it is necessary
public class Investment {
	private double year1Deposit;
	private double year2Deposit;
	private double year3Deposit;

	int coinSelection;

	private double yearOneProfit;
	private double yearTwoProfit;
	private double yearThreeProfit;

	private double yearOneTotalProfit;

	private double yearTwoTotalProfit;
	private double yearThreeTotalProfit;

	// constructor
	public Investment() {

	}

	public void setCoinSelection(int inputSelection) {
		coinSelection = inputSelection;
	}

	public int getCoinSelection() {
		return (coinSelection);
	}

	public double getYear1Deposit() {
		return year1Deposit;
	}

	public double getYear2Deposit() {
		return year2Deposit;
	}

	public double getYear3Deposit() {
		return year3Deposit;
	}

	public void setYear1Deposit(int inputYear1Deposit) {
		year1Deposit = inputYear1Deposit;
	}

	public void setYear2Deposit(int inputYear2Deposit) {
		year2Deposit = inputYear2Deposit;
	}

	public void setYear3Deposit(int inputYear3Deposit) {
		year3Deposit = inputYear3Deposit;
	}

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
		 * _________|___________________|_______________
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

	public double getYearOneProfit() {
		return yearOneProfit;
	}

	public double getYearTwoProfit() {
		return yearTwoProfit;
	}

	public double getYearThreeProfit() {
		return yearThreeProfit;
	}

	public double getYearOneTotalProfit() {
		return yearOneTotalProfit;
	}

	public double getYearTwoTotalProfit() {
		return yearTwoTotalProfit;
	}

	public double getYearThreeTotalProfit() {
		return yearThreeTotalProfit;
	}
}