package week8.assignment;
import java.time.*;
import java.util.*;
public class Q3{
    static abstract class Seat{
        String id;
        Seat(String id){
            this.id=id;
        }
        abstract double getPrice();
    }
    static class RegularSeat extends Seat{
        RegularSeat(String id){
            super(id);
        }
        double getPrice(){
            return 150;
        }
    }
    static class PremiumSeat extends Seat{
        PremiumSeat(String id){
            super(id);
        }
        double getPrice(){
            return 250;
        }
    }
    static class ReclinerSeat extends Seat{
        ReclinerSeat(String id){
            super(id);
        }
        double getPrice(){
            return 400;
        }
    }
    static class Customer{
        String name;
        Customer(String name){
            this.name=name;
        }
    }
    static class Show{
        String name;
        LocalDateTime startTime;
        Map<String,Booking> bookedSeats=new HashMap<>();
        Show(String name,LocalDateTime startTime){
            this.name=name;
            this.startTime=startTime;
        }
        boolean isAvailable(Seat seat){
            return !bookedSeats.containsKey(seat.id);
        }
        Booking book(Customer customer,List<Seat> seats){
            if(seats.isEmpty()||seats.size()>6){
                System.out.println("A booking must contain 1 to 6 seats.");
                return null;
            }
            if(!LocalDateTime.now().isBefore(startTime)){
                System.out.println("Booking is closed: show has started.");
                return null;
            }
            Set<String> selected=new HashSet<>();
            for(Seat seat:seats){
                if(!selected.add(seat.id)){
                    System.out.println("Duplicate seat selected: "+seat.id);
                    return null;
                }
                if(!isAvailable(seat)){
                    System.out.println("Seat "+seat.id+" is already booked for this show.");
                    return null;
                }
            }
            Booking booking=new Booking(customer,this,seats);
            for(Seat seat:seats){
                bookedSeats.put(seat.id,booking);
            }
            System.out.print("Booking confirmed for "+customer.name+": ");
            for(int i=0;i<seats.size();i++){
                System.out.print(seats.get(i).id+(i<seats.size()-1?", ":""));
            }
            System.out.printf(". Total: ₹%.2f.%n",booking.getTotal());
            return booking;
        }
        void release(Booking booking){
            for(Seat seat:booking.seats){
                bookedSeats.remove(seat.id,booking);
            }
        }
    }
    static class Booking{
        Customer customer;
        Show show;
        List<Seat> seats;
        private boolean cancelled=false;
        Booking(Customer customer,Show show,List<Seat> seats){
            this.customer=customer;
            this.show=show;
            this.seats=new ArrayList<>(seats);
        }
        double getTotal(){
            double total=0;
            for(Seat seat:seats){
                total+=seat.getPrice();
            }
            return total;
        }
        void cancel(){
            if(cancelled){
                System.out.println("Booking is already cancelled.");
                return;
            }
            if(!LocalDateTime.now().isBefore(show.startTime)){
                System.out.println("Cannot cancel: show has already started.");
                return;
            }
            show.release(this);
            cancelled=true;
            System.out.print(customer.name+"'s booking cancelled. Seats ");
            for(int i=0;i<seats.size();i++){
                System.out.print(seats.get(i).id+(i<seats.size()-1?", ":""));
            }
            System.out.println(" released.");
        }
    }
    public static void main(String[] args){
        Show show=new Show("7 PM Show",LocalDateTime.of(2027,10,5,19,0));
        Customer asha=new Customer("Asha");
        Customer ravi=new Customer("Ravi");
        Customer neha=new Customer("Neha");
        Booking ashaBooking=show.book(asha,Arrays.asList(new RegularSeat("A1"),new RegularSeat("A2"),new PremiumSeat("F5")));
        show.book(ravi,Arrays.asList(new RegularSeat("A2")));
        show.book(ravi,Arrays.asList(new ReclinerSeat("R1")));
        if(ashaBooking!=null){
            ashaBooking.cancel();
        }
        show.book(neha,Arrays.asList(new RegularSeat("A2")));
    }
}