package framework.driver.exception;

public class WrongWebDriverException extends RuntimeException{

    public WrongWebDriverException(String message) {
        super(message);
    }
}
