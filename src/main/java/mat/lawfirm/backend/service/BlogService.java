package mat.lawfirm.backend.service;

import mat.lawfirm.backend.dao.BlogPostRepository;
import mat.lawfirm.backend.entity.BlogPostThumbnail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final ProjectionFactory projectionFactory;
    private final BlogPostRepository repository;

    @Autowired
    public BlogService(BlogPostRepository repository,
                              ProjectionFactory projectionFactory) {
        this.repository = repository;
        this.projectionFactory = projectionFactory;
    }

    public List<String> findDistinctPostCategories() {
        return this.repository.findDistinctCategories();
    }

    public Page<?> findRecentThumbnailsInCategory(String category, Pageable pageable) {
        return this.repository.findRecentThumbnailsInCategory(category, pageable)
                .map(post -> projectionFactory.createProjection(BlogPostThumbnail.class, post));
    }

}
