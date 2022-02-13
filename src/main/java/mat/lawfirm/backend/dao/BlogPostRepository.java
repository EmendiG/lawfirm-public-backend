package mat.lawfirm.backend.dao;

import mat.lawfirm.backend.entity.BlogPost;
import mat.lawfirm.backend.entity.BlogPostThumbnail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@CrossOrigin
@RepositoryRestResource
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

    @Query("SELECT  blog.id, blog.title, blog.category , blog.pictureUrl, blog.description, blog.postTime " +
            "FROM BlogPost AS blog " +
            "ORDER BY blog.id DESC")
    Page<BlogPostThumbnail> findRecentThumbnails(Pageable pageable);

    @Query("SELECT blog.id as id, blog.title as title, blog.category as category, blog.pictureUrl as pictureUrl, " +
            "blog.description as description, blog.postTime as postTime " +
            "FROM BlogPost AS blog " +
            "WHERE blog.category = ?1 " +
            "ORDER BY blog.id DESC")
    Page<BlogPostThumbnail> findRecentThumbnailsInCategory(String category, Pageable pageable);

    @Query("SELECT DISTINCT blog.category FROM BlogPost blog")
    Set<String> findDistinctCategories();

}