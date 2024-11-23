package fr.chatop.portail.handler;

import fr.chatop.portail.exception.RentalNotFoundException;
import fr.chatop.portail.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RentalNotFoundException.class, UserNotFoundException.class })
    public ProblemDetail handleNotFoundException(Exception ex, WebRequest request) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setInstance(URI.create(request.getContextPath()));
        return problemDetail;
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ProblemDetail handleAuthenticationException(Exception ex, WebRequest request) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problemDetail.setInstance(URI.create(request.getContextPath()));
        return problemDetail;
    }

}
