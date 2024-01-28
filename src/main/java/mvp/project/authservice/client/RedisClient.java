package mvp.project.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(
        name = "${spring.data.redis.client-name}",
        url = "${spring.data.redis.host}"
)
@Component
public interface RedisClient {

    @PostMapping("/api/send-sessionId")
    void saveSessionId(@RequestBody Map.Entry<String, String> sessionId);
}