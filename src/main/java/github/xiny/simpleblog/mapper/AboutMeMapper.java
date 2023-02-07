package github.xiny.simpleblog.mapper;

import github.xiny.simpleblog.domain.AboutMe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86459
* @description 针对表【about_me】的数据库操作Mapper
* @createDate 2023-02-04 14:26:11
* @Entity github.xiny.myblog.domain.AboutMe
*/

@Mapper
public interface AboutMeMapper extends BaseMapper<AboutMe> {

}




