package week5;
public class Q4{
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;
    private boolean membershipIdSet;
    public Q4(){
        membershipIdSet=false;
    }
    public String getMembershipId(){
        return membershipId;
    }
    public void setMembershipId(String id){
        if(!membershipIdSet){
            membershipId=id;
            membershipIdSet=true;
        }
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public boolean isPremiumMember(){
        return premiumMember;
    }
    public void setPremiumMember(boolean premium){
        premiumMember=premium;
    }
    public void setSecurityAnswer(String answer){
        securityAnswer=transform(answer);
    }
    private String transform(String answer){
        StringBuilder result=new StringBuilder();
        for(int i=0;i<answer.length();i++){
            result.append((char)(answer.charAt(i)+1));
        }
        return result.toString();
    }
    public static void main(String[] args){
        Q4 m=new Q4();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println(m.getMembershipId());
        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());
        System.out.println(m.isPremiumMember());
        m.setSecurityAnswer("BlueMountain");
    }
}