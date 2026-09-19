package week6.practice;
class LibraryMember{
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;
    public LibraryMember(String memberId,int borrowLimit){
        if(memberId==null||memberId.trim().length()<4){
            throw new IllegalArgumentException("Invalid member ID");
        }
        this.memberId=memberId;
        this.borrowLimit=borrowLimit;
    }
    public void borrowBook(){
        if(booksBorrowed<borrowLimit){
            booksBorrowed++;
        }
    }
    public int getBooksBorrowed(){
        return booksBorrowed;
    }
}
class StudentMember extends LibraryMember{
    private String course;
    public StudentMember(String memberId,int borrowLimit,String course){
        super(memberId,borrowLimit);
        this.course=course;
    }
}
public class Q1{
    public static String enrollBatch(String[] memberIds,int borrowLimit){
        int enrolled=0;
        int rejected=0;
        for(String memberId:memberIds){
            try{
                new LibraryMember(memberId,borrowLimit);
                enrolled++;
            }
            catch(IllegalArgumentException e){
                rejected++;
            }
        }
        return "Enrolled: "+enrolled+" | Rejected: "+rejected;
    }
    public static void main(String[] args){
        try{
            new LibraryMember("LB1",3);
        }
        catch(IllegalArgumentException e){
            System.out.println("construction rejected");
        }
        StudentMember s=new StudentMember("STU10",3,"CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed());
        String[] memberIds={"STU1","LB1","STU2"," ","STU3"};
        System.out.println(enrollBatch(memberIds,3));
    }
}