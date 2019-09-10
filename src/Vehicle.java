import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Vehicle {
    protected Date startDate, endDate;
//    localDateTime objects for adding days to Date objects
    protected LocalDate lStartDate, lEndDate;
    String date;
    protected int cost;
    protected long days;
    protected Period actualDays;
    protected LocalDate returnDate;
    public void rentNow() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Vehicle required from (press <Enter> to start from today)  (dd/mm/yyyy) : ");
        date = scan.nextLine();
        if(date.isEmpty()) {
            startDate = new Date();
            lStartDate = LocalDate.now();
        } else {
            try {
                startDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                lStartDate = LocalDate.ofInstant(startDate.toInstant(),  ZoneId.systemDefault());
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Parse error");
            }
        }
        System.out.println("Enter your Date of Return(dd/mm/yyyy) : ");
        date = scan.nextLine();
        try {
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            lEndDate = LocalDate.ofInstant(endDate.toInstant(),  ZoneId.systemDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Start date: " + startDate + lStartDate);
        System.out.println("Return date: " + endDate + lEndDate);
        long diff = endDate.getTime() - startDate.getTime();
        days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        if(diff < 0){
            System.out.println("Invalid return date!");
        } else {
            System.out.println ("Days: " + days);
            System.out.println();
        }
//        Writing everything to a file now
        writeInfo();
    }
    private void writeInfo() {
//        Writing vehicle info into a file for further processing in the format
//        registration number,  make, startDate, endDate, driverIdentifictionNumber

        String tempRegNumber ;
        String tempMake;
        Random rnd = new Random();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int stateCode = 100;
        while (stateCode > 54){
            stateCode = Integer.parseInt(rnd.nextInt(10) +  rnd.nextInt(10) + "");
        }
        tempRegNumber = "KA-" + stateCode + " " +  (char) (rnd.nextInt(26) + 'A') + (char) (rnd.nextInt(26) + 'A') + "-" + rnd.nextInt(10) + rnd.nextInt(10) +rnd.nextInt(10) +rnd.nextInt(10) ;
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
            writer.write(textToAppend);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnNow() {
        String regNo = "";
        String date = "";
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Enter the Driver Identification Number : ");
            regNo = scan.nextLine();
//            Check if the driver has taken any vehicle out for rent
            String currLine = "", lastLine = "";

            System.out.println("Enter the date of actual return (dd/mm/yyyy): ");
            date = scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        returnDate = LocalDate.parse(date, fmt);
        actualDays = Period.between(returnDate, lStartDate);
        System.out.println("Number of Days " + actualDays.getDays());
    }
}
class Luxury extends Vehicle{

}
class Small_car extends Vehicle{
    boolean ac;
}
class Family_car extends Vehicle{
    boolean ac;
}
class Small_Van extends Vehicle{
    boolean ac;
}
class Large_Van extends Vehicle{
    boolean ac;
}