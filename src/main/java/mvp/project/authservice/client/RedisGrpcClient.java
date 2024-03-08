package mvp.project.authservice.client;

import mvp.project.authservice.CookieRequest;
import mvp.project.authservice.RedisServiceGrpc;
import mvp.project.authservice.SessionRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class RedisGrpcClient {

    @GrpcClient("redis-service")
    private RedisServiceGrpc.RedisServiceBlockingStub redisStub;
    public String saveSessionID(CookieRequest cookieRequest) {
        return redisStub.saveSessionID(cookieRequest).getResult();
    }

    public String getSessionID(SessionRequest sessionRequest) {
        return redisStub.getSessionID(sessionRequest).getResult();
    }
}