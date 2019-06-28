package com.wuzz.demo.associate;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: qingshan
 * @Date: 2019/4/13 19:05
 * @Description: 咕泡学院，只为更好的你
 */
public class AuthorAndBlog implements Serializable {
    Integer authorId; // 作者ID
    String authorName; // 作者名称
    List<BlogAndComment> blog; // 文章和评论列表

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<BlogAndComment> getBlog() {
        return blog;
    }

    public void setBlog(List<BlogAndComment> blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "AuthorAndBlog{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", blog=" + blog +
                '}';
    }
}
