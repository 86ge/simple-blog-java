package github.xiny.simpleblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.xiny.simpleblog.domain.BlogType;
import github.xiny.simpleblog.mapper.BlogTypeMapper;
import github.xiny.simpleblog.service.BlogTypeService;
import org.springframework.stereotype.Service;

/**
* @author 86459
* @description 针对表【blog_type】的数据库操作Service实现
* @createDate 2022-12-02 19:35:19
*/
@Service
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper, BlogType>
    implements BlogTypeService {

}




