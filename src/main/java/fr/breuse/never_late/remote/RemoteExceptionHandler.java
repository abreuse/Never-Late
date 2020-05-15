package fr.breuse.never_late.remote;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RemoteExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException e) {
        return ResponseEntity.status(e.status()).body(e.getMessage());
    }
}
