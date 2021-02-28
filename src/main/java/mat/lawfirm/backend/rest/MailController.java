package mat.lawfirm.backend.rest;

import mat.lawfirm.backend.dto.Mail;
import mat.lawfirm.backend.service.MailService;
import mat.lawfirm.backend.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@CrossOrigin
@RestController
public class MailController {

    private MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/api/sendMail")
    public Mail sendMail(@RequestBody Mail request) throws MessagingException {

        StringBuilder mailBody = new StringBuilder();
        mailBody.append("Imię i nazwisko : " + request.getFullName() + "\n");
        mailBody.append("Numer telefonu :  " + request.getPhoneNumber() + "\n");
        mailBody.append("Adres email :     " + request.getEmail() + "\n");
        mailBody.append("Czego dotyczy sprawa: \n" + request.getCaseDescription() );

        String calendar = CalendarUtils.ConvertMilliSecondsToFormattedDate(System.currentTimeMillis());

        mailService.sendMail(
                "Kontakt od " + request.getFullName() + " z dnia " + calendar ,
                mailBody.toString()
        );

        return request;
    }
}
