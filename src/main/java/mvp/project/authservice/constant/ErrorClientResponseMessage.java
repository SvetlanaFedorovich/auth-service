package mvp.project.authservice.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorClientResponseMessage {
    public static final String REALM_DOES_NOT_EXIST = "Ошибка";
    public static final String AUTHORIZATION_ERROR = "Ошибка авторизации";
    public static final String INCORRECT_USERNAME_OR_PASSWORD = "Неправильный логин или пароль";
    public static final String CHECK_THE_CORRECTNESS_OF_THE_ENTERED_DATA = "Проверьте правильность введенных данных";
    public static final String SERVER_CONNECTION_ERROR = "Ошибка подключения";
    public static final String CHECK_YOUR_INTERNET_CONNECTION_OR_SERVER = "Проверьте соединение с интернетом или сервером";
    public static final String YOU_HAVE_SUCCESSFULLY_LOGGED_IN = "Вы успешно авторизовались";
    public static final String SUCCESSFULLY = "Успешно!";
    public static final String BD_SERVER_CONNECTION_ERROR = "Ошибка подключения к серверу базы данных";
}