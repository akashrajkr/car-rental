import java.util.Calendar;
import java.util.Scanner;

public class Vehicle {
    String make;
    String registrationNumber;
    int[] S_date;
    String E_date;
    int cost;
    int days;
    public void rentNow() {
        //System.out.println("Renting now!");
        Scanner scan = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        System.out.println("Enter your Date of Return(dd/mm/yyyy)");
        E_date = scan.nextLine();
        S_date = new int[3];
        S_date[2] = calendar.get(calendar.YEAR);
        S_date[1] = calendar.get(calendar.MONTH)+1;
        S_date[0] = calendar.get(calendar.DATE);
        String[] dob_split = E_date.split("/");
        int[] enddate = new int[dob_split.length];
        for (int i = 0; i < enddate.length; i++)
            enddate[i] = Integer.parseInt(dob_split[i]);
        for (int i = 0; i < enddate.length; i++)
            System.out.println(S_date[i]);
        for (int i = 0; i < enddate.length; i++)
            System.out.println(enddate[i]);
        days=enddate[0]-S_date[0];
        /*if(enddate[2]>=S_date[2]&&enddate[1]>=S_date[1])
            if(enddate[1]==S_date[1])
                days=enddate[0]-S_date[0];
            else if(S_date[1]==2)
                days=28-S_date[0]+enddate[0];
            else if(S_date[1]);*/

        
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