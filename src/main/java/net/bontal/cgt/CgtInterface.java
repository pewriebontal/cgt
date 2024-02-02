// You can add extra methods if you think it is necessary
package net.bontal.cgt;
import java.util.*;
import net.bontal.cgt.User;

public class CgtInterface {
    public void run()
    {
        Scanner console = new Scanner (System.in);
        User user;
        
	    user = new User();
        System.out.print("name = ");
        String name = console.next();
        user.setName(name);

        int inputSelection;
        System.out.print("Coin Selection?");
        inputSelection=console.nextInt();
        user.setInvestCoinSelection(inputSelection);
        System.out.println("Selection ="+ user.getInvestCoinSelection());
    }

    public static void main(String[] args) {
        CgtInterface calc = new CgtInterface();
        calc.run();
        //System.out.println("Hello");
    }
}
