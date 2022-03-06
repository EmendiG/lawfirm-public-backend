package mat.lawfirm.backend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="blog_post")
@Data
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    private String category;

    private String pictureUrl;

    private String pictureSize;

    private String pictureDescription;

    private String description;

    private String content;

    private String tags;

    @CreationTimestamp
    private Date postTime;

    private String author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlogComment> commentIds;

}
