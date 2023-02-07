package github.xiny.simpleblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.xiny.simpleblog.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86459
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-10-20 16:21:28
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




