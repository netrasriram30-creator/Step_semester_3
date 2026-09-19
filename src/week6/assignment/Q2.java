package week6.assignment;
class EliteMember extends PremiumMember{
    private String lockerNumber;
    public EliteMember(String memberId,int monthlyFee,String trainerName,String lockerNumber){
        super(memberId,monthlyFee,trainerName);
        this.lockerNumber=lockerNumber;
    }
    @Override
    public String displayInfo(){
        return "Elite Member | Trainer: "+getTrainerName()+" | Locker: "+lockerNumber+" | Sessions: "+getSessionsAttended();
    }
}
class GroupClassMember extends GymMember{
    private String className;
    public GroupClassMember(String memberId,int monthlyFee,String className){
        super(memberId,monthlyFee);
        this.className=className;
    }
    public GroupClassMember(int monthlyFee,String className){
        super(monthlyFee);
        this.className=className;
    }
    @Override
    public String displayInfo(){
        return "Group Class Member | Class: "+className+" | Sessions: "+getSessionsAttended();
    }
}
public class Q2{
    static String classifyGeneration(GymMember member){
        if(member instanceof EliteMember){
            return "Multilevel descendant (3 generations deep)";
        }
        if(member instanceof GroupClassMember){
            return "Hierarchical sibling (independent branch)";
        }
        return "Standard Member";
    }
    static int getTotalSessionsAttended(GymMember[] members){
        int total=0;
        for(GymMember member:members){
            total+=member.getSessionsAttended();
        }
        return total;
    }
    public static void main(String[] args){
        GymMember general=new GymMember("MEM1",1000);
        PremiumMember premium=new PremiumMember("MEM2",2000,"Coach Riya");
        EliteMember elite=new EliteMember("MEM3",3000,"Coach Arjun","L12");
        GroupClassMember group=new GroupClassMember("MEM4",1500,"Zumba");
        System.out.println(general.displayInfo());
        System.out.println(premium.displayInfo());
        System.out.println(elite.displayInfo());
        System.out.println(group.displayInfo());
        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(group));
        premium.attendSession();
        premium.attendSession();
        premium.attendSession();
        elite.attendSession();
        elite.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();
        GymMember[] members={premium,elite,group};
        System.out.println(getTotalSessionsAttended(members));
    }
}