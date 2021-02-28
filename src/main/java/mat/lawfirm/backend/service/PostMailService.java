package mat.lawfirm.backend.service;

import mat.lawfirm.backend.entity.BlogPost;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class PostMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.deliveryMail}")
    private String deliveryMail;

    public void sendMail(BlogPost blogPost) throws MessagingException
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(deliveryMail);
        message.setSubject("Post w kategorii " + blogPost.getCategory() + ": " + blogPost.getTitle());
        message.setText(html2text(blogPost.getContent()));
        javaMailSender.send(message);
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

}
