package mvp.project.authservice.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static mvp.project.authservice.constant.ErrorValidationMessage.FIELD_IS_REQUIRED;
import static mvp.project.authservice.constant.ErrorValidationMessage.INCORRECT_FORMAT_OF_THE_ENTERED_DATA;
import static mvp.project.authservice.constant.ErrorValidationMessage.INSUFFICIENT_PASSWORD_LENGTH;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredentialsDto {

    @Pattern(regexp = "^[a-zA-Z0-9]+$",
            message = INCORRECT_FORMAT_OF_THE_ENTERED_DATA)
    @NotEmpty(message = FIELD_IS_REQUIRED)
    private String username;
    @Pattern(regexp = ".{8,}",
            message = INSUFFICIENT_PASSWORD_LENGTH)
    @NotEmpty(message = FIELD_IS_REQUIRED)
    private String password;
    private String client_id;
    private String grant_type;
    Iterable
}