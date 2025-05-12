package bj.formation.demoprojet.exceptions;


import bj.formation.demoprojet.handlers.HttpResponseHandler;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private  final HttpServletRequest requestUrl;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (a, b) -> a));

        return HttpResponseHandler.generateResponse(
                false,
                "Erreur de validation",
                HttpStatus.BAD_REQUEST,
                null,
                errors,
                requestUrl.getRequestURI()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {

        return HttpResponseHandler.generateResponse(
                false,
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                null,
                null,
                requestUrl.getRequestURI()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleInvalidJsonException(HttpMessageNotReadableException ex) {
        return HttpResponseHandler.generateResponse(
                false,
                "Format JSON invalide",
                HttpStatus.BAD_REQUEST,
                null,
                null,
                requestUrl.getRequestURI()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return HttpResponseHandler.generateResponse(
                false,
                "Accès refusé",
                HttpStatus.FORBIDDEN,
                null,
                null,
                requestUrl.getRequestURI()
        );
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex) {
        return HttpResponseHandler.generateResponse(
                false,
                "L'URL de la requête est invalide ou un ou plusieurs paramètres sont vides.",
                HttpStatus.FORBIDDEN,
                null,
                null,
                requestUrl.getRequestURI()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return HttpResponseHandler.generateResponse(
                false,
                ex.getMessage(),
                HttpStatus.FORBIDDEN,
                null,
                null,
                requestUrl.getRequestURI()
        );
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {

        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("exception", ex.getClass().getSimpleName());
        errorDetails.put("message", ex.getMessage());

        return HttpResponseHandler.generateResponse(
                false,
                "Une erreur interne est survenue",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                errorDetails,
                requestUrl.getRequestURI()
        );
    }
}
