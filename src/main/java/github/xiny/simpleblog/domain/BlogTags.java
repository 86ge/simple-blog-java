package github.xiny.simpleblog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName blog_tags
 */
@TableName(value ="blog_tags")
@Data
public class BlogTags extends SelectVO implements Serializable {
    /**
     *
     */
    @TableId
    private Integer tagsId;

    /**
     *
     */
    private String tagsName;

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
        BlogTags other = (BlogTags) that;
        return (this.getTagsId() == null ? other.getTagsId() == null : this.getTagsId().equals(other.getTagsId()))
            && (this.getTagsName() == null ? other.getTagsName() == null : this.getTagsName().equals(other.getTagsName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTagsId() == null) ? 0 : getTagsId().hashCode());
        result = prime * result + ((getTagsName() == null) ? 0 : getTagsName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagsId=").append(tagsId);
        sb.append(", tagsName=").append(tagsName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
