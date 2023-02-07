package github.xiny.simpleblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.xiny.simpleblog.domain.Blog;
import github.xiny.simpleblog.mapper.BlogMapper;
import github.xiny.simpleblog.service.BlogService;
import org.springframework.stereotype.Service;

/**
* @author 86459
* @description 针对表【blog】的数据库操作Service实现
* @createDate 2022-10-28 15:21:51
*/
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
    implements BlogService{

}




