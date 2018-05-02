package ftn.isamrs.tim5.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() { }

    public NotFoundException(String message) {
        super(message);
    }
}
