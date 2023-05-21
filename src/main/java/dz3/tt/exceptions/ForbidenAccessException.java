package dz3.tt.exceptions;

public class ForbidenAccessException extends Exception{
    public ForbidenAccessException() {
        super("User not allowed to access this endpoint");
    }
}
