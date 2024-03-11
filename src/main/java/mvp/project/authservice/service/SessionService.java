package mvp.project.authservice.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mvp.project.authservice.client.KeycloakGrpcClient;
import mvp.project.authservice.client.RedisGrpcClient;
import mvp.project.authservice.model.dto.UserCredentialsDto;
import mvp.project.authservice.model.response.KeycloakTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import static mvp.project.authservice.builder.CookieBuilder.cookieBuild;
import static mvp.project.authservice.builder.RedisCookieRequestBuilder.redisCookieRequestBuild;
import static mvp.project.authservice.builder.TokenRequestBuilder.tokenRequestBuild;
import static mvp.project.authservice.builder.TokenResponseBuilder.tokenResponseBuild;


@Service
@RequiredArgsConstructor
public class SessionService {

    private final RedisGrpcClient redisClient;
    private final KeycloakGrpcClient keycloakClient;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.grant-type}")
    private String grantType;

    public String saveSession(String username, String sessionId) {
        return redisClient.saveSessionID(redisCookieRequestBuild(username, sessionId));
    }

    public KeycloakTokenResponse getToken(UserCredentialsDto userCredentials, HttpServletResponse response) {
        KeycloakTokenResponse sessionID = tokenResponseBuild(keycloakClient
                .getToken(tokenRequestBuild(userCredentials, clientId, grantType)));
                    saveSession(userCredentials.getUsername(), sessionID.session_state());
                    setCookieInHeaderResponse(userCredentials, sessionID.session_state(), response);
                    return sessionID;
    }

    public void setCookieInHeaderResponse(UserCredentialsDto userCredentials, String sessionID, HttpServletResponse response) {
        var username = userCredentials.getUsername();
        response.addHeader(HttpHeaders.SET_COOKIE,
                cookieBuild(username, sessionID));
    }
}
