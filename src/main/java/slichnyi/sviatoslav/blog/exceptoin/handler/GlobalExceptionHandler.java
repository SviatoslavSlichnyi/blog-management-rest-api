package slichnyi.sviatoslav.blog.exceptoin.handler;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import slichnyi.sviatoslav.blog.dto.ErrorResponseDTO;
import slichnyi.sviatoslav.blog.exceptoin.NotFoundException;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFoundException(NotFoundException ex) {
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMsg = error.getDefaultMessage();
                    return "'%s' %s".formatted(fieldName, errorMsg);
                }).collect(Collectors.joining(", "));

        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleBadCredentialsException(BadCredentialsException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponseDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("The username or password is incorrect")
                .build();
    }

    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDTO handleAccountStatusException(AccountStatusException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponseDTO.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message("The account is locked")
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDTO handleAccessDeniedException(AccessDeniedException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponseDTO.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message("You are not authorized to access this resource")
                .build();
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDTO handleSignatureException(SignatureException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponseDTO.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message("The JWT signature is invalid")
                .build();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDTO handleExpiredJwtException(ExpiredJwtException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponseDTO.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message("The JWT token has expired")
                .build();
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleGeneralException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}