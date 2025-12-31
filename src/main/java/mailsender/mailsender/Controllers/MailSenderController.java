package mailsender.mailsender.Controllers;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mailsender.mailsender.Requests.CheckCodeRequest;
import mailsender.mailsender.Requests.GetCodeRequest;
import mailsender.mailsender.Services.MailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mail")
public class MailSenderController {

    private final MailSenderService mailSender;

    @PostMapping("/getcode")
    public ResponseEntity<?> sendCode(@RequestBody @Valid GetCodeRequest getCodeRequest) {
        mailSender.sendCode(getCodeRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkcode")
    public ResponseEntity<?> check(@RequestBody @Valid CheckCodeRequest checkCodeRequest) {
        mailSender.checkCode(checkCodeRequest);
        return ResponseEntity.noContent().build();
    }

}
