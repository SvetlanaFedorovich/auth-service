package mvp.project.authservice.builder;

import mvp.project.authservice.TokenResponse;
import mvp.project.authservice.model.response.KeycloakTokenResponse;

public class TokenResponseBuilder {
    public static KeycloakTokenResponse tokenResponseBuild(TokenResponse response) {
        return KeycloakTokenResponse.builder()
                .session_state(response.getSessionState())
                .build();
    }
}
