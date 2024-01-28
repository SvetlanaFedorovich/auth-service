package mvp.project.authservice.model.response;

import lombok.Builder;

@Builder
public record ErrorValidResponse(String fieldName, String message) {
}
