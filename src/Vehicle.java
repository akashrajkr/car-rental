import java.io.*;
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
    private int reservedDays, actualDays;
    float cost,base;

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
                    System.out.println(e.getMessage());
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


    void returnNow(String currLine) {
        String date = "";
        String[] details = new String[8];
        Scanner scan = new Scanner(System.in);
        try {
            details = currLine.split(",");
            System.out.println("Driver found");
            for(String i : details) System.out.println(i);
            System.out.println("Enter the date of actual return (dd/mm/yyyy): ");
            date = scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate returnDate = LocalDate.parse(date, fmt);
        LocalDate startDate = LocalDate.parse(details[6], fmt);
        LocalDate endDate = LocalDate.parse(details[7], fmt);
        reservedDays = (int) DAYS.between(startDate, endDate);
        actualDays = (int) DAYS.between(startDate, returnDate);
        System.out.println("Reserved days: " + reservedDays + "\nActual days: " + actualDays);
    }

    public int getActualDays() {
        return actualDays;
    }

    public int getReservedDays() {
        return reservedDays;
    }

    private float computeCost(long a, long b) {
        return 0;
    }

    void writeAcInfo(String isAcrequired) {
        String textToAppend = String.format(",%s", isAcrequired);
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

    String findDriver(String DIN){
//            Check if the driver has taken any vehicle out for rent
        String currLine;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("drivers.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true)
        {
            try {
                assert br != null;
                if (((currLine = br.readLine())== null)) break;
                if(currLine.split(",")[0].equalsIgnoreCase(DIN)){
                    return currLine;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Driver not found! Check DIN again...");
        return null;
    }

}

class Luxury_car extends Vehicle {
    private double base = 150.0;
    private float computeCost(long reservedDays, long actualDays){
        cost= (float) (reservedDays*base-(reservedDays-actualDays)*0.3*base);
        return cost;
    }
}

class Small_car extends Vehicle {
    private boolean ac=false;
    private String ans;
    private Scanner scan = new Scanner(System.in);

    private float computeCost(long reservedDays, long actualDays){
        float base = 30;
        if(reservedDays>=actualDays)
            cost=reservedDays* base -(reservedDays-actualDays)* base;
        else
            cost= (float) (reservedDays* base -(reservedDays-actualDays)* base *0.2);
        if(ac)
        {
            cost = (float) (1.1*cost);
        }
        return cost;
    }

    @Override
    void rentNow(Driver driver) {
        String isAcrequired;
        do {
            System.out.println("Is AC required? (yes/no)");
            isAcrequired = scan.nextLine();
        } while (!(isAcrequired.equalsIgnoreCase("yes") || isAcrequired.equalsIgnoreCase("y") || isAcrequired.equalsIgnoreCase("no") ||isAcrequired.equalsIgnoreCase("n")));
        if(isAcrequired.equalsIgnoreCase("yes") || isAcrequired.equalsIgnoreCase("y")) ac = true;
        if(isAcrequired.equalsIgnoreCase("no") || isAcrequired.equalsIgnoreCase("n")) ac = false;
        super.rentNow(driver);
        writeAcInfo(isAcrequired);
    }

    @Override
    void returnNow(String currLine) {
        super.returnNow(currLine);
        int rd = getReservedDays();
        int ad = getActualDays();
        float cost = computeCost(rd, ad);
        System.out.println("Cost: " + cost);
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
        if(ans.equalsIgnoreCase("y"))
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