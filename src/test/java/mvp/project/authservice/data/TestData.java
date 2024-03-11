package mvp.project.authservice.data;

import mvp.project.authservice.CookieRequest;
import mvp.project.authservice.CookieResponse;
import mvp.project.authservice.SessionRequest;
import mvp.project.authservice.TokenRequest;
import mvp.project.authservice.TokenResponse;
import mvp.project.authservice.model.dto.UserCredentialsDto;
import mvp.project.authservice.model.response.KeycloakTokenResponse;
import org.apache.commons.lang3.RandomStringUtils;

public class TestData {

    public static String token = RandomStringUtils.randomAlphabetic(20);

    public static UserCredentialsDto userCredentials = UserCredentialsDto.builder()
            .username("user")
            .password("password")
            .build();

    public static TokenResponse tokenResponse = TokenResponse
            .newBuilder()
            .setSessionState(token)
            .build();

    public static KeycloakTokenResponse keycloakTokenResponse = KeycloakTokenResponse.builder()
            .session_state(token)
            .build();
    public static TokenRequest tokenRequest = TokenRequest.newBuilder()
                .setUsername("user")
                .setPassword("password")
                .setClientId("crm-service")
                .setGrantType("password")
                .build();

    public static TokenRequest bedTokenRequest = TokenRequest.newBuilder()
            .setUsername("user")
            .setPassword("password")
            .setClientId("")
            .setGrantType("password")
            .build();

    public static CookieRequest cookieRequest = CookieRequest.newBuilder()
            .setName("user")
            .setValue(token)
            .build();
    public static CookieResponse cookieResponse = CookieResponse.newBuilder()
            .setResult(token)
            .build();
    public static SessionRequest sessionRequest = SessionRequest.newBuilder()
            .setUsername(cookieRequest.getName())
            .build();
    }

