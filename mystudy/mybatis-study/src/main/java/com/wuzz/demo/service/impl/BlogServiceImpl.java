package com.wuzz.demo.service.impl;

import com.wuzz.demo.associate.AuthorAndBlog;
import com.wuzz.demo.associate.BlogAndAuthor;
import com.wuzz.demo.associate.BlogAndComment;
import com.wuzz.demo.dao.BlogMapper;
import com.wuzz.demo.entity.Blog;
import com.wuzz.demo.service.BlogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Override
    public Blog selectBlogById(Integer bid) {
        return blogMapper.selectBlogById(bid);
    }

    @Override
    public int insertBlog(Blog blog) {
        return 0;
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int insertBlogs(List<Blog> blog) {
        return 0;
    }

    @Override
    public List<BlogAndAuthor> selectBlogWithAuthorResult(Integer bid) {
        return null;
    }

    @Override
    public BlogAndAuthor selectBlogWithAuthorQuery(Integer bid) {
        return null;
    }

    @Override
    public BlogAndComment selectBlogWithCommentById(Integer bid) {
        return null;
    }

    @Override
    public List<AuthorAndBlog> selectAuthorWithBlog() {
        return null;
    }

    @Override
    public List<Blog> selectBlogById2(Blog blog) {
        return null;
    }

    @Override
    public List<Blog> selectBlogList(RowBounds rowBounds) {
        return null;
    }
}
