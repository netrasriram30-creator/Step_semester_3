package week6.practice;
public class Q5{
    static String processNightlyAudit(LibraryMember[] members){
        int processed=0;
        int nullSkipped=0;
        int faculty=0;
        int regular=0;
        for(LibraryMember member:members){
            if(member==null){
                nullSkipped++;
                continue;
            }
            processed++;
            if(member instanceof FacultyMember){
                faculty++;
            }
            else{
                regular++;
            }
        }
        return processed+" processed | "+nullSkipped+" null skipped | "+faculty+" faculty | "+regular+" regular";
    }
    public static void main(String[] args){
        LibraryMember m1=new LibraryMember(3);
        System.out.println(m1.getMemberNumber());
        System.out.println(LibraryMember.getMembersEnrolled());
        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A2"));
        System.out.println(LibraryMember.isValidRenewalCode("R12a"));
        m1.borrowBook("Fiction");
        FacultyMember faculty=new FacultyMember(5,"Physics");
        LibraryMember[] members={faculty,null,new LibraryMember(3)};
        System.out.println(processNightlyAudit(members));
    }
}