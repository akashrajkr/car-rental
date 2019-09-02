import java.util.Calendar;
import java.util.Scanner;

import static java.lang.System.exit;
import java.util.Scanner;

class RentalManager{
    public static void main(String[] args) {
        System.out.println("1.Rent a Car\n2.Exit");
        Scanner scan = new Scanner(System.in);
        int ch = scan.nextInt();
        if(ch == 2){
            exit(0);
        }

        else{
            Driver driver = new Driver();
            driver.getDetails();
            if(!driver.canRent()){
                exit(0);
            }
        }
    }
}
