package week8.practice;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
abstract class Room{
    protected String roomId;
    private ArrayList<Reservation> reservations=new ArrayList<>();
    Room(String roomId){
        this.roomId=roomId;
    }
    abstract double calculatePrice(long nights);
    boolean isAvailable(LocalDate start,LocalDate end){
        for(Reservation r:reservations){
            if(r.isActive()&&start.isBefore(r.getEnd())&&end.isAfter(r.getStart())) return false;
        }
        return true;
    }
    void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
    String getRoomId(){
        return roomId;
    }
}
class StandardRoom extends Room{
    StandardRoom(String id){
        super(id);
    }
    double calculatePrice(long nights){
        return nights*100;
    }
}
class DeluxeRoom extends Room{
    DeluxeRoom(String id){
        super(id);
    }
    double calculatePrice(long nights){
        return nights*150;
    }
}
class Suite extends Room{
    Suite(String id){
        super(id);
    }
    double calculatePrice(long nights){
        return nights*250;
    }
}
class HotelCustomer{
    private String name;
    HotelCustomer(String name){
        this.name=name;
    }
    String getName(){
        return name;
    }
}
class Reservation{
    private Room room;
    private HotelCustomer customer;
    private LocalDate start,end;
    private boolean active=true;
    Reservation(Room room,HotelCustomer customer,LocalDate start,LocalDate end){
        this.room=room;
        this.customer=customer;
        this.start=start;
        this.end=end;
    }
    LocalDate getStart(){
        return start;
    }
    LocalDate getEnd(){
        return end;
    }
    boolean isActive(){
        return active;
    }
    boolean cancel(){
        if(!active||!LocalDate.now().isBefore(start.minusDays(1))) return false;
        active=false;
        return true;
    }
    double getPrice(){
        return room.calculatePrice(ChronoUnit.DAYS.between(start,end));
    }
    Room getRoom(){
        return room;
    }
    HotelCustomer getCustomer(){
        return customer;
    }
}
class HotelBookingService{
    Reservation book(Room room,HotelCustomer customer,LocalDate start,LocalDate end){
        if(!start.isBefore(end)){
            System.out.println("Invalid reservation dates.");
            return null;
        }
        if(!room.isAvailable(start,end)){
            System.out.println(room.getRoomId()+" is not available from "+start+" to "+end+".");
            return null;
        }
        Reservation r=new Reservation(room,customer,start,end);
        room.addReservation(r);
        System.out.println("Reservation confirmed for "+customer.getName()+", "+room.getRoomId()+" ("+start+" to "+end+").");
        System.out.println("Price: $"+r.getPrice());
        return r;
    }
    void checkAvailability(Room room,LocalDate start,LocalDate end){
        System.out.println(room.getRoomId()+(room.isAvailable(start,end)?" is available from ":" is not available from ")+start+" to "+end+".");
    }
    void cancel(Reservation reservation){
        if(reservation!=null&&reservation.cancel()){
            System.out.println("Reservation for "+reservation.getCustomer().getName()+", "+reservation.getRoom().getRoomId()+" cancelled successfully.");
        }else{
            System.out.println("Cancellation deadline passed or reservation is inactive.");
        }
    }
}
public class Q4{
    public static void main(String[] args){
        HotelBookingService service=new HotelBookingService();
        HotelCustomer a=new HotelCustomer("Customer A");
        HotelCustomer b=new HotelCustomer("Customer B");
        HotelCustomer c=new HotelCustomer("Customer C");
        Room standard=new StandardRoom("Standard Room 101");
        Room deluxe=new DeluxeRoom("Deluxe Room 201");
        LocalDate start=LocalDate.of(2027,1,1);
        LocalDate end=LocalDate.of(2027,1,5);
        service.checkAvailability(standard,start,end);
        Reservation r=service.book(standard,a,start,end);
        service.book(standard,b,LocalDate.of(2027,1,3),LocalDate.of(2027,1,7));
        service.cancel(r);
        service.book(deluxe,c,LocalDate.of(2027,2,10),LocalDate.of(2027,2,12));
    }
}