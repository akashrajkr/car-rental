import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import static java.lang.System.exit;
import java.util.Scanner;

class RentalManager{
    public static void main(String[] args) {
            System.out.println("1.Rent a vehicle.\n2.List all the vehicles out for rent.\n3. Return a vehicle.\n4.Exit");
        Scanner scan = new Scanner(System.in);
        int ch = Integer.parseInt(scan.nextLine());
        if(ch == 4){
            System.out.println("exiting...");
            exit(0);
        }
        else if(ch==1) {
            rentVehicle();
        }
        else if(ch == 2){
            try (BufferedReader vehicleDatabase = new BufferedReader(new FileReader("vehicles.txt"))) {
                System.out.println("List of vehicles out for rent: ");
                if(vehicleDatabase.readLine() == null){
                    System.out.println("no vehicles out for rent!");
                }
                String record;
                while((record = vehicleDatabase.readLine()) != null){
                    System.out.println(record);
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
                System.out.println("file not found! exiting...");
                exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("You entered a wrong choice!");
        }
    }

    static void rentVehicle() {
        Driver driver = new Driver();
        driver.getDetails();
        if(driver.canRent()){
            System.out.println("Driver can rent");
            Vehicle v = new Vehicle();
            v.rentNow();
        } else {
            System.out.println("Driver cannot rent");
        }
    }
}
