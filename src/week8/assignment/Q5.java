package week8.assignment;
import java.util.*;
public class Q5{
    interface NotificationChannel{
        void send(Student student,Notice notice);
    }
    static class EmailChannel implements NotificationChannel{
        public void send(Student student,Notice notice){
            System.out.println("[Email → "+student.name+"] "+notice.title);
        }
    }
    static class SmsChannel implements NotificationChannel{
        public void send(Student student,Notice notice){
            System.out.println("[SMS → "+student.name+"] "+notice.title);
        }
    }
    static class AppChannel implements NotificationChannel{
        public void send(Student student,Notice notice){
            System.out.println("[App → "+student.name+"] "+notice.title);
        }
    }
    static class Student{
        String name;
        String department;
        Set<NotificationChannel> channels=new LinkedHashSet<>();
        Student(String name,String department){
            this.name=name;
            this.department=department;
        }
        void addChannel(NotificationChannel channel){
            channels.add(channel);
        }
    }
    static class Notice{
        String title;
        Set<String> departments;
        Notice(String title,Set<String> departments){
            this.title=title;
            this.departments=new HashSet<>(departments);
        }
        boolean isValid(){
            return title!=null&&!title.trim().isEmpty()&&departments!=null&&!departments.isEmpty();
        }
    }
    static class NoticeBoard{
        List<Student> students=new ArrayList<>();
        void addStudent(Student student){
            students.add(student);
        }
        void post(Notice notice){
            if(!notice.isValid()){
                System.out.println("Cannot post notice: "+(notice.title==null||notice.title.trim().isEmpty()?"A title is required.":"At least one target department is required."));
                return;
            }
            System.out.println("Notice '"+notice.title+"' posted to "+String.join(", ",notice.departments)+".");
            for(Student student:students){
                if(notice.departments.contains(student.department)){
                    for(NotificationChannel channel:student.channels){
                        channel.send(student,notice);
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        NoticeBoard board=new NoticeBoard();
        Student asha=new Student("Asha","CSE");
        Student ravi=new Student("Ravi","ECE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());
        ravi.addChannel(new SmsChannel());
        board.addStudent(asha);
        board.addStudent(ravi);
        board.post(new Notice("Lab Closed Tomorrow",Set.of("CSE")));
        board.post(new Notice("Fee Deadline Extended",Set.of("CSE","ECE")));
        board.post(new Notice("Sports Day",Set.of()));
    }
}