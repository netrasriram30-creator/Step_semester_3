package week5;
public class Q2{
    public static String classifyAccess(String fieldModifier,String accessorContext){
        if(fieldModifier.equals("private")){
            if(accessorContext.equals("SAME_CLASS")){
                return "ALLOWED";
            }
        }
        else if(fieldModifier.equals("default")){
            if(accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE")){
                return "ALLOWED";
            }
        }
        else if(fieldModifier.equals("protected")){
            if(accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE")||accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")){
                return "ALLOWED";
            }
        }
        else if(fieldModifier.equals("public")){
            return "ALLOWED";
        }
        return "DENIED";
    }
    public static String firstDeniedAttempt(String[][] attempts){
        for(int i=0;i<attempts.length;i++){
            String modifier=attempts[i][0];
            String context=attempts[i][1];
            if(classifyAccess(modifier,context).equals("DENIED")){
                return modifier+" via "+context+" (attempt #"+(i+1)+")";
            }
        }
        return "None Denied";
    }
    public static void main(String[] args){
        String[][] attempts1={
                {"public","SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected","SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(attempts1));
        String[][] attempts2={
                {"public","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
                {"protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(attempts2));
    }
}