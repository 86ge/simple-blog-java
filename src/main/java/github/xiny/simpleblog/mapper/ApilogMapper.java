package github.xiny.simpleblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.xiny.simpleblog.domain.ApiLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86459
* @description 针对表【apilog(api请求记录表)】的数据库操作Mapper
* @createDate 2022-10-25 14:53:34
* @Entity generator.domain.Apilog
*/
@Mapper
public interface ApilogMapper extends BaseMapper<ApiLog> {

    @Select("""
        SELECT start_time FROM api_log
        where start_time > start_time < DATE_SUB(curdate(),INTERVAL 7 DAY) and start_time < DATE_SUB(curdate(),INTERVAL -1 DAY)
        ORDER BY start_time
""")
    List<ApiLog> getLast7DaysLog();

}




