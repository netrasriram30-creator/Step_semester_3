package week4;
class LibraryCard{
    static String libraryName;
    static String validUntil;
    String studentName;
    static{
        libraryName="SRM Central Library";
        validUntil="May 2027";
        System.out.println("Library info loaded");
    }
    public LibraryCard(String studentName){
        this.studentName=studentName;
    }
}
public class A4LibraryCard{
    public static void main(String[] args){
        String[] names={"Ananya","Rohan","Priya","Arjun","Sneha"};
        for(int i=0;i<names.length;i++){
            LibraryCard card=new LibraryCard(names[i]);
            System.out.println("Membership card issued: "+card.studentName);
        }
    }
}