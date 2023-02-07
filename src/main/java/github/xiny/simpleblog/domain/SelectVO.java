package github.xiny.simpleblog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SelectVO {
    @TableField(exist =false)
    private Integer pageIndex;

    @TableField(exist =false)
    private Integer pageSize;

    public SelectVO() {
    }

    public SelectVO(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
