package github.xiny.simpleblog.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.domain.Avatar;
import github.xiny.simpleblog.mapper.AvatarMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/avatar")
public class UserAvatarController {

    @Resource
    private AvatarMapper avatarMapper;

    @RequestMapping("/get")
    public AjaxJson getUserList(@RequestBody Avatar VO) {
        int pageIndex =1, userVOPageSize =10;
        try{
            pageIndex  = VO.getPageIndex();
            userVOPageSize  = VO.getPageSize();
        }catch (Exception ignored){}
        VO.setPageSize(null);
        VO.setPageIndex(null);

        QueryWrapper<Avatar> queryWrapper = new QueryWrapper<>();
        final Map map = new ObjectMapper().convertValue(VO, Map.class);
        queryWrapper.allEq(map,false);

        final Page<Object> objectPage = PageHelper.startPage(pageIndex, userVOPageSize);
        final List<Avatar> userList = avatarMapper.selectList(queryWrapper);
        return AjaxJson.getPageData(objectPage.getTotal(), userList);
    }

}
