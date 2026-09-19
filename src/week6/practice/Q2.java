package week6.practice;
class HonorsStudentMember extends StudentMember{
    private int bonusLimit;
    public HonorsStudentMember(String memberId,int borrowLimit,String course,int bonusLimit){
        super(memberId,borrowLimit,course);
        this.bonusLimit=bonusLimit;
    }
    @Override
    public String displayInfo(){
        return "Honors Student Member | Course: "+getCourse()+" | Bonus Limit: "+bonusLimit+" | Books Borrowed: "+getBooksBorrowed();
    }
}
class FacultyMember extends LibraryMember{
    private String department;
    public FacultyMember(String memberId,int borrowLimit,String department){
        super(memberId,borrowLimit);
        this.department=department;
    }
    public FacultyMember(int borrowLimit,String department){
        super(borrowLimit);
        this.department=department;
    }
    @Override
    public String displayInfo(){
        return "Faculty Member | Department: "+department+" | Books Borrowed: "+getBooksBorrowed();
    }
}
public class Q2{
    static String classifyGeneration(LibraryMember member){
        if(member instanceof HonorsStudentMember){
            return "Multilevel descendant (3 generations deep)";
        }
        if(member instanceof FacultyMember){
            return "Hierarchical sibling (independent branch)";
        }
        return "General Member";
    }
    static int getTotalBooksBorrowed(LibraryMember[] members){
        int total=0;
        for(LibraryMember member:members){
            total+=member.getBooksBorrowed();
        }
        return total;
    }
    public static void main(String[] args){
        LibraryMember general=new LibraryMember("STU1",3);
        StudentMember student=new StudentMember("STU2",3,"CSE");
        HonorsStudentMember honors=new HonorsStudentMember("STU3",3,"ECE",2);
        FacultyMember faculty=new FacultyMember("STU4",5,"Physics");
        System.out.println(general.displayInfo());
        System.out.println(student.displayInfo());
        System.out.println(honors.displayInfo());
        System.out.println(faculty.displayInfo());
        System.out.println(classifyGeneration(honors));
        System.out.println(classifyGeneration(faculty));
        student.borrowBook();
        student.borrowBook();
        honors.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();
        LibraryMember[] members={student,honors,faculty};
        System.out.println(getTotalBooksBorrowed(members));
    }
}