package dz3.tt.exceptions;

public class ArticleTypeNotFoundException extends Exception {
    public ArticleTypeNotFoundException() {
        super("Selected article type not found");
    }
}
