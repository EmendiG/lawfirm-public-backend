package mat.lawfirm.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="blog_comment")
@Getter
@Setter
public class BlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToMany(mappedBy="comment",orphanRemoval=true)
    private List<BlogComment> children = new ArrayList<BlogComment>();
    @OneToMany(mappedBy="mainComment",orphanRemoval=true)
    private List<BlogComment> mainChildren = new ArrayList<BlogComment>();

    @Column(name="author")
    private String author;

    @Column(name="content")
    private String content;

    @Column(name="comment_time")
    @CreationTimestamp
    private Date commentTime;

    @ManyToOne(targetEntity = BlogPost.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "blog_id", insertable = false, updatable = false)
    private BlogPost post;

    @Column(name = "blog_id")
    private Integer postId;

    @ManyToOne(targetEntity = BlogComment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private BlogComment comment;

    @Column(name = "comment_id")
    private Integer commentId;

    @ManyToOne(targetEntity = BlogComment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "main_parent_id", insertable = false, updatable = false)
    private BlogComment mainComment;

    @Column(name = "main_parent_id")
    private Integer mainCommentId;

    @JsonIgnore
    public BlogComment getBlogComment() {
        if (comment != null) {
            return comment;
        }
        return null;
    }

    public int getParentId() {
        if (comment != null) {
            return comment.getId();
        }
        return getId();
    }

    @JsonIgnore
    public BlogComment getMainBlogCommment() {
        if (mainComment != null) {
            return mainComment;
        }
        return null;
    }

    public int getMainParentId(){
        if (mainComment != null) {
            return mainComment.getId();
        }
        return getId();
    }

}
