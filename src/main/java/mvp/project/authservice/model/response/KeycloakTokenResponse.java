package mvp.project.authservice.model.response;

import lombok.Builder;


@Builder
public record KeycloakTokenResponse(String session_state) {
}
