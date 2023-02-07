package github.xiny.simpleblog.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import github.xiny.simpleblog.domain.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86459
* @description 针对表【blog】的数据库操作Mapper
* @createDate 2022-10-28 15:21:51
* @Entity github.xiny.myblog.admin.domain.Blog
*/
@Mapper
public interface BlogMapper extends MPJBaseMapper<Blog> {

    @Select("""
        SELECT title,blog.id,markdown,update_time,img,GROUP_CONCAT(tags_id) as tags_id,introduction FROM blog
        LEFT JOIN bind_tags on bind_tags.blog_id=blog.id
        GROUP BY update_time,title,blog.id,markdown ORDER BY update_time DESC
        """)
    List<Blog> getBlogList();

    @Select("""
        SELECT title,blog.id,update_time FROM blog
        GROUP BY update_time,title,blog.id ORDER BY update_time DESC
        """)
    List<Blog> getArchive();
}




