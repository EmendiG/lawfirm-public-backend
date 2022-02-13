package mat.lawfirm.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="blog_comment")
@Getter
@Setter
public class BlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy="comment", orphanRemoval=true, fetch = FetchType.EAGER)
    private Set<BlogComment> children = new HashSet<>();
    @OneToMany(mappedBy="mainComment", orphanRemoval=true, fetch = FetchType.EAGER)
    private Set<BlogComment> mainChildren = new HashSet<>();

    private String author;

    private String content;

    @CreationTimestamp
    private Date commentTime;

    @ManyToOne(targetEntity = BlogPost.class)
    @JoinColumn(name = "blog_id", insertable = false, updatable = false)
    private BlogPost post;

    @ManyToOne(targetEntity = BlogComment.class)
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private BlogComment comment;

    @ManyToOne(targetEntity = BlogComment.class)
    @JoinColumn(name = "main_parent_id", insertable = false, updatable = false)
    private BlogComment mainComment;

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
