package mvp.project.authservice.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mvp.project.authservice.client.KeycloakClient;
import mvp.project.authservice.client.RedisClient;
import mvp.project.authservice.model.dto.UserCredentialsDto;
import mvp.project.authservice.model.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

import static mvp.project.authservice.builder.CookieBuilder.cookieBuild;
import static mvp.project.authservice.builder.UserBuilder.userBuild;


@Service
@RequiredArgsConstructor
public class SessionService {
    private final RedisClient redisClient;
    private final KeycloakClient keycloakClient;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.grant-type}")
    private String grantType;

    public void saveSession(String username, String sessionId) {
        Map.Entry<String, String> cookie = new AbstractMap.SimpleEntry<>(username, sessionId);
        redisClient.saveSessionId(cookie);
    }

    public Optional<TokenResponse> getToken(UserCredentialsDto userCredentials) {
        return keycloakClient.getToken(userBuild(userCredentials, clientId, grantType));
    }

    public void setCookieInHeaderResponse(UserCredentialsDto userCredentials, TokenResponse token, HttpServletResponse response) {
        var username = userCredentials.getUsername();
        var sessionId = token.session_state();
        response.addHeader(HttpHeaders.SET_COOKIE,
                cookieBuild(username, sessionId));
    }
}
