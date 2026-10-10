package week9.assignment;
public class Q1{
    public static String findTopper(int[][] marks){
        int bestRow=0;
        int bestTotal=-1;
        for(int i=0;i<marks.length;i++){
            int total=0;
            for(int j=0;j<marks[i].length;j++){
                total+=marks[i][j];
            }
            if(total>bestTotal){
                bestTotal=total;
                bestRow=i;
            }
        }
        return "("+bestRow+", "+bestTotal+")";
    }
    public static void main(String[] args){
        int[][] marks={{78,85,90},{88,92,79},{65,70,95}};
        System.out.println(findTopper(marks));
    }
}