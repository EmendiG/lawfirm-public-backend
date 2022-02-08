package mat.lawfirm.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="blog_post")
@Getter
@Setter
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="category")
    private String category;

    @Column(name="picture_url")
    private String pictureUrl;

    @Column(name="picture_size")
    private String pictureSize;

    @Column(name="picture_description")
    private String pictureDescription;

    @Column(name="description")
    private String description;

    @Column(name="content")
    private String content;

    @Column(name="tags")
    private String tags;

    @Column(name="post_time")
    @CreationTimestamp
    private Date postTime;

    @Column(name="author")
    private String author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogComment> commentIds;

}
