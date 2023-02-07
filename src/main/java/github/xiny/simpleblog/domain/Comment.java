package github.xiny.simpleblog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment extends SelectVO implements Serializable {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论内容
     */
    private String context;

    /**
     * 评论博客id
     */
    private Integer commentBlogId;

    /**
     * 评论用户id
     */
    private Integer commentUserId;

    /**
     * 评论评论id
     */
    private Integer commentCommentId;

    private Integer isDelete;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getCommentBlogId() == null ? other.getCommentBlogId() == null : this.getCommentBlogId().equals(other.getCommentBlogId()))
            && (this.getCommentUserId() == null ? other.getCommentUserId() == null : this.getCommentUserId().equals(other.getCommentUserId()))
            && (this.getCommentCommentId() == null ? other.getCommentCommentId() == null : this.getCommentCommentId().equals(other.getCommentCommentId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getCommentBlogId() == null) ? 0 : getCommentBlogId().hashCode());
        result = prime * result + ((getCommentUserId() == null) ? 0 : getCommentUserId().hashCode());
        result = prime * result + ((getCommentCommentId() == null) ? 0 : getCommentCommentId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", context=").append(context);
        sb.append(", commentBlogId=").append(commentBlogId);
        sb.append(", commentUserId=").append(commentUserId);
        sb.append(", commentCommentId=").append(commentCommentId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Comment() {
    }

    public Comment(Integer id, String context, Integer commentBlogId, Integer commentUserId, Date updateTime) {
        this.id = id;
        this.context = context;
        this.commentBlogId = commentBlogId;
        this.commentUserId = commentUserId;
        this.updateTime = updateTime;
    }

    public Comment(Integer id, String context, Integer commentBlogId, Integer commentUserId,Integer commentCommentId, Date updateTime) {
        this.id = id;
        this.context = context;
        this.commentBlogId = commentBlogId;
        this.commentUserId = commentUserId;
        this.commentCommentId = commentCommentId;
        this.updateTime = updateTime;
    }

    public Comment(Integer id, String context, Integer commentBlogId, Integer commentUserId, Date updateTime,Integer isDelete) {
        this.id = id;
        this.context = context;
        this.commentBlogId = commentBlogId;
        this.commentUserId = commentUserId;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
    }

    public Comment(Integer id, String context, Integer commentBlogId, Integer commentUserId,Integer commentCommentId, Date updateTime,Integer isDelete) {
        this.id = id;
        this.context = context;
        this.commentBlogId = commentBlogId;
        this.commentUserId = commentUserId;
        this.commentCommentId = commentCommentId;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
    }
}
