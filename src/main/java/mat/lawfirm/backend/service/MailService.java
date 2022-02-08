package mat.lawfirm.backend.service;

import mat.lawfirm.backend.entity.BlogComment;
import mat.lawfirm.backend.entity.BlogPost;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.deliveryMail}")
    private String deliveryMail;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String subject, String text) {
        var message = new SimpleMailMessage();
        message.setTo(deliveryMail);
        message.setSubject(subject);
        message.setText(text);
        this.sendMail(message);
    }

    @Async
    public void sendMail(BlogComment blogComment,
                         String postTitle,
                         Integer blogPostId)
            throws MessagingException {
        var message = new SimpleMailMessage();
        message.setTo(deliveryMail);
        message.setSubject("Komentarz dodany w \"" + postTitle + "\" przez \"" + blogComment.getAuthor() + "\"");
        message.setText(blogComment.getContent() + "\n\nUrl: \n" +
                "https://adwokatmalgorzatadus.pl/blog/"+ blogPostId);
        this.sendMail(message);
    }

    public void sendMail(BlogPost blogPost)
            throws MessagingException {
        var message = new SimpleMailMessage();
        message.setTo(deliveryMail);
        message.setSubject("Post w kategorii " + blogPost.getCategory() + ": " + blogPost.getTitle());
        message.setText(html2text(blogPost.getContent()) +
                "\n\nUrl: \n" +
                "https://adwokatmalgorzatadus.pl/blog/"+ blogPost.getId());
        this.sendMail(message);
    }

    private static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    private void sendMail(SimpleMailMessage message) {
        javaMailSender.send(message);
    }

}
