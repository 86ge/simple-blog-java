package github.xiny.simpleblog.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import github.xiny.simpleblog.domain.*;
import github.xiny.simpleblog.mapper.CommentMapper;
import github.xiny.simpleblog.service.SelectService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/comment")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SaCheckLogin
@SaCheckPermission("admin.list")
public class CommentController {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private SelectService selectService;
    @RequestMapping("/get")
    public AjaxJson getRoleList(@RequestBody Comment VO) {
        final SelectData<Comment> select = selectService.select(VO, commentMapper);
        return AjaxJson.getPageData((long) select.getTotal(), select.getData());
    }

    @RequestMapping("/add")
    @SaCheckPermission("admin.add")
    public AjaxJson addRole(@RequestBody Comment comment) {
        final int i = commentMapper.insert(comment);
        if (i > 0) {
            return AjaxJson.getSuccess("添加成功！");
        }
        return AjaxJson.getError("添加失败！");
    }

    @RequestMapping("/update")
    @SaCheckPermission("admin.update")
    public AjaxJson updateRole(@RequestBody  Comment comment) {
        final int i = commentMapper.updateById(comment);
        if (i > 0) {
            return AjaxJson.getSuccess("修改成功！");
        }
        return AjaxJson.getError("修改失败！");
    }

    @RequestMapping("/delete")
    @SaCheckPermission("admin.delete")
    public AjaxJson deleteRole(@RequestParam Integer commentId) {
        final int i = commentMapper.deleteById(commentId);
        if (i > 0) {
            return AjaxJson.getSuccess("删除成功！");
        }
        return AjaxJson.getError("删除失败！");
    }

    @RequestMapping("/getCommentMessageList")
    public AjaxJson getCommentMessageList(@RequestBody Comment VO) {
        int pageIndex =1, userVOPageSize =10;
        try{
            pageIndex  = VO.getPageIndex();
            userVOPageSize  = VO.getPageSize();
        }catch (Exception ignored){}
        VO.setPageSize(null);
        VO.setPageIndex(null);

        final Page<Object> objectPage = PageHelper.startPage(pageIndex, userVOPageSize);
        final List<CommentMessage> userList = commentMapper.getAllCommentMessage();
        return AjaxJson.getPageData(objectPage.getTotal(), userList);
    }
}



