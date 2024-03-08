package mvp.project.authservice.service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import mvp.project.authservice.wiremock.WiremockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.wiremock.grpc.dsl.WireMockGrpc;

import static mvp.project.authservice.constant.ErrorClientResponseMessage.CHECK_YOUR_INTERNET_CONNECTION_OR_SERVER;
import static mvp.project.authservice.data.TestData.bedTokenRequest;
import static mvp.project.authservice.data.TestData.cookieRequest;
import static mvp.project.authservice.data.TestData.cookieResponse;
import static mvp.project.authservice.data.TestData.sessionRequest;
import static mvp.project.authservice.data.TestData.tokenRequest;
import static mvp.project.authservice.data.TestData.tokenResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.wiremock.grpc.dsl.WireMockGrpc.equalToMessage;
import static org.wiremock.grpc.dsl.WireMockGrpc.message;
import static org.wiremock.grpc.dsl.WireMockGrpc.method;

public class SessionServiceTest extends WiremockServer {

    @Test
    void whenGetTokenInvoked_thenExpectTokenIsReturned() {
        mockKeycloakGrpcService.stubFor(
                method("getToken")
                        .withRequestMessage(equalToMessage(tokenRequest))
                        .willReturn(message(tokenResponse)));
        assertThat(keycloakGrpcClientTest.getToken(tokenRequest)).isEqualTo(tokenResponse);
        StatusRuntimeException exception =  assertThrows(StatusRuntimeException.class,
                () -> keycloakGrpcClientTest.getToken(bedTokenRequest));
        assertThat(exception.getStatus().getCode()).isEqualTo(Status.NOT_FOUND.getCode());
    }
    @Test
    void whenGetTokenInvoked_thenExpectNetworkErrorConnect() {
        mockKeycloakGrpcService.stubFor(
                method("getToken")
                        .withRequestMessage(equalToMessage(tokenRequest))
                        .willReturn(WireMockGrpc.Status.UNAVAILABLE, CHECK_YOUR_INTERNET_CONNECTION_OR_SERVER));
        StatusRuntimeException exception = assertThrows(StatusRuntimeException.class, () -> keycloakGrpcClientTest.getToken(tokenRequest));
        assertThat(exception.getStatus().getCode()).isEqualTo(Status.UNAVAILABLE.getCode());
    }
    @Test
    void whenSaveTokenInvoked_thenExpectSessionIDIsReturned() {
        mockRedisGrpcService.stubFor(
                method("saveSessionID")
                        .willReturn(message(cookieResponse)));
        mockRedisGrpcService.stubFor(
                method("getSessionID")
                        .withRequestMessage(equalToMessage(sessionRequest))
                        .willReturn(message(cookieResponse)));
        assertThat(redisGrpcClientTest.saveSessionID(cookieRequest))
                .isEqualTo(redisGrpcClientTest.getSessionID(sessionRequest));
    }

    @BeforeAll
    public static void init(){
        beforeAll();
    }
    @AfterAll
    public static void shoutDown(){
        afterAll();
    }
}
