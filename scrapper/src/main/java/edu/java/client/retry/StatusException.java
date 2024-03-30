package edu.java.client.retry;

import org.springframework.http.HttpStatus;

public abstract class StatusException extends RuntimeException {
    public abstract HttpStatus getStatus();
}
