package github.xiny.simpleblog.controller.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.domain.Blog;
import github.xiny.simpleblog.mapper.BlogMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/blog")
public class BlogController {

    @Resource
    private BlogMapper blogMapper;

    @RequestMapping("/getById")
    public AjaxJson getBlogById(@RequestParam String blogId){
        final Blog blog = blogMapper.selectById(blogId);
        return AjaxJson.getSuccessData(blog);
    }
    @RequestMapping("/get")
    public AjaxJson getUserList(@RequestBody Blog VO) {
        int pageIndex =1, userVOPageSize =10;
        try{
            pageIndex  = VO.getPageIndex();
            userVOPageSize  = VO.getPageSize();
        }catch (Exception ignored){}
        VO.setPageSize(null);
        VO.setPageIndex(null);
        final Page<Object> objectPage = PageHelper.startPage(pageIndex, userVOPageSize);
        final List<Blog> list = blogMapper.getBlogList();
        for (Blog blog : list) {
            blog.setMarkdown(blog.getMarkdown().trim().split("\\n")[0]);
        }
        return AjaxJson.getPageData(objectPage.getTotal(), list);
    }
    @RequestMapping("/archive")
    public AjaxJson getArchive(){
        final List<Blog> list = blogMapper.getArchive();
        return AjaxJson.getSuccessData(list);
    }
}
