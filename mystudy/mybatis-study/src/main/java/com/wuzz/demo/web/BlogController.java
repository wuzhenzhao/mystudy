package com.wuzz.demo.web;

import com.wuzz.demo.entity.Blog;
import com.wuzz.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "get",method = RequestMethod.GET)
    private Blog get (Integer bid){
        return blogService.selectBlogById(bid);
    }

    @RequestMapping(value = "update",method = RequestMethod.GET)
    private int update (String name){
        Blog blog3 = new Blog();
        blog3.setBid(1002);
        blog3.setName(name);
        return blogService.updateBlog(blog3);
    }
}
