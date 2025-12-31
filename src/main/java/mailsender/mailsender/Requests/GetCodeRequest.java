package mailsender.mailsender.Requests;


import jakarta.validation.constraints.Email;


public record GetCodeRequest(

        @Email(message = "Email should be valid")
        String email

) {
}
