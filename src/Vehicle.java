import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

class Vehicle {
    private Date startDate, endDate;

    //    localDateTime objects for adding days to Date objects
    private LocalDate lStartDate, lEndDate;

    void rentNow() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Vehicle required from (press <Enter> to start from today)  (dd/mm/yyyy) : ");
        String date = scan.nextLine();
        if (date.isEmpty()) {
            startDate = new Date();
            lStartDate = LocalDate.now();
        } else {
            try {
                startDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                lStartDate = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Parse error");
            }
        }
        while (true) {
            System.out.println("Enter your Date of Return(dd/mm/yyyy) : ");
            date = scan.nextLine();
            try {
                endDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                lEndDate = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Start date: " + startDate);
            System.out.println("Return date: " + endDate);
            if (endDate.before(startDate)) {
                System.out.println("Invalid return date!");
            } else {
                break;
            }
        }
//      Writing everything to a file now
        writeInfo();
    }

    private void writeInfo() {
//        Writing vehicle info into a file for further processing in the format
//        registration number,  make, startDate, endDate, driverIdentifictionNumber
        String tempRegNumber;
        String tempMake;
        Random rnd = new Random();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int stateCode = 100;
        while (stateCode > 54) {
            stateCode = Integer.parseInt(rnd.nextInt(10) + rnd.nextInt(10) + "");
        }
        tempRegNumber = "KA-" + stateCode + " " + (char) (rnd.nextInt(26) + 'A') +
                (char) (rnd.nextInt(26) + 'A') + "-" + rnd.nextInt(10) + rnd.nextInt(10) +
                rnd.nextInt(10) + rnd.nextInt(10);
        tempMake = rnd.nextInt(15) + 2005 + "";
        System.out.println("Registration number " + tempRegNumber + "\nMake : " + tempMake);

        String textToAppend = String.format(",%s,%s,%s,%s", tempRegNumber, tempMake, lStartDate.format(fmt), lEndDate.format(fmt));
//        lStartDate = LocalDate.ofInstant(startDate.toInstant(),  ZoneId.systemDefault());  Might help while reading the date format from txt file

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new FileWriter("drivers.txt", true)  //Set true for append mode
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (writer != null) {
                writer.write(textToAppend);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


     void returnNow() {
        String regNo;
        String date = "";
        String[] details = new String[7];
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Enter the Driver Identification Number : ");
            regNo = scan.nextLine();
//            Check if the driver has taken any vehicle out for rent
            String currLine;
            BufferedReader br = new BufferedReader(new FileReader("drivers.txt"));
            while ((currLine = br.readLine()) != null)
            {

                details = currLine.split(",");
                if(details[0].equals(regNo)){
                    System.out.println("Driver found");
                    for(String i : details) System.out.println(i);
                }
            }
            System.out.println("Enter the date of actual return (dd/mm/yyyy): ");
            date = scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate returnDate = LocalDate.parse(date, fmt);
        LocalDate startDate = LocalDate.parse(details[5], fmt);
        LocalDate endDate = LocalDate.parse(details[6], fmt);
        long days = DAYS.between(startDate, endDate);
        long actualDays = DAYS.between(startDate, returnDate);
//        System.out.println("Number of Days " + actualDays + days);
        int cost = computeCost(days, actualDays);
         System.out.println("The cost: " + cost);
    }

    private int computeCost(long reservedDays, long actualDays){
        System.out.println("Vehicle was reserved for " + reservedDays + " days");
        System.out.println("Vehicle was actually returned in " + actualDays + " days");
        return 0; // return cost later
    }
}

class Luxury extends Vehicle {

}

class Small_car extends Vehicle {
    boolean ac;
}

class Family_car extends Vehicle {
    boolean ac;
}

class Small_Van extends Vehicle {
    boolean ac;
}

class Large_Van extends Vehicle {
    boolean ac;
}