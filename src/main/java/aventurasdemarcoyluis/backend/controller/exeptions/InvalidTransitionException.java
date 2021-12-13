package aventurasdemarcoyluis.backend.controller.exeptions;

public class InvalidTransitionException extends Exception{
    public InvalidTransitionException(String message){
        super(message);
    }
}
