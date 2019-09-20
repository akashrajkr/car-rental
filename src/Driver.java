import java.io.*;
import static java.lang.System.exit;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class Driver {
    private String driver_name, DOB, DIN, vtype, DLtype;

    void getDetails() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the details :");
            System.out.println("Enter your name");
            driver_name = scan.nextLine();
            System.out.println("Enter your Date of Birth(dd/mm/yyyy)");
            while(true) {
                try {
                    DOB = scan.nextLine();
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate.parse(DOB, fmt);
                    System.out.println("Valid");
                    break;
                } catch (DateTimeParseException e){
                    System.out.println("Invalid date format!!!\nPlease enter in the proper date format(dd/mm/yyyy)");
                }
            }
            System.out.println("Enter your Driver Identification Number");
            DIN = scan.nextLine();
            System.out.println("What kind of licence do you own?\n1. Full\n2. Provisional");
            DLtype = scan.nextLine();
            System.out.println("What kind of vehicle do you want to rent?");
            System.out.println("1. Small car\n2. Family car\n3. Luxury car\n4. Small van\n5. Large van");
            vtype = scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean canRent() {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dob = LocalDate.parse(DOB, fmt);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(dob, now);
        int age = diff.getYears();
//        System.out.println("Age = " + diff.getYears());
        /*

    1. Small car
    2. Family car
    3. Luxury car
    4. Small van
    5. Large van
*/
        String vehicleChosen = "";
        switch (vtype) {
            case "1": vehicleChosen = "Small car";
                        break;
            case "2": vehicleChosen = "Family car";
                        break;
            case "3": vehicleChosen = "Luxury car";
                        break;
            case "4": vehicleChosen = "Small van";
                        break;
            case "5": vehicleChosen = "Large van";
                        break;
            default:
                System.out.println("Wrong input for vehicle..!");
        }
        if (Integer.parseInt(DLtype) == 1) {
            if (age < 25 && Integer.parseInt(vtype) == 5 || Integer.parseInt(vtype) == 3 ) {
                System.out.println("You must be at least 25 years of age to drive a " + vehicleChosen + ".");
                return false;
            } else if ((age < 21 && Integer.parseInt(vtype) == 2) || (Integer.parseInt(vtype) == 4)) {
                System.out.println("You must be at least 21 years of age to drive a " + vehicleChosen + ".");
                return false;
            } else if (age < 17 && Integer.parseInt(vtype) == 1) {
                System.out.println("You must be at least 17 years of age to drive a " + vehicleChosen + ".");
                return false;
            } else {
                return true;
            }
        }
        System.out.println("You must have a full licence to drive a " + vtype + ".");
        return false;
    }

    void writeInfo() {
//        Writing to the drivers.txt file
//        Driver identification number,driver name,birthdate(if you want)
        String textToAppend = String.format("%s,%s,%s,%s", DIN, driver_name, DOB,vtype);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new FileWriter("drivers.txt", true)  //Set true for append mode
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.newLine();   //Add new line
            writer.write(textToAppend);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.getMessage();
        }

    }

    void listVehicles() {
        try {
            BufferedReader vehicleDatabase = new BufferedReader(new FileReader("drivers.txt"));
            vehicleDatabase.readLine();
            System.out.println("List of vehicles out for rent: ");
            String record;
            if ((record = vehicleDatabase.readLine()) == null) {
                System.out.println("No vehicles out for rent!");
            }
            int i = 0;
            while ((record != null)) {
                try {
                    String[] details = record.split(",");
                    System.out.println(String.format("%d. %s, Make: %s", i + 1, details[4], details[5]));
                    record = vehicleDatabase.readLine();
                    i++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    record = vehicleDatabase.readLine();
                }
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!\nExiting...");
            exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String getVtype() {
        return vtype;
    }
}
