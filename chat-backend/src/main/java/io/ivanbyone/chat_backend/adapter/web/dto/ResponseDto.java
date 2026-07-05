package io.ivanbyone.chat_backend.adapter.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseDto<T> {
    private boolean success;
    private int status;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    private ResponseDto(
            boolean success,
            int status,
            String message,
            T data
    ) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ResponseDto<T> success(T data, int statusCode) {
        return new ResponseDto<>(true, statusCode, "Success", data);
    }

    public static <T> ResponseDto<T> error(String message, int statusCode) {
        return new ResponseDto<>(false, statusCode, message, null);
    }
}
