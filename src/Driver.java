import java.util.Calendar;
import java.util.Scanner;

public class Driver {
    private String driver_name, DOB, DIN, vtype, DLtype;

    public void getDetails() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the details :");
            System.out.println("Enter your name");
            driver_name = scan.nextLine();
            System.out.println("Enter your Date of Birth(dd/mm/yyyy)");
            DOB = scan.nextLine();
            System.out.println("Enter your Driver Identification Number");
            DIN = scan.nextLine();
            System.out.println("What kind of licence do you own?(Full/Provisional)");
            DLtype = scan.nextLine();
            System.out.println("What kind of vehicle do you want to rent?");
            System.out.println("Small car\nFamily car\nLuxury car\nSmall van\nLarge van");
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

        if (DLtype.equalsIgnoreCase("full")) {
            if (age < 25 && (vtype.equalsIgnoreCase("Large van") || vtype.equalsIgnoreCase("Luxury car"))) {
                System.out.println("You must be at least 25 years of age to drive a " + vtype + ".");
                return false;
            } else if (age < 21 && (vtype.equalsIgnoreCase("Family car") || vtype.equalsIgnoreCase("Small van"))) {
                System.out.println("You must be at least 21 years of age to drive a " + vtype + ".");
                return false;
            } else if (age < 17 && vtype.equalsIgnoreCase("Small car")) {
                System.out.println("You must be at least 17 years of age to drive a " + vtype + ".");
                return false;
            } else {
                return true;
            }
        }

        System.out.println("You must have a full licence to drive a " + vtype + ".");
        return false;

    }
}
