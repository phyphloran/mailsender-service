package mailsender.mailsender.ExceptionHandler.Exceptions;


public class IncorrectEmailException extends RuntimeException {

    public IncorrectEmailException(String message) {
        super(message);
    }

}
