package mat.lawfirm.backend.controller;

import mat.lawfirm.backend.dto.Mail;
import mat.lawfirm.backend.service.MailService;
import mat.lawfirm.backend.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/api/sendMail")
    public Mail sendMail(@RequestBody Mail request) {
        var mailBody = "Imię i nazwisko : " + request.getFullName() + "\n" +
                       "Numer telefonu :  " + request.getPhoneNumber() + "\n" +
                       "Adres email :     " + request.getEmail() + "\n" +
                       "Czego dotyczy sprawa: \n" + request.getCaseDescription();
        var calendar = CalendarUtils.getCurrentTimeStamp();
        mailService.sendMail("Kontakt od " + request.getFullName() + " z dnia " + calendar,
                             mailBody);
        return request;
    }

}
