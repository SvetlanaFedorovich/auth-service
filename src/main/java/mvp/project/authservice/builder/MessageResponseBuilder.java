package mvp.project.authservice.builder;

import mvp.project.authservice.model.response.MessageResponse;

public class MessageResponseBuilder {
    public static MessageResponse messageResponseBuild(String code, String status, String description) {
        return MessageResponse.builder()
                .code(code)
                .status(status)
                .description(description)
                .build();
    }
}