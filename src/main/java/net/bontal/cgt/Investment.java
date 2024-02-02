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
