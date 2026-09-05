package week4;
class Payment{
    public void pay(double amount){
        System.out.println("Paid (cash): Rs "+amount);
    }
}
class CardPayment extends Payment{
    public void payWithProcessingFee(double amount){
        System.out.println("Charged (card, incl. fee): Rs "+(amount * 1.02));
    }
}
public class A5PaymentDispatch{
    public static void processTransaction(Payment payment,double amount){
        if (payment instanceof CardPayment){
            ((CardPayment) payment).payWithProcessingFee(amount);
        }
        else{
            payment.pay(amount);
        }
    }
    public static void main(String[] args){
        Payment[] payments={new CardPayment(),new Payment(),new CardPayment(),new Payment(),new CardPayment()};
        double[] amounts={100, 50, 200, 75, 120};
        double totalCollected=0;
        for (int i=0;i<payments.length;i++){
            processTransaction(payments[i],amounts[i]);
            if (payments[i] instanceof CardPayment){
                totalCollected+=amounts[i]*1.02;
            }
            else{
                totalCollected+=amounts[i];
            }
        }
        System.out.println("Total Collected: Rs "+totalCollected);
    }
}