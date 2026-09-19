package week6.practice;
class LibraryMember{
    private static int membersEnrolled=0;
    private final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed;
    private int[] fineHistory=new int[10];
    private int fineCount;
    public LibraryMember(String memberId,int borrowLimit){
        this.borrowLimit=borrowLimit;
        membersEnrolled++;
        memberNumber="LIB-"+(100+membersEnrolled);
    }
    public LibraryMember(int borrowLimit){
        this("MEM-"+(membersEnrolled+1),borrowLimit);
    }
    public void borrowBook(){
        if(booksBorrowed<borrowLimit){
            booksBorrowed++;
        }
    }
    public void borrowBook(String genre){
        borrowBook();
    }
    public int getBooksBorrowed(){
        return booksBorrowed;
    }
    protected void chargeFine(int amount){
        if(fineCount<fineHistory.length){
            fineHistory[fineCount]=amount;
            fineCount++;
        }
    }
    public int[] getFineHistory(){
        int[] copy=new int[fineCount];
        for(int i=0;i<fineCount;i++){
            copy[i]=fineHistory[i];
        }
        return copy;
    }
    public int getTotalFine(){
        int total=0;
        for(int i=0;i<fineCount;i++){
            total+=fineHistory[i];
        }
        return total;
    }
    public String displayInfo(){
        return "General | Books: "+booksBorrowed;
    }
    public String getMemberNumber(){
        return memberNumber;
    }
    public static int getMembersEnrolled(){
        return membersEnrolled;
    }
    public static boolean isValidRenewalCode(String code){
        if(code==null||code.length()!=4){
            return false;
        }
        return code.charAt(0)=='R'&&Character.isDigit(code.charAt(1))&&Character.isDigit(code.charAt(2))&&Character.isUpperCase(code.charAt(3));
    }
}
class StudentMember extends LibraryMember{
    private String course;
    public StudentMember(String memberId,int borrowLimit,String course){
        super(memberId,borrowLimit);
        this.course=course;
    }
    public StudentMember(int borrowLimit,String course){
        super(borrowLimit);
        this.course=course;
    }
    public String getCourse(){
        return course;
    }
    @Override
    protected void chargeFine(int amount){
        super.chargeFine(amount/2);
    }
    @Override
    public String displayInfo(){
        return "Student | Course: "+course+" | Books: "+getBooksBorrowed();
    }
}
public class Q1{
    public static String enrollBatch(String[] memberIds,int borrowLimit){
        int enrolled=0;
        int rejected=0;
        for(String memberId:memberIds){
            try{
                if(memberId==null||memberId.trim().length()<4){
                    throw new IllegalArgumentException("Invalid member ID");
                }
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
            if("LB1".trim().length()<4){
                throw new IllegalArgumentException("Invalid member ID");
            }
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