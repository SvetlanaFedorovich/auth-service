//package mvp.project.authservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletResponse;
//import mvp.project.authservice.AuthServiceApplication;
//import mvp.project.authservice.client.KeycloakGrpcClient;
//import mvp.project.authservice.client.RedisGrpcClient;
//import mvp.project.authservice.model.dto.UserCredentialsDto;
//import mvp.project.authservice.service.SessionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Optional;
//
//import static mvp.project.authservice.data.TestData.cookieRequest;
//import static mvp.project.authservice.data.TestData.keycloakTokenResponse;
//import static mvp.project.authservice.data.TestData.response;
//import static mvp.project.authservice.data.TestData.token;
//import static mvp.project.authservice.data.TestData.tokenRequest;
//import static mvp.project.authservice.data.TestData.tokenResponse;
//import static mvp.project.authservice.data.TestData.userCredentials;
//import static org.hamcrest.Matchers.any;
//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(AuthController.class)
//class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private SessionService sessionService;
//
//    @Test
//    public void testLogin_Unauthorized() throws Exception {
//        when(sessionService.getToken(userCredentials, response)).thenReturn(keycloakTokenResponse);
//        String body = """
//                  {
//                    "username": "user",
//                    "password": "password"
//                  }
//                """;
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code", equalTo("200")))
//                .andExpect(jsonPath("$.status", equalTo("Успешно!")))
//                .andExpect(jsonPath("$.description", equalTo("Вы успешно авторизовались")));
//    }
//}
//
//
