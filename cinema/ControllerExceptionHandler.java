package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoSeatException.class)
    public ResponseEntity<ErrorResponse> handleNoSeatException(NoSeatException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatAvailabilityException.class)
    public ResponseEntity<ErrorResponse> handleSeatAvailabilityException(SeatAvailabilityException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("The ticket has been already purchased!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ErrorResponse> handleTokenException(TokenException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("Wrong token!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<ErrorResponse> handlePasswordException(PasswordException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("The password is wrong!");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
