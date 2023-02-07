package github.xiny.simpleblog.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.domain.BlogTags;
import github.xiny.simpleblog.mapper.BlogTagsMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/user/blog/tags")
public class UserBlogTagsComtroller {

    @Resource
    private BlogTagsMapper blogTagsMapper;

    @RequestMapping("/get")
    public AjaxJson getTagsList(@RequestBody BlogTags VO) {
        int pageIndex =1, userVOPageSize =10;
        try{
            pageIndex  = VO.getPageIndex();
            userVOPageSize  = VO.getPageSize();
        }catch (Exception ignored){}
        VO.setPageSize(null);
        VO.setPageIndex(null);

        QueryWrapper<BlogTags> queryWrapper = new QueryWrapper<>();
        final Map map = new ObjectMapper().convertValue(VO, Map.class);
        queryWrapper.allEq(map,false);

        final Page<Object> objectPage = PageHelper.startPage(pageIndex, userVOPageSize);
        final List<BlogTags> userList = blogTagsMapper.selectList(queryWrapper);
        return AjaxJson.getPageData(objectPage.getTotal(), userList);
    }

}
