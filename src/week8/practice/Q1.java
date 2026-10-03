package week8.practice;
abstract class Vehicle{
    protected String vehicleId;
    private Rental activeRental;
    Vehicle(String vehicleId){
        this.vehicleId=vehicleId;
    }
    abstract double calculateCharge(int days);
    boolean isAvailable(){
        return activeRental==null;
    }
    void setActiveRental(Rental rental){
        activeRental=rental;
    }
    Rental getActiveRental(){
        return activeRental;
    }
    void returnVehicle(){
        activeRental=null;
    }
    String getVehicleId(){
        return vehicleId;
    }
}
class Sedan extends Vehicle{
    Sedan(String id){
        super(id);
    }
    double calculateCharge(int days){
        return days*50;
    }
}
class SUV extends Vehicle{
    SUV(String id){
        super(id);
    }
    double calculateCharge(int days){
        return days*80;
    }
}
class Truck extends Vehicle{
    Truck(String id){
        super(id);
    }
    double calculateCharge(int days){
        return days*100;
    }
}
class VehicleCustomer{
    private String name;
    VehicleCustomer(String name){
        this.name=name;
    }
    String getName(){
        return name;
    }
}
class Rental{
    private Vehicle vehicle;
    private VehicleCustomer customer;
    private int days;
    Rental(Vehicle vehicle,VehicleCustomer customer,int days){
        this.vehicle=vehicle;
        this.customer=customer;
        this.days=days;
    }
    Vehicle getVehicle(){
        return vehicle;
    }
    VehicleCustomer getCustomer(){
        return customer;
    }
    double getTotalCharge(){
        return vehicle.calculateCharge(days);
    }
}
class RentalService{
    void rentVehicle(Vehicle vehicle,VehicleCustomer customer,int days){
        if(days<=0){
            System.out.println("Invalid rental duration.");
            return;
        }
        if(!vehicle.isAvailable()){
            System.out.println(vehicle.getVehicleId()+" is currently unavailable.");
            return;
        }
        Rental rental=new Rental(vehicle,customer,days);
        vehicle.setActiveRental(rental);
        System.out.println(vehicle.getVehicleId()+" rented successfully by "+customer.getName()+".");
        System.out.println("Rental charge: $"+rental.getTotalCharge());
    }
    void returnVehicle(Vehicle vehicle,VehicleCustomer customer){
        Rental rental=vehicle.getActiveRental();
        if(rental==null||rental.getCustomer()!=customer){
            System.out.println("No active rental found for "+customer.getName()+".");
            return;
        }
        vehicle.returnVehicle();
        System.out.println(vehicle.getVehicleId()+" returned by "+customer.getName()+".");
    }
}
public class Q1{
    public static void main(String[] args){
        RentalService service=new RentalService();
        VehicleCustomer c1=new VehicleCustomer("Customer 1");
        VehicleCustomer c2=new VehicleCustomer("Customer 2");
        VehicleCustomer c3=new VehicleCustomer("Customer 3");
        Vehicle sedan=new Sedan("Sedan A");
        Vehicle suv=new SUV("SUV B");
        service.rentVehicle(sedan,c1,3);
        service.rentVehicle(sedan,c2,2);
        service.returnVehicle(sedan,c1);
        service.rentVehicle(suv,c3,5);
    }
}