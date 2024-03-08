package mvp.project.authservice.client;

import io.grpc.Channel;
import mvp.project.authservice.KeycloakServiceGrpc;
import mvp.project.authservice.TokenRequest;
import mvp.project.authservice.TokenResponse;

public class KeycloakGrpcClientTest {
    private final KeycloakServiceGrpc.KeycloakServiceBlockingStub keycloakStub;

    public KeycloakGrpcClientTest(Channel channel) {
        keycloakStub = KeycloakServiceGrpc.newBlockingStub(channel);
    }

    public TokenResponse getToken(TokenRequest tokenRequest) {
        return keycloakStub.getToken(tokenRequest);
    }
}
