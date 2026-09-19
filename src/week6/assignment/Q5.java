package week6.assignment;
public class Q5{
    static String processWeeklyCheckIn(GymMember[] members){
        int processed=0;
        int nullSkipped=0;
        int group=0;
        int individual=0;
        for(GymMember member:members){
            if(member==null){
                nullSkipped++;
                continue;
            }
            processed++;
            if(member instanceof GroupClassMember){
                group++;
            }
            else{
                individual++;
            }
        }
        return processed+" processed | "+nullSkipped+" null skipped | "+group+" group | "+individual+" individual";
    }
    public static void main(String[] args){
        GymMember m1=new GymMember(1000);
        System.out.println(m1.getMembershipNumber());
        System.out.println(GymMember.getMembersEnrolled());
        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.isValidReferralCode("G4B"));
        System.out.println(GymMember.isValidReferralCode("X45B"));
        m1.payFee(500);
        m1.payFee(500,"UPI");
        System.out.println(m1.getFeesPaid());
        GymMember[] members={
                new GroupClassMember(1500,"Zumba"),
                null,
                new GymMember(1000)
        };
        System.out.println(processWeeklyCheckIn(members));
    }
}