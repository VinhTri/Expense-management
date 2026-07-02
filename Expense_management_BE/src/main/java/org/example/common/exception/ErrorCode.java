package org.example.common.exception;


import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_EXISTED(1001, "Tên đăng nhập đã tồn tại!", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1002, "Email đã được sử dụng!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1003, "Không tìm thấy người dùng!", HttpStatus.NOT_FOUND),
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi hệ thống chưa xác định!", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}

