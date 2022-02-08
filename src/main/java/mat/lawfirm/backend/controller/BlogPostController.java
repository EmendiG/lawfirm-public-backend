package mat.lawfirm.backend.controller;

import mat.lawfirm.backend.dao.BlogPostRepository;
import mat.lawfirm.backend.entity.BlogPostThumbnail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BlogPostController {

    private final ProjectionFactory projectionFactory;
    private final BlogPostRepository repository;

    @Autowired
    public BlogPostController(BlogPostRepository repository,
                              ProjectionFactory projectionFactory) {
        this.repository = repository;
        this.projectionFactory = projectionFactory;
    }

    @RequestMapping(value = "/api/categories", method = RequestMethod.GET)
    public List<String> findDistinctPostCategories() {
        return this.repository.findDistinctCategories();
    }

    @RequestMapping(value = "/api/findRecentThumbnailsInCategory", method = RequestMethod.GET)
    public Page<?> findRecentThumbnailsInCategory(@RequestParam("category") String category, Pageable pagable) {
        return this.repository.findRecentThumbnailsInCategory(category, pagable)
                .map(post -> projectionFactory.createProjection(BlogPostThumbnail.class, post));
    }

}
