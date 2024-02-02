package net.bontal.cgt;
import net.bontal.cgt.User;

// You can add extra methods if you think it is necessary

public class User {
    private String name;
    private Investment userInvestment;
    double annualSalary;
    double buyingPrice;
    double sellingPrice;
    int years;
    boolean resident;
    
 

    public User() {
        userInvestment = new Investment();
    }

    public String getName() {
        return (name);
    }

    public void setName(String inputName) {
        name = inputName;
    }

    public void setInvestCoinSelection(int inputCoin) {
        userInvestment.setCoinSelection(inputCoin);
    }

    public int getInvestCoinSelection() {
        return userInvestment.getCoinSelection();
    }

}
