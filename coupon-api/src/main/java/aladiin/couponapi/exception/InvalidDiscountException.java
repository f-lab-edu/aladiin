package aladiin.couponapi.exception;

public abstract class InvalidDiscountException extends Exception {

    public InvalidDiscountException(String message) {
        super(message);
    }
}
