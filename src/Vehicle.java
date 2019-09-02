public class Vehicle {
    String make;
    String registrationNumber;

    public void rentVehicle() {
        Driver driver = new Driver();
        driver.getDetails();
        if(driver.canRent()){
            System.out.println("Driver can rent");
        } else {
            System.out.println("Driver cannot rent");
        }
    }

}
