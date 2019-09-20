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
                new Driver().listVehicles();
            } else if(ch == 3) {
                returnVehicle();
                break;
            } else {
                System.out.println("Wrong choice...Enter again.");
            }
        }
    }

    private static void returnVehicle() {
        String DIN;
        String [] details;
        String currLine;
        int vtype;
        try {
            System.out.println("Enter the Driver Identification Number : ");
            DIN = scan.nextLine();
//            Check if the driver has taken any vehicle out for rent
            currLine = new Vehicle().findDriver(DIN);
            if(currLine != null){
                details = currLine.split(",");
                vtype = Integer.parseInt(details[3]);
            } else return;
            switch (vtype) {
                case 1:
                    Small_car small_car = new Small_car();
                    small_car.returnNow(currLine);
                    break;
                case 2:
                    Family_car family_car = new Family_car();
                    family_car.returnNow(currLine);
                    break;
                case 3:
                    Luxury_car luxury_car = new Luxury_car();
                    luxury_car.returnNow(currLine);
                    break;
                case 4:
                    Small_Van small_van = new Small_Van();
                    small_van.returnNow(currLine);
                    break;
                case 5:
                    Large_Van large_van = new Large_Van();
                    large_van.returnNow(currLine);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
