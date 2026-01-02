package mailsender.mailsender.ExceptionHandler;


//import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import mailsender.mailsender.Dtos.ErrorDto;
import mailsender.mailsender.ExceptionHandler.Exceptions.IncorrectCodeException;
import mailsender.mailsender.ExceptionHandler.Exceptions.IncorrectEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
//@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> constraintViolationException(ConstraintViolationException exception) {
        List<String> errorMessages = exception.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ErrorDto(errorMessages));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(new ErrorDto(errorMessages));
    }

    @ExceptionHandler(IncorrectEmailException.class)
    public ResponseEntity<ErrorDto> incorrectEmailException(IncorrectEmailException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(List.of(exception.getMessage())));
    }

    @ExceptionHandler(IncorrectCodeException.class)
    public ResponseEntity<ErrorDto> incorrectCodeException(IncorrectCodeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(List.of(exception.getMessage())));
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<ErrorDto> mailSendException(MailSendException exception) {
        log.error("Mail error: {}", exception.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(List.of("Unexpected error")));
    }

}
