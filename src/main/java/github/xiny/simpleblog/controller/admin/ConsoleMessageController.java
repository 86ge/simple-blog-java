package github.xiny.simpleblog.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.service.ConsoleMessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SaCheckPermission("admin.list")
@RequestMapping("/admin/message")
public class ConsoleMessageController {

    @Resource
    private ConsoleMessageService consoleMessageService;
    @RequestMapping("/getLogCount")
    public AjaxJson getLogCount(){
        return AjaxJson.getSuccessData(consoleMessageService.getLogCount());
    }
    @RequestMapping("/getCommentCount")
    public AjaxJson getCommentCount(){
        return AjaxJson.getSuccessData(consoleMessageService.getCommentCount());
    }
    @RequestMapping("/getBlogCount")
    public AjaxJson getBlogCount(){
        return AjaxJson.getSuccessData(consoleMessageService.getBlogCount());
    }
    @RequestMapping("/getUserCount")
    public AjaxJson getUserCount(){
        return AjaxJson.getSuccessData(consoleMessageService.getUserCount());

    }
}
