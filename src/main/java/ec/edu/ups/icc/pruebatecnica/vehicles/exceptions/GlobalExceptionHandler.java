package ec.edu.ups.icc.pruebatecnica.vehicles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ec.edu.ups.icc.pruebatecnica.vehicles.dto.OperationResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public OperationResponseDto handleNotFound(NotFoundException ex) {
        return new OperationResponseDto(ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public OperationResponseDto handleConflict(ConflictException ex) {
        return new OperationResponseDto(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OperationResponseDto handleValidation() {
        return new OperationResponseDto("Invalid request data");
    }
}