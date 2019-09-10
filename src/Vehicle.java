import java.util.Calendar;
import java.util.Scanner;

class Vehicle {
    String make;
    String registrationNumber;
    private int[] S_date = new int[3];
    private int[] E_date = new int[3];
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
        } else {
            String[] dateString = date.split("/");
            for (int i = 0; i < dateString.length; i++)
                S_date[i] = Integer.parseInt(dateString[i]);
        }
        System.out.println("Enter your Date of Return(dd/mm/yyyy)");
        date = scan.nextLine();
        String[] eDateString = date.split("/");

        for (int i = 0; i < eDateString.length; i++)
            E_date[i] = Integer.parseInt(eDateString[i]);
        System.out.print("Start date: ");
        for (int i = 0; i < 3; i++)
            System.out.print(S_date[i] + " ");
        System.out.println();
        System.out.print("Return date: ");
        for (int i = 0; i < 3; i++)
            System.out.print(E_date[i] + " ");
        System.out.println();
//        days=E_date[0]-S_date[0];
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