package aventurasdemarcoyluis.controller.exeptions;

public class InvalidTransitionException extends Exception{
    public InvalidTransitionException(String message){
        super(message);
    }
}
