package week8.assignment;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class Q2{
    enum Status{
        Submitted,Graded
    }
    static abstract class Assignment{
        String title;
        LocalDate dueDate;
        int maxMarks;
        Assignment(String title,LocalDate dueDate,int maxMarks){
            this.title=title;
            this.dueDate=dueDate;
            this.maxMarks=maxMarks;
        }
        abstract double penaltyRate();
        double applyPenalty(double marks,long lateDays){
            return Math.max(0,marks*(1-penaltyRate()*lateDays));
        }
    }
    static class CodingAssignment extends Assignment{
        CodingAssignment(String title,LocalDate dueDate,int maxMarks){
            super(title,dueDate,maxMarks);
        }
        double penaltyRate(){
            return 0.10;
        }
    }
    static class WrittenAssignment extends Assignment{
        WrittenAssignment(String title,LocalDate dueDate,int maxMarks){
            super(title,dueDate,maxMarks);
        }
        double penaltyRate(){
            return 0.20;
        }
    }
    static class Student{
        String name;
        Map<String,Submission> submissions=new HashMap<>();
        Student(String name){
            this.name=name;
        }
        void submit(Assignment assignment,LocalDate date){
            Submission old=submissions.get(assignment.title);
            if(old!=null&&old.status==Status.Graded){
                System.out.println("Cannot resubmit: '"+assignment.title+"' has already been graded.");
                return;
            }
            if(old==null){
                old=new Submission(this,assignment,date);
                submissions.put(assignment.title,old);
            }
            else{
                old.submissionDate=date;
            }
            long lateDays=Math.max(0,ChronoUnit.DAYS.between(assignment.dueDate,date));
            System.out.println(name+"'s submission for '"+assignment.title+"' received ("+(lateDays==0?"on time":lateDays+" days late")+"). Status: Submitted.");
        }
    }
    static class Submission{
        Student student;
        Assignment assignment;
        LocalDate submissionDate;
        private Status status=Status.Submitted;
        double finalMarks;
        Submission(Student student,Assignment assignment,LocalDate date){
            this.student=student;
            this.assignment=assignment;
            this.submissionDate=date;
        }
        void grade(double marks){
            if(status!=Status.Submitted){
                System.out.println("Submission has already been graded.");
                return;
            }
            if(marks<0||marks>assignment.maxMarks){
                System.out.println("Invalid marks.");
                return;
            }
            long lateDays=Math.max(0,ChronoUnit.DAYS.between(assignment.dueDate,submissionDate));
            finalMarks=assignment.applyPenalty(marks,lateDays);
            status=Status.Graded;
            System.out.printf("%s graded: %.0f/%d",student.name,finalMarks,assignment.maxMarks);
            if(lateDays>0){
                System.out.printf(" after %.0f%% late penalty",assignment.penaltyRate()*lateDays*100);
            }
            System.out.println(". Status: Graded.");
        }
    }
    public static void main(String[] args){
        Assignment coding=new CodingAssignment("Linked List Lab",LocalDate.of(2027,3,10),50);
        Assignment written=new WrittenAssignment("Design Essay",LocalDate.of(2027,3,12),50);
        Student asha=new Student("Asha");
        Student ravi=new Student("Ravi");
        asha.submit(coding,LocalDate.of(2027,3,10));
        ravi.submit(written,LocalDate.of(2027,3,14));
        asha.submissions.get(coding.title).grade(45);
        ravi.submissions.get(written.title).grade(40);
        asha.submit(coding,LocalDate.of(2027,3,11));
    }
}