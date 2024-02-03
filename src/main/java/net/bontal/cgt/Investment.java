/*
 *   Author: Min Thu Khaing, Thet Paing Hmu
 
 *   Date: from 02-02-2024 to 03-02-2024
 *   Description: Investment Class.
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

package net.bontal.cgt;

// You can add extra methods if you think it is necessary
public class Investment {
	private double year1Deposit;
	private double year2Deposit;
	private double year3Deposit;
	int coinSelection;

	// constructor
	public Investment() {

	}

	public void setCoinSelection(int inputSelection) {
		coinSelection = inputSelection;
	}

	public int getCoinSelection() {
		return (coinSelection);
	}

}
