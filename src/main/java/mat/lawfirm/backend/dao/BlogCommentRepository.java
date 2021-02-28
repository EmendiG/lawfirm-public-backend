package mat.lawfirm.backend.dao;

import mat.lawfirm.backend.entity.BlogComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource
public interface BlogCommentRepository extends JpaRepository<BlogComment, Integer> {

    Page<BlogComment> findByPostId(Integer post, Pageable pageable);
}
