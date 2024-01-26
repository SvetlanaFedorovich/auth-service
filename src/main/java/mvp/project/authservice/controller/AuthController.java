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
import static mvp.project.authservice.constant.ErrorClientResponseMessage.SUCCESSFULLY;
import static mvp.project.authservice.constant.ErrorClientResponseMessage.YOU_HAVE_SUCCESSFULLY_LOGGED_IN;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final SessionService sessionService;


    @PostMapping("/auth")
    public ResponseEntity<MessageResponse> login(@Validated @RequestBody UserCredentialsDto userCredentials,
                                                 HttpServletResponse response) {
        sessionService.getToken(userCredentials).ifPresent(
                tokenResponse -> {
                    sessionService.setCookieInHeaderResponse(userCredentials, tokenResponse, response);
                    sessionService.saveSession(userCredentials.getUsername(), tokenResponse.session_state());
                });
        return ResponseEntity.ok().body(messageResponseBuild(String.valueOf(HttpStatus.OK.value()), SUCCESSFULLY, YOU_HAVE_SUCCESSFULLY_LOGGED_IN));
    }
}