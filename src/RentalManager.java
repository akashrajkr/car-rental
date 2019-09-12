import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;

class RentalManager{
    public static void main(String[] args) {

        while(true) {
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
                try (BufferedReader vehicleDatabase = new BufferedReader(new FileReader("drivers.txt"))) {
                    vehicleDatabase.readLine(); // Reading the firstline as it is useless
                    System.out.println("List of vehicles out for rent: ");
                    String record;
                    if(vehicleDatabase.readLine() == null){
                        System.out.println("No vehicles out for rent!");
                    }

                    while (((record = vehicleDatabase.readLine()) != null)) {
                        System.out.println(record.split(",")[0]);
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
        Vehicle v = new Vehicle();
        v.returnNow();
        exit(0);
    }

    private static void rentVehicle() {
        Driver driver = new Driver();
        driver.getDetails();
        if(driver.canRent()){
            System.out.println("Driver can rent");
            Vehicle v = new Vehicle();
            v.rentNow(driver);
        } else {
            System.out.println("Driver cannot rent");
        }
    }
}
