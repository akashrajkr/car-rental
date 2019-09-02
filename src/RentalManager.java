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
            Vehicle newVehicle = new Vehicle();
            newVehicle.rentVehicle();
        } else if(ch == 2){
            try (BufferedReader vehicleDatabase = new BufferedReader(new FileReader("~/IdeaProjects/car-rental/src/vehicles.txt"))) {
                System.out.println(vehicleDatabase.readLine());
            } catch (FileNotFoundException e){
                e.printStackTrace();
                System.out.println("file not found! exiting...");
                exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
