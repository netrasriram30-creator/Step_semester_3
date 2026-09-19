package week6.assignment;
class GymMember{
    private static int membersEnrolled=0;
    private final String membershipNumber;
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;
    private int feesPaid;
    private int[] lateFeeHistory=new int[10];
    private int lateFeeCount;
    private String paymentMode;
    public GymMember(String memberId,int monthlyFee){
        if(memberId==null||memberId.trim().length()<4){
            throw new IllegalArgumentException("Invalid member ID");
        }
        if(monthlyFee<=0){
            throw new IllegalArgumentException("Invalid monthly fee");
        }
        this.memberId=memberId;
        this.monthlyFee=monthlyFee;
        membersEnrolled++;
        membershipNumber="GYM-"+(2000+membersEnrolled);
    }
    public GymMember(int monthlyFee){
        if(monthlyFee<=0){
            throw new IllegalArgumentException("Invalid monthly fee");
        }
        this.monthlyFee=monthlyFee;
        membersEnrolled++;
        membershipNumber="GYM-"+(2000+membersEnrolled);
    }
    public void attendSession(){
        sessionsAttended++;
    }
    public int getSessionsAttended(){
        return sessionsAttended;
    }
    public String displayInfo(){
        return "Standard Member | Sessions: "+sessionsAttended;
    }
    protected void chargeLateFee(int amount){
        if(lateFeeCount<lateFeeHistory.length){
            lateFeeHistory[lateFeeCount]=amount;
            lateFeeCount++;
        }
    }
    public int[] getLateFeeHistory(){
        int[] copy=new int[lateFeeCount];
        for(int i=0;i<lateFeeCount;i++){
            copy[i]=lateFeeHistory[i];
        }
        return copy;
    }
    public int getTotalLateFees(){
        int total=0;
        for(int i=0;i<lateFeeCount;i++){
            total+=lateFeeHistory[i];
        }
        return total;
    }
    public String getMembershipNumber(){
        return membershipNumber;
    }
    public void payFee(int amount){
        if(amount>0){
            feesPaid+=amount;
        }
    }
    public void payFee(int amount,String mode){
        paymentMode=mode;
        payFee(amount);
    }
    public int getFeesPaid(){
        return feesPaid;
    }
    public static boolean isValidReferralCode(String code){
        if(code==null||code.length()!=4){
            return false;
        }
        return code.charAt(0)=='G'&&Character.isDigit(code.charAt(1))&&Character.isDigit(code.charAt(2))&&Character.isUpperCase(code.charAt(3));
    }
    public static int getMembersEnrolled(){
        return membersEnrolled;
    }
}
class PremiumMember extends GymMember{
    private String trainerName;
    public PremiumMember(String memberId,int monthlyFee,String trainerName){
        super(memberId,monthlyFee);
        this.trainerName=trainerName;
    }
    public String getTrainerName(){
        return trainerName;
    }
    @Override
    public String displayInfo(){
        return "Premium Member | Trainer: "+trainerName+" | Sessions: "+getSessionsAttended();
    }
    @Override
    protected void chargeLateFee(int amount){
        super.chargeLateFee(amount/2);
    }
}
public class Q1{
    static String signUpBatch(String[] memberIds,int monthlyFee){
        int signedUp=0;
        int rejected=0;
        for(String memberId:memberIds){
            try{
                new GymMember(memberId,monthlyFee);
                signedUp++;
            }
            catch(IllegalArgumentException e){
                rejected++;
            }
        }
        return "Signed Up: "+signedUp+" | Rejected: "+rejected;
    }
    public static void main(String[] args){
        try{
            new GymMember("GM1",1000);
        }
        catch(IllegalArgumentException e){
            System.out.println("construction rejected");
        }
        PremiumMember p=new PremiumMember("MEM01",2000,"Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println(p.getSessionsAttended());
        String[] memberIds={"MEM1","GM1","MEM2"," ","MEM3"};
        System.out.println(signUpBatch(memberIds,1000));
    }
}