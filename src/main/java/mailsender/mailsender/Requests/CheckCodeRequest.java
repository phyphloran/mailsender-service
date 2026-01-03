package mailsender.mailsender.Requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CheckCodeRequest(

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email can not be empty")
        @Size(max = 50, message = "The length of the email should be no more than 50")
        String email,

        @NotBlank(message = "Code can not be empty")
        @Size(min = 6, max = 6, message = "The code must be 6 characters long")
        String code

) {
}
