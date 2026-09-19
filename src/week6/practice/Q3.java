package week6.practice;
public class Q3{
    public static void main(String[] args){
        StudentMember s=new StudentMember("STU5",3,"CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());
        int[] history=s.getFineHistory();
        history[0]=999;
        System.out.println(java.util.Arrays.toString(s.getFineHistory()));
    }
}