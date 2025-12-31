package mailsender.mailsender.Dtos;


import java.util.List;


public record ErrorDto(

        List<String> errorMessages

) {
}
