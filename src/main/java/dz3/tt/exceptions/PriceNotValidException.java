package dz3.tt.exceptions;

public class PriceNotValidException extends Exception{
    public PriceNotValidException() {
        super("Price must be larger than 0");
    }
}

