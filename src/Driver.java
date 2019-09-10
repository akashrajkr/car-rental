import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Driver {
    private String driver_name, DOB, DIN, vtype, DLtype;
    private Date dob;

    public void getDetails() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the details :");
            System.out.println("Enter your name");
            driver_name = scan.nextLine();
            System.out.println("Enter your Date of Birth(dd/mm/yyyy)");
            DOB = scan.nextLine();
            dob = new SimpleDateFormat("dd/MM/yyyy").parse(DOB);
            System.out.println(dob);
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

    public boolean canRent() {
        String[] dob_split = DOB.split("/");
        int[] birthdate = new int[dob_split.length];

        for (int i = 0; i < birthdate.length; i++) {
            birthdate[i] = Integer.parseInt(dob_split[i]);
        }
        Calendar cal = Calendar.getInstance();
        int age = cal.getWeekYear() - birthdate[2];

        if (age == 17 || age == 21 || age == 25) {
            if (cal.get(cal.MONTH) < birthdate[1])
                age--;
            else if (cal.get(cal.MONTH) == birthdate[1]) {
                if (cal.get(cal.DATE) < birthdate[0])
                    age--;
            }
        }
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

    public void writeInfo() {
//        Writing to the drivers.txt file
//        Driver identification number,driver name,birthdate(if you want)

    }
}
