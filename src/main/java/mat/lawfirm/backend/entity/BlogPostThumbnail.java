package mat.lawfirm.backend.entity;

import org.springframework.data.rest.core.config.Projection;

import java.sql.Date;


@Projection(name = "blogPostThumbnail", types = { mat.lawfirm.backend.entity.BlogPost.class })
public interface BlogPostThumbnail {

    Integer getId();
    String getTitle();
    String getCategory();
    String getPictureUrl();
    String getDescription();
    Date getPostTime();

}

