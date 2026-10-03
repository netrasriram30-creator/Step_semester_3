package week8.assignment;
public class Q4{
    interface MembershipPlan{
        int getMonths();
        double calculateFee();
        String getName();
    }
    static class MonthlyPlan implements MembershipPlan{
        public int getMonths(){
            return 1;
        }
        public double calculateFee(){
            return 1000;
        }
        public String getName(){
            return "Monthly";
        }
    }
    static class QuarterlyPlan implements MembershipPlan{
        public int getMonths(){
            return 3;
        }
        public double calculateFee(){
            return 3000*0.90;
        }
        public String getName(){
            return "Quarterly";
        }
    }
    static class AnnualPlan implements MembershipPlan{
        public int getMonths(){
            return 12;
        }
        public double calculateFee(){
            return 12000*0.75;
        }
        public String getName(){
            return "Annual";
        }
    }
    enum Status{
        Active,Frozen,Expired
    }
    static class Member{
        String name;
        Membership membership;
        Member(String name){
            this.name=name;
        }
        void buyMembership(MembershipPlan plan){
            membership=new Membership(this,plan);
            System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",plan.getName(),name,plan.calculateFee(),membership.getStatus());
        }
        void checkIn(){
            if(membership!=null&&membership.checkIn()){
                System.out.println(name+" checked in successfully.");
            }
            else{
                System.out.println("Check-in denied: "+name+"'s membership is "+(membership==null?"not available":membership.getStatus())+".");
            }
        }
    }
    static class Membership{
        Member member;
        MembershipPlan plan;
        private Status status=Status.Active;
        Membership(Member member,MembershipPlan plan){
            this.member=member;
            this.plan=plan;
        }
        Status getStatus(){
            return status;
        }
        boolean checkIn(){
            return status==Status.Active;
        }
        void freeze(){
            if(status==Status.Expired){
                System.out.println("Cannot freeze an Expired membership.");
                return;
            }
            if(status==Status.Frozen){
                System.out.println("Membership is already Frozen.");
                return;
            }
            status=Status.Frozen;
            System.out.println(member.name+"'s membership frozen. Status: "+status+".");
        }
        void unfreeze(){
            if(status==Status.Expired){
                System.out.println("Cannot unfreeze an Expired membership.");
                return;
            }
            if(status==Status.Active){
                System.out.println("Membership is already Active.");
                return;
            }
            status=Status.Active;
            System.out.println(member.name+"'s membership unfrozen. Status: "+status+".");
        }
        void expire(){
            if(status==Status.Expired){
                System.out.println("Membership is already Expired.");
                return;
            }
            status=Status.Expired;
            System.out.println(member.name+"'s membership expired. Status: "+status+".");
        }
    }
    public static void main(String[] args){
        Member asha=new Member("Asha");
        Member ravi=new Member("Ravi");
        asha.buyMembership(new QuarterlyPlan());
        ravi.buyMembership(new MonthlyPlan());
        asha.checkIn();
        asha.membership.freeze();
        asha.checkIn();
        ravi.membership.expire();
        ravi.membership.freeze();
    }
}