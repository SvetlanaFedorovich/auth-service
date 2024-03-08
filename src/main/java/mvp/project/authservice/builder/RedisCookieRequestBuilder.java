package mvp.project.authservice.builder;

import mvp.project.authservice.CookieRequest;

public class RedisCookieRequestBuilder {

    public static CookieRequest redisCookieRequestBuild(String username, String sessionId) {
        return CookieRequest.newBuilder()
                .setName(username)
                .setValue(sessionId)
                .build();
    }
}
