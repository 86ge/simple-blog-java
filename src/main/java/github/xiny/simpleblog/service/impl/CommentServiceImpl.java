package github.xiny.simpleblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.xiny.simpleblog.domain.Comment;
import github.xiny.simpleblog.mapper.CommentMapper;
import github.xiny.simpleblog.service.CommentService;
import org.springframework.stereotype.Service;

/**
* @author 86459
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2022-10-30 14:17:38
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

}




