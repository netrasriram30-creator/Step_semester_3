package week6.practice;
public class Q4{
    static String batchPrint(LibraryMember[] members){
        StringBuilder result=new StringBuilder();
        for(LibraryMember member:members){
            result.append(member.displayInfo());
            if(member instanceof StudentMember){
                StudentMember student=(StudentMember)member;
                result.append(" [Course via downcast: "+student.getCourse()+"]");
            }
            result.append(" | ");
        }
        return result.toString();
    }
    public static void main(String[] args){
        LibraryMember[] members={
                new LibraryMember("LB5",3),
                new StudentMember("STU6",3,"ECE")
        };
        System.out.println(batchPrint(members));
    }
}