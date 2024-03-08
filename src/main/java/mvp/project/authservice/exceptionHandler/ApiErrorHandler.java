package mvp.project.authservice.exceptionHandler;

import io.grpc.StatusRuntimeException;
import mvp.project.authservice.model.response.ErrorValidResponse;
import mvp.project.authservice.model.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static mvp.project.authservice.builder.MessageResponseBuilder.messageResponseBuild;
@RestControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(StatusRuntimeException.class)
    private ResponseEntity<MessageResponse> apiHandleException(StatusRuntimeException e) {
        return ResponseEntity.ok()
                .body(messageResponseBuild(String.valueOf(
                        e.getStatus().getCode().value()),
                        e.getStatus().getCode().name(),
                        e.getStatus().getDescription()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidResponse>> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorValidResponse> responses = e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> ErrorValidResponse.builder()
                        .fieldName(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();
        return new ResponseEntity<>(responses,
                HttpStatus.BAD_REQUEST);
    }

}
