package aventurasdemarcoyluis.backend.controller.exeptions;

public class InvalidAttackException extends Exception{
    public InvalidAttackException(String message){
        super(message);
    }
}
