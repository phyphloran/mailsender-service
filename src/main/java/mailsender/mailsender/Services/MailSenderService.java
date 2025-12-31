package mailsender.mailsender.Services;


import mailsender.mailsender.Requests.CheckCodeRequest;
import mailsender.mailsender.Requests.GetCodeRequest;


public interface MailSenderService {

    void sendCode(GetCodeRequest getCodeRequest);

    boolean checkCode(CheckCodeRequest checkCodeRequest);

}
