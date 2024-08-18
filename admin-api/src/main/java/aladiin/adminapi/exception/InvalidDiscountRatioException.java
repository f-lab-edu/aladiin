package aladiin.adminapi.exception;

public class InvalidDiscountRatioException extends InvalidDiscountException {
    private static final String message = "할인 비율이 올바르지 않습니다";

    public InvalidDiscountRatioException() {
        super(message);
    }
}
