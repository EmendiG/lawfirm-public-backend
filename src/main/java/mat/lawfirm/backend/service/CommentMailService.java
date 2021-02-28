package mat.lawfirm.backend.service;

import mat.lawfirm.backend.entity.BlogComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@EnableAsync
@Service
public class CommentMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.deliveryMail}")
    private String deliveryMail;

    @Async
    public void sendMail(BlogComment blogComment, String postTitle) throws MessagingException
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(deliveryMail);
        message.setSubject( "Komentarz dodany w \"" + postTitle + "\" przez \"" + blogComment.getAuthor() + "\"" );
        message.setText( blogComment.getContent() );
        javaMailSender.send(message);
    }

}
