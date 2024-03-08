package mvp.project.authservice.client;

import mvp.project.authservice.KeycloakServiceGrpc;
import mvp.project.authservice.TokenRequest;
import mvp.project.authservice.TokenResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;


@Service
public class KeycloakGrpcClient {

    @GrpcClient("keycloak-service")
    private KeycloakServiceGrpc.KeycloakServiceBlockingStub keycloakStub;

    public TokenResponse getToken(TokenRequest tokenRequest) {
        return keycloakStub.getToken(tokenRequest);
    }
}