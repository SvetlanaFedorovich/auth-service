package mvp.project.authservice.builder;

import mvp.project.authservice.TokenRequest;
import mvp.project.authservice.model.dto.UserCredentialsDto;

public class TokenRequestBuilder {
    public static TokenRequest tokenRequestBuild(UserCredentialsDto userDto, String clientId, String grantType) {
        return TokenRequest.newBuilder()
                .setUsername(userDto.getUsername())
                .setPassword(userDto.getPassword())
                .setClientId(clientId)
                .setGrantType(grantType)
                .build();
    }
}
