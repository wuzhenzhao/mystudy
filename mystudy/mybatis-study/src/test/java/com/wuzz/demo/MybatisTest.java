package com.wuzz.demo;

import com.wuzz.demo.associate.AuthorAndBlog;
import com.wuzz.demo.associate.BlogAndAuthor;
import com.wuzz.demo.associate.BlogAndComment;
import com.wuzz.demo.dao.BlogMapper;
import com.wuzz.demo.entity.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/6/27
 * Time: 14:57
 * Description 描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class MybatisTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void testSelectBlogById(){
        Blog blog = blogMapper.selectBlogById(1001);
        System.out.println(blog);
    }

    @Test
    public void testInsertBlog(){
        //主键自增返回测试
        Blog blog = new Blog();
        blog.setAuthorId(1001);
        blog.setName("activemq源码分析");
        blogMapper.insertBlog(blog);
        System.out.println(blog.getBid());
    }

    @Test
    public void testInsertBlogs(){
        List<Blog> list=new ArrayList<Blog>();
        //主键自增返回测试
        Blog blog = new Blog();
        blog.setAuthorId(1001);
        blog.setName("批量1");

        Blog blog2 = new Blog();
        blog2.setAuthorId(1001);
        blog2.setName("批量2");

        list.add(blog);
        list.add(blog2);
        blogMapper.insertBlogs(list);
        System.out.println(blog.getBid());
        System.out.println(blog2.getBid());
    }

    @Test
    public void testSelectBlogWithAuthorResult(){
        List<BlogAndAuthor> list = blogMapper.selectBlogWithAuthorResult(1001);
        System.out.println(list);

    }

    @Test
    public void testSelectBlogWithAuthorQuery(){
        BlogAndAuthor blogAndAuthor = blogMapper.selectBlogWithAuthorQuery(1001);
        //mybatis.configuration.lazy-loading-enabled=true 可以开启延时加载
        //mybatis.configuration.aggressive-lazy-loading=true
//        System.out.println(blogAndAuthor);
        System.out.println(blogAndAuthor.getBid());

    }

    @Test
    public void testSelectBlogWithCommentById(){
        BlogAndComment blogAndComment = blogMapper.selectBlogWithCommentById(1001);
        System.out.println(blogAndComment);

    }

    @Test
    public void testSelectAuthorWithBlog(){
        List<AuthorAndBlog> authorAndBlogs = blogMapper.selectAuthorWithBlog();
        System.out.println(authorAndBlogs);

    }

    @Test
    public void testSelectBlogById2(){
        //主键自增返回测试
        Blog blog = new Blog();
//        blog.setBid(1002);
        blog.setName("吴振照");
        Blog blog1 = blogMapper.selectBlogById2(blog);
        System.out.println(blog1);
    }
}
