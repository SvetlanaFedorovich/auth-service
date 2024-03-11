package mvp.project.authservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mvp.project.authservice.model.dto.UserCredentialsDto;
import mvp.project.authservice.model.response.MessageResponse;
import mvp.project.authservice.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static mvp.project.authservice.builder.MessageResponseBuilder.messageResponseBuild;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.AUTHORIZATION_ERROR;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.NOT_SUCCESSFUL;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.SUCCESSFULLY;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.YOU_HAVE_SUCCESSFULLY_LOGGED_IN;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final SessionService sessionService;

    @PostMapping("/auth")
    public ResponseEntity<MessageResponse> login(@Validated @RequestBody UserCredentialsDto userCredentials,
                                                 HttpServletResponse response) {
        return sessionService.getToken(userCredentials, response).session_state().isEmpty() ?
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(messageResponseBuild(String.valueOf(HttpStatus.UNAUTHORIZED.value()), NOT_SUCCESSFUL, AUTHORIZATION_ERROR)):
                ResponseEntity.ok()
                        .body(messageResponseBuild(String.valueOf(HttpStatus.OK.value()), SUCCESSFULLY, YOU_HAVE_SUCCESSFULLY_LOGGED_IN));
    }
}