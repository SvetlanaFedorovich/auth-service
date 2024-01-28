package mvp.project.authservice.model.response;

import lombok.Builder;


@Builder
public record TokenResponse (String access_token, String refresh_token, String token_type, String session_state, Integer expires_in) {
}
