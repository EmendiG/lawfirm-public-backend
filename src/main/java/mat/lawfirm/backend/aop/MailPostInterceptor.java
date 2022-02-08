package mat.lawfirm.backend.aop;

import lombok.extern.slf4j.Slf4j;
import mat.lawfirm.backend.entity.BlogComment;
import mat.lawfirm.backend.entity.BlogPost;
import mat.lawfirm.backend.service.MailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Component
@Aspect
public class MailPostInterceptor {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MailService mailService;

    @After("execution(* mat.lawfirm.backend.dao.Blog*+.save(*))")
    public void logAction(JoinPoint joinPoint) {

        var args = joinPoint.getArgs();

        for (Object tempArg : args) {
            if (tempArg instanceof BlogPost) {
                var blogPost = (BlogPost) tempArg;
                try {
                    mailService.sendMail(blogPost);
                } catch (MessagingException messagingException) {
                    log.error("BlogPost MessagingException: ", messagingException);
                }
            } else if (tempArg instanceof BlogComment) {
                var blogComment = (BlogComment) tempArg;
                var blogPost = entityManager.find(BlogPost.class, blogComment.getPostId());
                try {
                    mailService.sendMail(blogComment, blogPost.getTitle(), blogPost.getId());
                } catch (MessagingException messagingException) {
                    log.error("BlogComment MessagingException: ", messagingException);
                }
            }
        }
    }

}
