package week9.practice;
import java.util.Scanner;
public class Q1{
    public static String findBook(String[][] catalog,String targetIsbn){
        int low=0;
        int high=catalog.length-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(catalog[mid][0].equals(targetIsbn)){
                return catalog[mid][1];
            }
            else if(catalog[mid][0].compareTo(targetIsbn)<0){
                low=mid+1;
            }
            else{
                high=mid-1;
            }
        }
        return "Not Found";
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[][] catalog={{"0001112223","Introduction to Algebra"},{"0002223334","Beginning Python"},{"0003334445","Classic Mythology"},{"0004445556","Data and Society"},{"0005556667","European History"}};
        System.out.println("Enter ISBN: ");
        String targetIsbn=sc.nextLine();
        System.out.println(findBook(catalog,targetIsbn));
        sc.close();
    }
}