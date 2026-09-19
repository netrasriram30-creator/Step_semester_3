package week6.assignment;
public class Q4{
    static String batchPrint(GymMember[] members){
        StringBuilder result=new StringBuilder();
        for(GymMember member:members){
            result.append(member.displayInfo());
            if(member instanceof PremiumMember){
                PremiumMember premium=(PremiumMember)member;
                result.append(" [Trainer via downcast: "+premium.getTrainerName()+"]");
            }
            result.append(" | ");
        }
        return result.toString();
    }
    public static void main(String[] args){
        GymMember[] members={
                new GymMember("MEM6",1000),
                new PremiumMember("MEM7",2000,"Coach Riya")
        };
        System.out.println(batchPrint(members));
        GymMember plain=new GymMember("MEM8",1000);
        try{
            PremiumMember bad=(PremiumMember)plain;
        }
        catch(ClassCastException e){
            System.out.println("ClassCastException at runtime");
        }
    }
}