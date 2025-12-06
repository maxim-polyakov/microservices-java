package com.authorization.api.model;

import com.authorization.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * The information required to register a user.
 */
@Data
@Schema(description = "Тело запроса для регистрации нового пользователя")
public class RegistrationBody {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)

    private String username;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")
    private String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[\\w!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{6,}$")
    @Size(min = 6, max = 32)
    private String password;

    @NotNull
    @NotBlank
    @Schema(
        description = "Имя пользователя",
        example = "John"
    )
    private String firstName;

    /** The last name. */
    @NotNull
    @NotBlank
    private String lastName;

    private Role role;
}