package week9.assignment;
import java.util.*;
public class Q3{
    public static String mostPopular(String[] orders){
        HashMap<String,Integer> counts=new HashMap<>();
        for(String item:orders){
            counts.put(item,counts.getOrDefault(item,0)+1);
        }
        String bestItem=orders[0];
        int bestCount=counts.get(bestItem);
        for(String item:orders){
            if(counts.get(item)>bestCount){
                bestItem=item;
                bestCount=counts.get(item);
            }
        }
        return "("+bestItem+", "+bestCount+")";
    }
    public static void main(String[] args){
        String[] orders={"dosa","idli","vada","dosa","idli","dosa","tea"};
        System.out.println(mostPopular(orders));
        String[] orders2={"tea","coffee","coffee","tea"};
        System.out.println(mostPopular(orders2));
    }
}