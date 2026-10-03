package week8.practice;
import java.util.*;
abstract class Question{
    protected String question;
    protected int marks;
    Question(String question,int marks){
        this.question=question;
        this.marks=marks;
    }
    abstract boolean evaluate(String answer);
    String getQuestion(){
        return question;
    }
    int getMarks(){
        return marks;
    }
}
class MultipleChoiceQuestion extends Question{
    private String correctOption;
    MultipleChoiceQuestion(String question,int marks,String correctOption){
        super(question,marks);
        this.correctOption=correctOption;
    }
    boolean evaluate(String answer){
        return correctOption.equalsIgnoreCase(answer);
    }
}
class TrueFalseQuestion extends Question{
    private boolean correctAnswer;
    TrueFalseQuestion(String question,int marks,boolean correctAnswer){
        super(question,marks);
        this.correctAnswer=correctAnswer;
    }
    boolean evaluate(String answer){
        return Boolean.toString(correctAnswer).equalsIgnoreCase(answer);
    }
}
class ShortAnswerQuestion extends Question{
    private String correctAnswer;
    ShortAnswerQuestion(String question,int marks,String correctAnswer){
        super(question,marks);
        this.correctAnswer=correctAnswer;
    }
    boolean evaluate(String answer){
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}
class Examination{
    private String name;
    private ArrayList<Question> questions=new ArrayList<>();
    Examination(String name){
        this.name=name;
    }
    void addQuestion(Question question){
        questions.add(question);
    }
    String getName(){
        return name;
    }
    ArrayList<Question> getQuestions(){
        return questions;
    }
}
class Student{
    private String name;
    Student(String name){
        this.name=name;
    }
    String getName(){
        return name;
    }
}
class Attempt{
    private Student student;
    private Examination exam;
    private Map<Question,String> answers=new LinkedHashMap<>();
    private boolean submitted=false;
    Attempt(Student student,Examination exam){
        this.student=student;
        this.exam=exam;
    }
    void answer(Question question,String answer){
        if(submitted){
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        if(exam.getQuestions().contains(question)){
            answers.put(question,answer);
            System.out.println("Answer recorded for "+question.getQuestion()+".");
        }
    }
    void submit(){
        if(submitted){
            System.out.println("Examination already submitted.");
            return;
        }
        submitted=true;
        int score=0,total=0;
        System.out.println(exam.getName()+" submitted by "+student.getName()+".");
        for(Question q:exam.getQuestions()){
            total+=q.getMarks();
            boolean correct=q.evaluate(answers.getOrDefault(q,""));
            int earned=correct?q.getMarks():0;
            score+=earned;
            System.out.println(q.getQuestion()+": "+(correct?"Correct":"Incorrect")+" ("+earned+" points)");
        }
        System.out.println("Total score: "+score+"/"+total);
    }
}
public class Q3{
    public static void main(String[] args){
        Student student=new Student("Student 1");
        Examination exam=new Examination("Exam A");
        Question q1=new MultipleChoiceQuestion("Question 1",5,"C");
        Question q2=new TrueFalseQuestion("Question 2",5,false);
        exam.addQuestion(q1);
        exam.addQuestion(q2);
        Attempt attempt=new Attempt(student,exam);
        System.out.println(exam.getName()+" started by "+student.getName()+".");
        attempt.answer(q1,"C");
        attempt.answer(q2,"True");
        attempt.submit();
        attempt.answer(q1,"A");
    }
}