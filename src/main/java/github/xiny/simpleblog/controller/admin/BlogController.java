package github.xiny.simpleblog.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.domain.BindTags;
import github.xiny.simpleblog.domain.Blog;
import github.xiny.simpleblog.mapper.BindTagsMapper;
import github.xiny.simpleblog.mapper.BlogMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
@SaCheckPermission("admin.list")
@RestController("adminBlogController")
@RequestMapping("/admin/blog")
public class BlogController {

    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BindTagsMapper bindTagsMapper;
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
            System.out.println(blog.toString());
        }
        return AjaxJson.getPageData(objectPage.getTotal(), list);
    }

    @RequestMapping("/add")
    @SaCheckPermission("admin.add")
    public AjaxJson addUser(@RequestBody JsonNode node) {
        try {
            JsonNode tagsNode = node.get("tagsId");
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Integer> tagsList = objectMapper.convertValue(tagsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));

            ObjectNode objectNode = (ObjectNode) node;
            objectNode.remove("tagsId");

            objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            var blog = objectMapper.treeToValue(objectNode, Blog.class);
            blogMapper.insert(blog);
            bindTagsMapper.deleteByMap(new HashMap<>(){{put("blog_id", blog.getId());}});
            for (Integer id : tagsList) {
                bindTagsMapper.insert(new BindTags(id,blog.getId()));
            }
            return AjaxJson.getSuccess("添加成功！");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxJson.getError("添加失败！");
        }
    }

    @RequestMapping("/delete")
    @SaCheckPermission("admin.delete")
    public AjaxJson deleteUser(@RequestParam Integer blogId) {
        int  i;
        try {
          i  = blogMapper.deleteById(blogId);
        }catch (Exception e){
            final String message = e.getMessage();
            return AjaxJson.getError(message);
        }
        if (i > 0) {
            return AjaxJson.getSuccess("删除成功！");
        }
        return AjaxJson.getError("删除失败！");
    }

    @RequestMapping("/update")
    @SaCheckPermission("admin.update")
    public AjaxJson updateUser(@RequestBody JsonNode node) throws JsonProcessingException {
        JsonNode tagsNode = node.get("tagsId");
        ObjectMapper objectMapper = new ObjectMapper();
        final List<Integer> tagsList = objectMapper.convertValue(tagsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));

        ObjectNode objectNode = (ObjectNode) node;
        objectNode.remove("tagsId");

        objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var blog = objectMapper.treeToValue(objectNode, Blog.class);
        blog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        final int i = blogMapper.updateById(blog);
        bindTagsMapper.deleteByMap(new HashMap<>(){{put("blog_id", blog.getId());}});
        for (Integer id : tagsList) {
            bindTagsMapper.insert(new BindTags(id,blog.getId()));
        }
        if (i > 0) {
            return AjaxJson.getSuccess("修改成功！");
        }
        return AjaxJson.getError("修改失败！");
    }

    @RequestMapping("/setTags")
    @SaCheckPermission("admin.update")
    public AjaxJson setTags(@RequestBody JsonNode jsonNode) {
        if (!jsonNode.has("blogId")||!jsonNode.has("tags")) {
            return AjaxJson.getError("参数错误！");
        }
        final int blogId = jsonNode.get("blogId").asInt();
        final JsonNode idList = jsonNode.get("tags");
        ObjectMapper objectMapper = new ObjectMapper();
        final List<Integer> bindTags = objectMapper.convertValue(idList, objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
        bindTagsMapper.deleteByMap(new HashMap<>(){{put("blog_id", blogId);}});
        for (Integer id : bindTags) {
            bindTagsMapper.insert(new BindTags(id,blogId));
        }
        return AjaxJson.getSuccess("设置成功！");
    }
}
