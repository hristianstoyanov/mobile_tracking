package mobile.tracking.exception.handling;

import mobile.tracking.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Hristiyan on 10.11.2019 ã..
 */
@ControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUncaughtExceptions(final Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.DEFAULT_ERROR_MESSAGE);
    }
}
