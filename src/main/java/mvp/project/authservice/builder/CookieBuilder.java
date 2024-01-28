package mvp.project.authservice.builder;

import org.springframework.http.ResponseCookie;

import java.time.Duration;

public class CookieBuilder {
    public static String cookieBuild(String nameCookie, String sessionId) {
        return ResponseCookie.from(nameCookie, sessionId)
                .httpOnly(true)
                .maxAge(Duration.ofDays(1))
                .path("/")
                .build().toString();
    }
}