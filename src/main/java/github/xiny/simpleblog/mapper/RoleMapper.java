package github.xiny.simpleblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.xiny.simpleblog.domain.Role;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86459
* @description 针对表【role】的数据库操作Mapper
* @createDate 2022-10-21 17:04:06
* @Entity generator.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




