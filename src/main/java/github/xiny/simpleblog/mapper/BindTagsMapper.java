package github.xiny.simpleblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.xiny.simpleblog.domain.BindTags;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86459
* @description 针对表【bind_tags】的数据库操作Mapper
* @createDate 2022-12-02 19:36:21
* @Entity github.xiny.myblog.admin.domain.BindTags
*/
@Mapper
public interface BindTagsMapper extends BaseMapper<BindTags> {

}




