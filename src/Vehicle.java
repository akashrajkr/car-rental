import javax.swing.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Vehicle {
    String make;
    String registrationNumber;
    private int[] S_date = new int[3];
    private int[] E_date = new int[3];
    private Date startDate, endDate;
    private String date;
    int cost;
    int days;
    public void rentNow() {
        //System.out.println("Renting now!");
        Scanner scan = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        System.out.println("Vehicle required from (press <Enter> to start from today)  (dd/mm/yyyy) : ");
        date = scan.nextLine();
        if(date.isEmpty()) {
            S_date[2] = calendar.get(calendar.YEAR);
            S_date[1] = calendar.get(calendar.MONTH)+1;
            S_date[0] = calendar.get(calendar.DATE);
            startDate = new Date();
        } else {
            String[] dateString = date.split("/");
            for (int i = 0; i < dateString.length; i++)
                S_date[i] = Integer.parseInt(dateString[i]);
            try {
                startDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Parse error");
            }
        }
        System.out.println("Enter your Date of Return(dd/mm/yyyy) : ");
        date = scan.nextLine();
        String[] eDateString = date.split("/");
        try {
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < eDateString.length; i++)
            E_date[i] = Integer.parseInt(eDateString[i]);
        System.out.println("Start date: " + startDate);
        System.out.println("Return date: " + endDate);
        long diff = endDate.getTime() - startDate.getTime();
        diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        if(diff < 0){
            System.out.println("Invalid return date!");
        } else {
            System.out.println ("Days: " + diff);
        }
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