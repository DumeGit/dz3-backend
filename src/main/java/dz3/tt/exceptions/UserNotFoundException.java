package dz3.tt.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("No users found in database");
    }
}
