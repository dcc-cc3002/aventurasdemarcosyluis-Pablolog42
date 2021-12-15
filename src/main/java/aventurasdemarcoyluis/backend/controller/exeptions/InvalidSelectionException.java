package aventurasdemarcoyluis.backend.controller.exeptions;

/**
 *  Exception Class depicting the exception generated
 *  when trying to select something fails.
 *  (i.e. any time an invalid choice has been made by the player)
 */
public class InvalidSelectionException extends Exception{
    public InvalidSelectionException(String message){
        super(message);
    }
}
