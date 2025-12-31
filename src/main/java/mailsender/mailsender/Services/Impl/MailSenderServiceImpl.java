package mailsender.mailsender.Services.Impl;



import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import mailsender.mailsender.ExceptionHandler.Exceptions.IncorrectCodeException;
import mailsender.mailsender.Requests.CheckCodeRequest;
import mailsender.mailsender.Requests.GetCodeRequest;
import mailsender.mailsender.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;


@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final SecureRandom secureRandom;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${server.code-life-time}")
    private Long CODE_LIFE_TIME;

    private final JavaMailSender mailSender;

    private final Cache<String, String> verificationCodes;

    @Override
    public void sendCode(GetCodeRequest getCodeRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String email = getCodeRequest.email();
        String code = generateCode();
        mailMessage.setTo(getCodeRequest.email());
        mailMessage.setSubject("Verification Code");
        mailMessage.setText("Your verification code: " + code + "\nValid for " + CODE_LIFE_TIME + " minutes.");
        mailMessage.setFrom(from);
        mailSender.send(mailMessage);
        verificationCodes.put(email, code);
    }

    @Override
    public boolean checkCode(CheckCodeRequest checkCodeRequest) {
        String verifiedCode = verificationCodes.getIfPresent(checkCodeRequest.email());
        if (verifiedCode == null || !verifiedCode.equals(checkCodeRequest.code())) {
            throw new IncorrectCodeException("Incorrect or expired code");
        }
        verificationCodes.invalidate(checkCodeRequest.email());
        return true;
    }


    private String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(secureRandom.nextInt(10));

        }
        return code.toString();
    }

}