package week2;
public class Q4LibraryISBN{
    public static String normalizeCode(String raw){
        raw=raw.trim();
        return raw.substring(0,3).toUpperCase()+raw.substring(3);
    }
    public static String validateAndFormat(String code){
        if(code.length()!=13){
            return "Invalid: wrong length";
        }
        for(int i=0;i<3;i++){
            if(!Character.isLetter(code.charAt(i))){
                return "Invalid: publisher code must be 3 letters";
            }
        }
        for(int i=3;i<13;i++){
            if(!Character.isDigit(code.charAt(i))){
                return "Invalid: body must contain only digits";
            }
        }
        StringBuilder result=new StringBuilder();
        result.append("[").append(code.substring(0,3)).append("] ");
        result.append("YEAR: ").append(code.substring(3,7));
        result.append(" | CATALOG: ").append(code.substring(7));
        return result.toString();
    }
    public static void main(String[] args){
        String code1=normalizeCode(" pen2026004251 ");
        System.out.println(validateAndFormat(code1));
        String code2=normalizeCode("12N2026004251");
        System.out.println(validateAndFormat(code2));
    }
}