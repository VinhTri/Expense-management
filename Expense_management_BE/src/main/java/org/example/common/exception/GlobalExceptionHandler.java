package org.example.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Xử lý lỗi từ Custom Exception của chúng ta (AppException)
    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        Map<String, Object> response = new HashMap<>();
        response.put("code", errorCode.getCode());
        response.put("message", errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(response);
    }

    // 2. Xử lý lỗi Validation (từ @Valid trong DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        response.put("code", 400);
        response.put("message", "Dữ liệu đầu vào không hợp lệ");
        response.put("details", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 3. Xử lý tất cả các lỗi còn lại chưa biết nguyên nhân (Fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericExceptions(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        response.put("message", ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        response.put("details", ex.getMessage());

        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION.getStatusCode()).body(response);
    }
}
