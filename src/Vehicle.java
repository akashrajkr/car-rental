import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Vehicle {
    protected String make;
    protected String registrationNumber;
    protected Date startDate, endDate;
    String date;
    protected int cost;
    protected long days;
    public void rentNow() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Vehicle required from (press <Enter> to start from today)  (dd/mm/yyyy) : ");
        date = scan.nextLine();
        if(date.isEmpty()) {
            startDate = new Date();
        } else {
            try {
                startDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Parse error");
            }
        }
        System.out.println("Enter your Date of Return(dd/mm/yyyy) : ");
        date = scan.nextLine();
        try {
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Start date: " + startDate);
        System.out.println("Return date: " + endDate);
        long diff = endDate.getTime() - startDate.getTime();
        days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        if(diff < 0){
            System.out.println("Invalid return date!");
        } else {
            System.out.println ("Days: " + days);
        }
    }

    public void returnNow() {

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