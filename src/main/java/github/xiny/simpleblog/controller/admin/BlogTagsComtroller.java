package github.xiny.simpleblog.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.domain.BlogTags;
import github.xiny.simpleblog.domain.SelectData;
import github.xiny.simpleblog.mapper.BlogTagsMapper;
import github.xiny.simpleblog.service.SelectService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController()
@SaCheckPermission("admin.list")
@RequestMapping("/admin/blog/tags")
public class BlogTagsComtroller {

    @Resource
    private BlogTagsMapper blogTagsMapper;
    @Resource
    private SelectService selectService;
    @RequestMapping("/get")
    public AjaxJson getTagsList(@RequestBody BlogTags VO) {
        final SelectData<BlogTags> select = selectService.select(VO, blogTagsMapper);
        return AjaxJson.getPageData((long) select.getTotal(), select.getData());
    }

}
