package week8.assignment;
public class Q1{
    interface WashType{
        int getDuration();
        double getCharge();
        String getName();
    }
    static class QuickWash implements WashType{
        public int getDuration(){
            return 30;
        }
        public double getCharge(){
            return 20;
        }
        public String getName(){
            return "Quick";
        }
    }
    static class NormalWash implements WashType{
        public int getDuration(){
            return 45;
        }
        public double getCharge(){
            return 30;
        }
        public String getName(){
            return "Normal";
        }
    }
    static class HeavyWash implements WashType{
        public int getDuration(){
            return 60;
        }
        public double getCharge(){
            return 45;
        }
        public String getName(){
            return "Heavy";
        }
    }
    static class Student{
        String name;
        Student(String name){
            this.name=name;
        }
    }
    static class WashCycle{
        Student student;
        WashingMachine machine;
        WashType type;
        WashCycle(Student student,WashingMachine machine,WashType type){
            this.student=student;
            this.machine=machine;
            this.type=type;
        }
    }
    static class WashingMachine{
        String id;
        private WashCycle currentCycle;
        WashingMachine(String id){
            this.id=id;
        }
        boolean isFree(){
            return currentCycle==null;
        }
        void startWash(Student student,WashType type){
            if(!isFree()){
                System.out.println("Machine "+id+" is currently busy.");
                return;
            }
            currentCycle=new WashCycle(student,this,type);
            System.out.println(type.getName()+" wash started on "+id+" for "+student.name+" ("+type.getDuration()+" min). Charge: ₹"+String.format("%.2f",type.getCharge())+".");
        }
        void completeWash(){
            if(isFree()){
                System.out.println(id+" has no active cycle.");
                return;
            }
            System.out.println(id+" cycle completed. "+id+" is now free.");
            currentCycle=null;
        }
    }
    public static void main(String[] args){
        WashingMachine m1=new WashingMachine("M1");
        WashingMachine m2=new WashingMachine("M2");
        Student asha=new Student("Asha");
        Student ravi=new Student("Ravi");
        Student neha=new Student("Neha");
        m1.startWash(asha,new QuickWash());
        m1.startWash(ravi,new HeavyWash());
        m2.startWash(ravi,new HeavyWash());
        m1.completeWash();
        m1.startWash(neha,new NormalWash());
    }
}