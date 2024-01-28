package mvp.project.authservice.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorValidationMessage {

    public static final String INCORRECT_FORMAT_OF_THE_ENTERED_DATA = "Неверный формат введенных данных";
    public static final String FIELD_IS_REQUIRED = "Поле обязательно для заполнения";
    public static final String INSUFFICIENT_PASSWORD_LENGTH = "Пароль должен содержать не менее 8 символов";
}