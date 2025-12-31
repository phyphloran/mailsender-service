package mailsender.mailsender.Requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CheckCodeRequest(

        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Code can not be empty")
        String code

) {
}
