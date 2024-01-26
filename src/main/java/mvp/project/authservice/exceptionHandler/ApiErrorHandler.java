package mvp.project.authservice.exceptionHandler;

import feign.FeignException;
import mvp.project.authservice.model.response.ErrorValidResponse;
import mvp.project.authservice.model.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static mvp.project.authservice.constant.ErrorClientResponseMessage.AUTHORIZATION_ERROR;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.BD_SERVER_CONNECTION_ERROR;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.CHECK_THE_CORRECTNESS_OF_THE_ENTERED_DATA;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.CHECK_YOUR_INTERNET_CONNECTION_OR_SERVER;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.INCORRECT_USERNAME_OR_PASSWORD;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.REALM_DOES_NOT_EXIST;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.SERVER_CONNECTION_ERROR;


@RestControllerAdvice
public class ApiErrorHandler {


    @ExceptionHandler(FeignException.class)
    private ResponseEntity<MessageResponse> handleException(FeignException e) {
        if (e.status() == HttpStatus.NOT_FOUND.value()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(MessageResponse.builder()
                            .code(String.valueOf(e.status()))
                            .message(REALM_DOES_NOT_EXIST)
                            .description(CHECK_THE_CORRECTNESS_OF_THE_ENTERED_DATA)
                            .build());
        }
        if (e.status() == HttpStatus.UNAUTHORIZED.value()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(MessageResponse.builder()
                            .code(String.valueOf(e.status()))
                            .message(AUTHORIZATION_ERROR)
                            .description(INCORRECT_USERNAME_OR_PASSWORD)
                            .build());
        }
        if (e.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageResponse.builder()
                            .code(String.valueOf(e.status()))
                            .message(BD_SERVER_CONNECTION_ERROR)
                            .description(CHECK_YOUR_INTERNET_CONNECTION_OR_SERVER)
                            .build());
        }
        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body(MessageResponse.builder()
                        .code(String.valueOf(e.status()))
                        .message(SERVER_CONNECTION_ERROR)
                        .description(CHECK_YOUR_INTERNET_CONNECTION_OR_SERVER)
                        .build());
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