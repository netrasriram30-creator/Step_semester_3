package week8.practice;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
abstract class Employee{
    private String name;
    Employee(String name){
        this.name=name;
    }
    String getName(){
        return name;
    }
    abstract int maxLeaveDays();
    LeaveRequest submitRequest(LocalDate start,LocalDate end){
        long days=ChronoUnit.DAYS.between(start,end)+1;
        if(days<=0||days>maxLeaveDays()){
            System.out.println("Leave request not allowed for "+name+".");
            return null;
        }
        LeaveRequest request=new LeaveRequest(this,start,end);
        System.out.println("Leave request submitted for "+name+" ("+request.getDates()+").");
        System.out.println("Status: "+request.getStatus());
        return request;
    }
}
class FullTimeEmployee extends Employee{
    FullTimeEmployee(String name){
        super(name);
    }
    int maxLeaveDays(){
        return 20;
    }
}
class PartTimeEmployee extends Employee{
    PartTimeEmployee(String name){
        super(name);
    }
    int maxLeaveDays(){
        return 10;
    }
}
class Contractor extends Employee{
    Contractor(String name){
        super(name);
    }
    int maxLeaveDays(){
        return 5;
    }
}
class Reviewer{
    private String name;
    Reviewer(String name){
        this.name=name;
    }
    void review(LeaveRequest request,LeaveRequest.Status decision){
        if(request==null) return;
        if(request.changeStatus(decision)){
            System.out.println(request.getEmployee().getName()+"'s leave request ("+request.getDates()+") "+decision.toString().toLowerCase()+" by "+name+".");
            System.out.println("Status: "+request.getStatus());
        }else{
            System.out.println("Cannot change leave request status from "+request.getStatus()+" to "+decision+".");
        }
    }
}
class LeaveRequest{
    enum Status{Pending,Approved,Rejected}
    private Employee employee;
    private LocalDate start,end;
    private Status status=Status.Pending;
    LeaveRequest(Employee employee,LocalDate start,LocalDate end){
        this.employee=employee;
        this.start=start;
        this.end=end;
    }
    Employee getEmployee(){
        return employee;
    }
    Status getStatus(){
        return status;
    }
    String getDates(){
        DateTimeFormatter format=DateTimeFormatter.ofPattern("MMM d");
        return start.format(format)+"-"+end.format(format);
    }
    boolean changeStatus(Status newStatus){
        if(status!=Status.Pending||newStatus==Status.Pending) return false;
        status=newStatus;
        return true;
    }
}
public class Q2{
    public static void main(String[] args){
        Employee john=new FullTimeEmployee("John");
        Employee jane=new PartTimeEmployee("Jane");
        Reviewer alice=new Reviewer("Alice");
        Reviewer bob=new Reviewer("Bob");
        LeaveRequest r1=john.submitRequest(LocalDate.of(2026,1,1),LocalDate.of(2026,1,5));
        alice.review(r1,LeaveRequest.Status.Approved);
        LeaveRequest r2=jane.submitRequest(LocalDate.of(2026,2,10),LocalDate.of(2026,2,11));
        bob.review(r2,LeaveRequest.Status.Rejected);
        if(r1!=null&&!r1.changeStatus(LeaveRequest.Status.Pending)){
            System.out.println("Cannot change leave request status from "+r1.getStatus()+" to Pending.");
        }
    }
}