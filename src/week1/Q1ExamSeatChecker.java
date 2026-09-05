package week1;
public class Q1ExamSeatChecker{
    public static void checkDuplicateSeats(int[] seatNumbers){
        boolean duplicateFound=false;
        for(int i=0;i<seatNumbers.length;i++){
            for(int j=i+1;j<seatNumbers.length;j++){
                if(seatNumbers[i]==seatNumbers[j]){
                    System.out.println("Duplicate Seat Number Found: "+seatNumbers[i]);
                    duplicateFound=true;
                    break;
                }
            }
        }
        if(!duplicateFound){
            System.out.println("No Duplicate Seats Found");
        }
    }
    public static void main(String[] args){
        int[] seatNumbers={101,102,103,102,105};
        checkDuplicateSeats(seatNumbers);
        int[] seatNumbers2={101,102,103,104,105};
        checkDuplicateSeats(seatNumbers2);
    }
}