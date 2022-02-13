package mat.lawfirm.backend.controller;

import mat.lawfirm.backend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
public class BlogPostController {

    private final BlogService service;

    @Autowired
    public BlogPostController(BlogService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/categories", method = RequestMethod.GET)
    public Set<String> findDistinctPostCategories() {
        return this.service.findDistinctPostCategories();
    }

    @RequestMapping(value = "/api/findRecentThumbnailsInCategory", method = RequestMethod.GET)
    public Page<?> findRecentThumbnailsInCategory(@RequestParam("category") String category, Pageable pageable) {
        return this.service.findRecentThumbnailsInCategory(category, pageable);
    }

}
