package mat.lawfirm.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    @Value("${spring.deliveryMail}")
    private String deliveryMail;

    @Autowired
    public MailService(JavaMailSender javaMailSender)
    {
            this.javaMailSender = javaMailSender;
    }

    public void sendMail(
                         String subject,
                         String text) throws MessagingException
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(deliveryMail);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

}
