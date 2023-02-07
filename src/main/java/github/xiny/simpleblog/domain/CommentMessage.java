package github.xiny.simpleblog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class CommentMessage extends Comment{

    private String account;

    private String blogTitle;

    private String commentUserName;

    private String avatar;

    public CommentMessage(Integer id, String context, Integer commentBlogId, Integer commentUserId, Integer commentCommentId, Date updateTime, String account, String blogTitle, String commentUserName,Integer isDelete,String avatar) {
        super(id, context, commentBlogId, commentUserId, commentCommentId, updateTime,isDelete);
        this.account = account;
        this.blogTitle = blogTitle;
        this.commentUserName = commentUserName;
        this.avatar = avatar;
    }

    public CommentMessage(Integer id, String context, Integer commentBlogId, Integer commentUserId, Date updateTime, String account, String blogTitle,Integer isDelete,String avatar) {
        super(id, context, commentBlogId, commentUserId, updateTime,isDelete);
        this.account = account;
        this.blogTitle = blogTitle;
        this.avatar = avatar;

    }
    public CommentMessage(Integer id, String context, Integer commentBlogId, Integer commentUserId, Date updateTime, String account, String blogTitle,Integer commentCommentId,Integer isDelete,String avatar) {
        super(id, context, commentBlogId, commentUserId,commentCommentId, updateTime,isDelete);
        this.account = account;
        this.blogTitle = blogTitle;
        this.avatar = avatar;

    }

    public CommentMessage(String account, String blogTitle, String commentUserName,String avatar) {
        this.account = account;
        this.blogTitle = blogTitle;
        this.commentUserName = commentUserName;
        this.avatar = avatar;
    }

    public CommentMessage(Integer id,String context,Integer commentBlogId,Integer commentUserId,Date updateTime,String account,String title,Integer is_delete) {
        super(id,context,commentBlogId,commentUserId,updateTime,is_delete);
        this.account = account;
        this.blogTitle = title;
    }
}
