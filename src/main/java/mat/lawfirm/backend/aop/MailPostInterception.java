package mat.lawfirm.backend.aop;

import mat.lawfirm.backend.entity.BlogComment;
import mat.lawfirm.backend.entity.BlogPost;
import mat.lawfirm.backend.service.CommentMailService;
import mat.lawfirm.backend.service.PostMailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Aspect
public class MailPostInterception {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PostMailService postMailService;

    @Autowired
    private CommentMailService commentMailService;

    @After("execution(* mat.lawfirm.backend.dao.Blog*+.save(*))")
    public void logAction(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            if (tempArg instanceof BlogPost) {
                BlogPost blogPost = (BlogPost) tempArg;
                try {
                    postMailService.sendMail(blogPost);
                } catch (MessagingException messagingException) {
                    System.out.println(messagingException);
                }
            }
            if (tempArg instanceof BlogComment) {
                BlogComment blogComment = (BlogComment) tempArg;
                BlogPost blogPost = entityManager.find(BlogPost.class, blogComment.getPostId());
                try {
                    commentMailService.sendMail(blogComment, blogPost.getTitle() );
                } catch (MessagingException messagingException) {
                    System.out.println(messagingException);
                }
            }
        }

    }
}
