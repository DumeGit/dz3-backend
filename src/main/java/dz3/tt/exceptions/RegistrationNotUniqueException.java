package dz3.tt.exceptions;

public class RegistrationNotUniqueException extends Exception{
    public RegistrationNotUniqueException() {
        super("Email or username not unique");
    }
}
