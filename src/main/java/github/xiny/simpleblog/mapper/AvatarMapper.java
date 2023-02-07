package github.xiny.simpleblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.xiny.simpleblog.domain.Avatar;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86459
* @description 针对表【avatar】的数据库操作Mapper
* @createDate 2022-11-25 14:39:44
* @Entity github.xiny.myblog.admin.domain.Avatar
*/

@Mapper
public interface AvatarMapper extends BaseMapper<Avatar> {

}




