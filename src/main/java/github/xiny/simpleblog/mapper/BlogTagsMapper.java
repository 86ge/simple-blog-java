package github.xiny.simpleblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.xiny.simpleblog.domain.BlogTags;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86459
* @description 针对表【blog_tags】的数据库操作Mapper
* @createDate 2022-12-02 19:35:18
* @Entity github.xiny.myblog.admin.domain.BlogTags
*/
@Mapper
public interface BlogTagsMapper extends BaseMapper<BlogTags> {

}




