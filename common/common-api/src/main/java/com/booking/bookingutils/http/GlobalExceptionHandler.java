package com.booking.bookingutils.http;

import com.booking.bookingutils.exception.InvalidInputException;
import com.booking.bookingutils.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody ErrorResponse handleNotFoundExceptions(NotFoundException notFoundException) {
        return createHttpErrorInfo(NOT_FOUND, null, notFoundException);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverExceptionHandler(Exception ex) {
        return ex.getMessage();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody ErrorResponse handleConstraintViolationException(ServerHttpRequest request, Exception ex) {
        return createHttpErrorInfo(BAD_REQUEST, request, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody ErrorResponse handleInvalidInputException(InvalidInputException ex) {
        return createHttpErrorInfo(BAD_REQUEST, null, ex);
    }

    private ErrorResponse createHttpErrorInfo(
            HttpStatus httpStatus, ServerHttpRequest request, Exception ex) {
        final var path = request.getPath().pathWithinApplication().value();
        final var message = ex.getMessage();

        log.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
        return new ErrorResponse(httpStatus, message, path);
    }

}