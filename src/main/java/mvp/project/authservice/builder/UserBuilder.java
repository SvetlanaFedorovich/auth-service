package mvp.project.authservice.builder;

import mvp.project.authservice.model.dto.UserCredentialsDto;

public class UserBuilder {

    public static UserCredentialsDto userBuild(UserCredentialsDto userDto, String clientId, String grantType) {
        return UserCredentialsDto.builder()
                .client_id(clientId)
                .grant_type(grantType)
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }
}