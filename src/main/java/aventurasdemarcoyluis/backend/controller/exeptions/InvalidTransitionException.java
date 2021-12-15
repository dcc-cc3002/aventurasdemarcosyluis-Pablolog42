package aventurasdemarcoyluis.backend.controller.exeptions;

/**
 *  Exception Class depicting a failed transition between two phases.
 *  Is triggered when the user tries to perform an invalid transition between phases.
 */
public class InvalidTransitionException extends Exception{
    public InvalidTransitionException(String message){
        super(message);
    }
}
