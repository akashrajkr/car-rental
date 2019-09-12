import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

class Vehicle {
    //    localDateTime objects for adding days to Date objects
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate endDate, startDate;
    protected float cost,base;

    void rentNow(Driver driver) {
        Scanner scan = new Scanner(System.in);
        String date;
        while (true){
            System.out.println("Vehicle required from (press <Enter> to start from today)  (dd/mm/yyyy) : ");
            date = scan.nextLine();
            if (date.isEmpty()) {
                startDate = LocalDate.now();
                break;
            }
            else {
                try{
                    startDate = LocalDate.parse(date, fmt);
                }catch (DateTimeParseException e){
                    e.getMessage();
                    continue;
                }

            }
            if(startDate.isBefore(LocalDate.now())) break;
            else System.out.println("You can't really go back to the past!\n please enter a valid date...\n");
        }
        while (true) {
            System.out.println("Enter your Date of Return(dd/mm/yyyy) : ");
            date = scan.nextLine();
            try{
                endDate = LocalDate.parse(date, fmt);
            }catch (DateTimeParseException e){
                e.getMessage();
                continue;
            }
            System.out.println("Start date: " + startDate);
            System.out.println("Return date: " + endDate);
            if (endDate.isBefore(startDate)) {
                System.out.println("Invalid return date! (Return date is before the start date)");
                continue;
            } else break;
        }
//      Writing everything to a file now
        System.out.println("Assigning you a vehicle now!");
        driver.writeInfo();
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

        String textToAppend = String.format(",%s,%s,%s,%s", tempRegNumber, tempMake, startDate.format(fmt), endDate.format(fmt));
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
        float cost = computeCost(days, actualDays);
        System.out.println("The cost: " + cost);
    }

    private float computeCost(long reservedDays, long actualDays){
        /*System.out.println("Vehicle was reserved for " + reservedDays + " days");
        System.out.println("Vehicle was actually returned in " + actualDays + " days");
        return 0; // return cost later*/
    }
}

class Luxury extends Vehicle {
    private double base = 150.0;
    private float computeCost(long reservedDays, long actualDays){
        cost= (float) (reservedDays*base-(reservedDays-actualDays)*0.3*base);
        return 0;
    }
}

class Small_car extends Vehicle {
    boolean ac=false;
    String ans;
    private float base = 30;
    Scanner scan = new Scanner(System.in);
    Small_car(){
        System.out.println("Do you require Air Condidtioning? (Y/N)");
        ans = scan.nextLine();
        if(ans=="Y")
            ac=true;

    }
    private float computeCost(long reservedDays, long actualDays){
        if(reservedDays>=actualDays)
            cost=reservedDays*base-(reservedDays-actualDays)*base;
        else
            cost= (float) (reservedDays*base-(reservedDays-actualDays)*base*0.2);
        if(ac)
        {
            cost = (float) (1.1*cost);
        }
        return cost;
    }
}

class Family_car extends Vehicle {
    boolean ac=false;
    String ans;
    private float base =  40;
    Scanner scan = new Scanner(System.in);
    Family_car(){
        System.out.println("Do you require Air Condidtioning? (Y/N)");
        ans = scan.nextLine();
        if(ans=="Y")
            ac=true;

    }
    private float computeCost(long reservedDays, long actualDays){
        if(reservedDays>=actualDays)
            cost= (float) (reservedDays*base-(reservedDays-actualDays)*base*0.5);
        else
            cost= (float) (reservedDays*base-(reservedDays-actualDays)*base*0.2);
        if(ac)
        {
            cost = (float) (1.1*cost);
        }
        return cost;
    }
}

class Small_Van extends Vehicle {
    boolean ac=false;
    String ans;
    private float base = 50;
    Scanner scan = new Scanner(System.in);
    Small_Van(){
        System.out.println("Do you require Air Conditioning? (Y/N)");
        ans = scan.nextLine();
        if(ans=="Y")
            ac=true;

    }
    private float computeCost(long reservedDays, long actualDays){
        if(reservedDays>=actualDays)
            cost= (float) (reservedDays*base-(reservedDays-actualDays)*base*0.5);
        else
            cost= (float) (reservedDays*base-(reservedDays-actualDays)*base*0.2);
        if(ac)
        {
            cost = (float) (1.1*cost);
        }
        return cost;
    }
}

class Large_Van extends Vehicle {
    boolean ac=false;
    String ans;
    private float base = 70;
    Scanner scan = new Scanner(System.in);
    Large_Van(){
        System.out.println("Do you require Air Condidtioning? (Y/N)");
        ans = scan.nextLine();
        if(ans=="Y")
            ac=true;

    }
    private float computeCost(long reservedDays, long actualDays){
        if(reservedDays>=actualDays)
            cost= (float) (reservedDays*base-(reservedDays-actualDays)*base*0.5);
        else
            cost= (float) (reservedDays*base-(reservedDays-actualDays)*base*0.2);
        if(ac)
        {
            cost = (float) (1.1*cost);
        }
        return cost;
    }
}