package mat.lawfirm.backend.aop;

import lombok.extern.slf4j.Slf4j;
import mat.lawfirm.backend.entity.BlogComment;
import mat.lawfirm.backend.entity.BlogPost;
import mat.lawfirm.backend.service.MailService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Slf4j
@Component
@Aspect
public class MailPostInterceptor {

    private final MailService mailService;

    @Autowired
    public MailPostInterceptor(MailService mailService) {
        this.mailService = mailService;
    }

    @AfterReturning(value = "execution(* mat.lawfirm.backend.dao.BlogComment*+.save(*))", returning = "blogComment")
    public void logAction(BlogComment blogComment) {
        try {
            mailService.sendMail(blogComment);
        } catch (MessagingException messagingException) {
            log.error("BlogComment MessagingException: ", messagingException);
        }
    }

    @AfterReturning(value = "execution(* mat.lawfirm.backend.dao.BlogPost*+.save(*))", returning = "blogPost")
    public void logAction(BlogPost blogPost) {
        try {
            mailService.sendMail(blogPost);
        } catch (MessagingException messagingException) {
            log.error("BlogPost MessagingException: ", messagingException);
        }
    }

}
