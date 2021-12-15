package aventurasdemarcoyluis.backend.controller.exeptions;

/**
 *  Exception Class depicting the exception generated
 * when trying to use an attack that is not valid
 * due to the specific circumstances it was invoked at.
 *
 * Also used when an attack fails to be executed.
 */
public class InvalidAttackException extends Exception{
    public InvalidAttackException(String message){
        super(message);
    }
}
