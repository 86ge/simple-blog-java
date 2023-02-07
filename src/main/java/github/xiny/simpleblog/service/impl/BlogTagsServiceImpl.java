package github.xiny.simpleblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.xiny.simpleblog.domain.BlogTags;
import github.xiny.simpleblog.mapper.BlogTagsMapper;
import github.xiny.simpleblog.service.BlogTagsService;
import org.springframework.stereotype.Service;

/**
* @author 86459
* @description 针对表【blog_tags】的数据库操作Service实现
* @createDate 2022-12-02 19:35:19
*/
@Service
public class BlogTagsServiceImpl extends ServiceImpl<BlogTagsMapper, BlogTags>
    implements BlogTagsService {

}




