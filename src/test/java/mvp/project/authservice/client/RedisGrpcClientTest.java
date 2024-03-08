package mvp.project.authservice.client;

import io.grpc.Channel;
import mvp.project.authservice.CookieRequest;
import mvp.project.authservice.RedisServiceGrpc;
import mvp.project.authservice.SessionRequest;


public class RedisGrpcClientTest {
    private final RedisServiceGrpc.RedisServiceBlockingStub redisStub;

    public RedisGrpcClientTest(Channel channel) {
       redisStub = RedisServiceGrpc.newBlockingStub(channel);
    }

    public String saveSessionID(CookieRequest cookieRequest) {
        return redisStub.saveSessionID(cookieRequest).getResult();
    }

    public String getSessionID(SessionRequest sessionRequest) {
        return redisStub.getSessionID(sessionRequest).getResult();
    }
}
