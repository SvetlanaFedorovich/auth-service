package mvp.project.authservice.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorClientResponseMessage {
    public static final String NOT_SUCCESSFUL = "Не успешно";
    public static final String AUTHORIZATION_ERROR = "Ошибка авторизации, попробуйте снова";
    public static final String CHECK_YOUR_INTERNET_CONNECTION_OR_SERVER = "Проверьте соединение с интернетом или сервером";
    public static final String YOU_HAVE_SUCCESSFULLY_LOGGED_IN = "Вы успешно авторизовались";
    public static final String SUCCESSFULLY = "Успешно!";
}