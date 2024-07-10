package com.aladiin.common.error;

import com.aladiin.exception.InvalidDiscountException;
import com.aladiin.exception.NoSuchCouponExistException;
import com.aladiin.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice("com.aladiin.controller")
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDiscountException.class)
    public ResponseEntity<String> InvalidDiscountExceptionHandler(final InvalidDiscountException e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalExceptionHandler(final Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String fieldErrorMessage = fieldErrors.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(CommonResponse.ofFail("400",fieldErrorMessage, null));
    }

    @ExceptionHandler(NoSuchCouponExistException.class)
    public ResponseEntity NoSuchCouponExistExceptionHandler(final NoSuchCouponExistException e) {
        return ResponseEntity.badRequest().body(CommonResponse.ofFail("400",e.getMessage(), null));
    }
}
