package dz3.tt.exceptions;

public class ArticleNotFoundException extends Exception{
    public ArticleNotFoundException() {
        super("Selected article not found");
    }
}
