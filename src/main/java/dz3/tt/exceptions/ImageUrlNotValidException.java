package dz3.tt.exceptions;

public class ImageUrlNotValidException extends Exception{
    public ImageUrlNotValidException() {
        super("Image URL is not a valid URL");
    }
}