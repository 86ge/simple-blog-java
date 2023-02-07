package github.xiny.simpleblog.controller.user;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.domain.Comment;
import github.xiny.simpleblog.mapper.CommentMapper;
import github.xiny.simpleblog.service.user.UserCommentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/comment")
public class UserCommentController {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserCommentService commentService;

    @RequestMapping("/get")
    public AjaxJson getComment(@RequestParam String blogId){
        return AjaxJson.getSuccessData(commentService.getComment(blogId));
    }

    @RequestMapping("/add")
    @SaCheckLogin
    public AjaxJson addComment(@RequestBody Comment comment){
        comment.setCommentUserId(StpUtil.getLoginIdAsInt());
        commentMapper.insert(comment);
        return AjaxJson.getSuccess();
    }

    @RequestMapping("/delete")
    @SaCheckLogin
    public AjaxJson deleteComment(@RequestParam String commentId){
        commentMapper.deleteByCommentBlogId(StpUtil.getLoginIdAsInt(),commentId);
        return AjaxJson.getSuccess();
    }
}
