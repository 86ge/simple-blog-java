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
 * @TableName blog
 */
@TableName(value ="blog")
@Data
public class Blog extends SelectVO implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String html;

    private String markdown;

    private String img;

    /**
     *
     */
    private Date updateTime;

    private String introduction;

    @TableField(exist = false)
    private String tagsId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
