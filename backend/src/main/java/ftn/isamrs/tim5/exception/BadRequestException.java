package ftn.isamrs.tim5.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() { }

    public BadRequestException(String message) {
        super(message);
    }
}
