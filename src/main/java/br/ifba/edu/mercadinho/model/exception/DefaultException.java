package br.ifba.edu.mercadinho.model.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class DefaultException extends RuntimeException {
    private Integer code;
    private HttpStatus status;
    private LocalDateTime timestamp;

    public DefaultException(HttpStatus status, String message) {
        super(message);
        this.code = status.value();
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
