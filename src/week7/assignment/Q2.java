package week7.assignment;
abstract class ArtPiece{
    private static int counter=0;
    private final int pieceId;
    public ArtPiece(){
        counter++;
        pieceId=counter;
    }
    public abstract String describe();
    public int getPieceId(){
        return pieceId;
    }
}
class Painting extends ArtPiece{
    private String title;
    public Painting(String title){
        super();
        this.title=title;
    }
    public String describe(){
        return "Painting: "+title+", framed on canvas";
    }
}
class Sculpture extends ArtPiece{
    private String title;
    public Sculpture(String title){
        super();
        this.title=title;
    }
    public String describe(){
        return "Sculpture: "+title+", carved from stone";
    }
}
public class Q2{
    public static void main(String[] args){
        Painting p=new Painting("Sunset Fields");
        Sculpture s=new Sculpture("The Thinker II");
        System.out.println(p.describe());
        System.out.println(s.describe());
    }
}