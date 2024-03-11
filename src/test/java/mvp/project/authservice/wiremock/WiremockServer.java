package mvp.project.authservice.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import mvp.project.authservice.KeycloakServiceGrpc;
import mvp.project.authservice.RedisServiceGrpc;
import mvp.project.authservice.client.KeycloakGrpcClientTest;
import mvp.project.authservice.client.RedisGrpcClientTest;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.wiremock.grpc.GrpcExtensionFactory;
import org.wiremock.grpc.dsl.WireMockGrpcService;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
public class WiremockServer {
    protected static KeycloakGrpcClientTest keycloakGrpcClientTest;
    protected static RedisGrpcClientTest redisGrpcClientTest;
    protected static ManagedChannel channel;
    protected static WireMockGrpcService mockKeycloakGrpcService;
    protected static WireMockGrpcService mockRedisGrpcService;

    public static void beforeAll() {
        channel = ManagedChannelBuilder.forAddress("localhost", wm.getPort()).usePlaintext().build();

        keycloakGrpcClientTest = new KeycloakGrpcClientTest(channel);
        redisGrpcClientTest = new RedisGrpcClientTest(channel);

        mockRedisGrpcService = new WireMockGrpcService(
                new WireMock(wm.getPort()), RedisServiceGrpc.SERVICE_NAME);
        mockKeycloakGrpcService = new WireMockGrpcService(
                new WireMock(wm.getPort()), KeycloakServiceGrpc.SERVICE_NAME);
    }

    public static void afterAll() {
        channel.shutdown();
    }

    @RegisterExtension
    public static WireMockExtension wm =
            WireMockExtension.newInstance()
                    .options(
                            wireMockConfig()
                                    .dynamicPort()
                                    .withRootDirectory("src/test/resources/wiremock")
                                    .extensions(new GrpcExtensionFactory()))
                    .build();
}
