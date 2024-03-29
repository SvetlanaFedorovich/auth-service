package mvp.project.authservice.model.response;

import lombok.Builder;

@Builder
public record MessageResponse(
        String code,
        String status,
        String description
) {
}
