package week8.practice;
import java.util.*;
class Product{
    private String name;
    private double price;
    Product(String name,double price){
        this.name=name;
        this.price=price;
    }
    String getName(){
        return name;
    }
    double getPrice(){
        return price;
    }
}
class ShoppingCustomer{
    private String name;
    ShoppingCustomer(String name){
        this.name=name;
    }
    String getName(){
        return name;
    }
}
interface PaymentMethod{
    boolean processPayment(double amount);
    String getName();
}
class CreditCardPayment implements PaymentMethod{
    public boolean processPayment(double amount){
        return true;
    }
    public String getName(){
        return "Credit Card";
    }
}
class PayPalPayment implements PaymentMethod{
    public boolean processPayment(double amount){
        return false;
    }
    public String getName(){
        return "PayPal";
    }
}
class BankTransferPayment implements PaymentMethod{
    public boolean processPayment(double amount){
        return true;
    }
    public String getName(){
        return "Bank Transfer";
    }
}
class Order{
    enum Status{Pending,Paid}
    private String orderId;
    private ShoppingCustomer customer;
    private Map<Product,Integer> items=new LinkedHashMap<>();
    private Status status=Status.Pending;
    Order(String orderId,ShoppingCustomer customer){
        this.orderId=orderId;
        this.customer=customer;
        System.out.println("Order created for "+customer.getName()+".");
    }
    void addProduct(Product product,int quantity){
        if(quantity>0) items.put(product,items.getOrDefault(product,0)+quantity);
    }
    double getTotal(){
        double total=0;
        for(Product p:items.keySet()) total+=p.getPrice()*items.get(p);
        return total;
    }
    void pay(PaymentMethod method){
        if(items.isEmpty()){
            System.out.println("Cannot process payment for an empty order.");
            return;
        }
        if(status==Status.Paid){
            System.out.println("Order is already paid.");
            return;
        }
        System.out.println("Payment initiated via "+method.getName()+" for Order "+orderId+".");
        if(method.processPayment(getTotal())){
            status=Status.Paid;
            System.out.println("Payment for Order "+orderId+" successful.");
        }else{
            System.out.println("Payment for Order "+orderId+" failed.");
        }
        System.out.println("Order status: "+status);
    }
}
public class Q5{
    public static void main(String[] args){
        ShoppingCustomer x=new ShoppingCustomer("Customer X");
        ShoppingCustomer y=new ShoppingCustomer("Customer Y");
        ShoppingCustomer z=new ShoppingCustomer("Customer Z");
        Product a=new Product("Product A",20);
        Product b=new Product("Product B",30);
        Product c=new Product("Product C",40);
        Order orderX=new Order("X",x);
        orderX.addProduct(a,2);
        orderX.addProduct(b,1);
        orderX.pay(new CreditCardPayment());
        Order orderY=new Order("Y",y);
        orderY.pay(new CreditCardPayment());
        Order orderZ=new Order("Z",z);
        orderZ.addProduct(c,1);
        orderZ.pay(new PayPalPayment());
    }
}