import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

class RentalManager{
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        while(true) {
            System.out.println("1.Rent a vehicle.\n2.List all the vehicles out for rent.\n3. Return a vehicle.\n4.Exit");
            int ch = Integer.parseInt(scan.nextLine());
            if(ch == 4){
                System.out.println("exiting...");
                exit(0);
            }
            else if(ch==1) {
                rentVehicle();
            }
            else if(ch == 2){
                try (BufferedReader vehicleDatabase = new BufferedReader(new FileReader("drivers.txt"))) {
                    vehicleDatabase.readLine(); // Reading the firstline as it is useless
                    System.out.println("List of vehicles out for rent: ");
                    String record;
                    if(vehicleDatabase.readLine() == null){
                        System.out.println("No vehicles out for rent!");
                    }
                    int i = 0;
                    while (((record = vehicleDatabase.readLine()) != null)) {
                        String[] details = record.split(",");
                        System.out.println(String.format("%d. %s, make: %s", i, details[4], details[5]));
                        i++;
                    }
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                    System.out.println("file not found! exiting...");
                    exit(0);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            } else if(ch == 3) {
                returnVehicle();
                break;
            } else {
                System.out.println("Wrong choice...Enter again.");
            }
        }
    }

    private static void returnVehicle() {
        String regNo = "";
        System.out.println("Enter the Driver Identification Number : ");
        regNo = scan.nextLine();

        exit(0);
    }

    private static void rentVehicle() {
        Driver driver = new Driver();
        driver.getDetails();
        if(driver.canRent()){
            System.out.println("Driver can rent");
            boolean booked = false;
            while (!booked)
            {
                int choice = Integer.parseInt(driver.getVtype());
                switch (choice) {
                    case 1:
                        Small_car small_car = new Small_car();
                        small_car.rentNow(driver);
                        booked = true;
                        break;
                    case 2:
                        Family_car family_car = new Family_car();
                        family_car.rentNow(driver);
                        booked = true;
                        break;
                    case 3:
                        Luxury_car luxury_car = new Luxury_car();
                        luxury_car.rentNow(driver);
                        booked = true;
                        break;
                    case 4:
                        Small_Van small_van = new Small_Van();
                        small_van.rentNow(driver);
                        booked = true;
                        break;
                    case 5:
                        Large_Van large_van = new Large_Van();
                        large_van.rentNow(driver);
                        booked = true;
                        break;
                    default:
                        System.out.println("Wrong choice!");
                }
            }
        } else {
            System.out.println("Driver cannot rent");
        }
    }
}
