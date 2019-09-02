import java.util.Scanner;

import static java.lang.System.exit;


public class Main {

    public static void main(String[] args) {
        System.out.println("1.Rent a Car\n2.Exit");
        Scanner scan = new Scanner(System.in);
        int ch = scan.nextInt();
        if(ch == 2){
            exit(0);
        }

        else{
            RentalManager rm = new RentalManager();
            rm.getDetails();
            if(!rm.canRent()){
                exit(0);
            }
        }
    }
}
