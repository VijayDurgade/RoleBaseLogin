package com.example.roles.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {


    private Long id;

    @NotBlank(message = "firstName.required")
    private String firstName;

    @NotBlank(message = "lastName.required")
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Email(message = "email.invalid")
    @Length(max = 70, message = "{email.length}")
    private String email;
    @NotBlank(message = "mobileNumber.required")
    @Size(min = 10, message = "{mobileNo.length.required}")
    private String mobileNumber;

    @NotBlank
    private String userName;

    @NotBlank(message = "Password.required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;
    @Nullable
    private String physicianName;
}
