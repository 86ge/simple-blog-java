package github.xiny.simpleblog.service;

import cn.hutool.core.date.DateUtil;
import github.xiny.simpleblog.domain.ApiLog;
import github.xiny.simpleblog.mapper.ApilogMapper;
import github.xiny.simpleblog.mapper.BlogMapper;
import github.xiny.simpleblog.mapper.CommentMapper;
import github.xiny.simpleblog.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ConsoleMessageService {

    @Resource
    private ApilogMapper apiLogMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private UserMapper userMapper;

    public Map<String, Long> getLogCount(){
        final List<ApiLog> last7DaysLog = apiLogMapper.getLast7DaysLog();
        LinkedHashMap<String, Long> map = new LinkedHashMap<>();
        final Date nowDate = new Date();
        for (int i = 6; i >= 0; i--)
            map.put(DateUtil.format(DateUtil.offsetDay(nowDate, -i), "MM-dd"), 0L);
        for (ApiLog apiLog : last7DaysLog) {
            final String day = DateUtil.format(apiLog.getStartTime(), "MM-dd");
            if (map.containsKey(day))
                map.put(day, map.get(day) + 1);
        }
        return map;
    }

    public Long getCommentCount(){
        return commentMapper.selectCount(null);
    }
    public Long getBlogCount(){
        return blogMapper.selectCount(null);
    }
    public Long getUserCount(){
        return userMapper.selectCount(null);
    }
}
