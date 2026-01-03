package mailsender.mailsender.Requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record GetCodeRequest(

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email can not be empty")
        @Size(max = 50, message = "The length of the email should be no more than 50")
        String email

) {
}
