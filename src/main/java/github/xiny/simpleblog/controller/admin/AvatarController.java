package github.xiny.simpleblog.controller.admin;


import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.domain.Avatar;
import github.xiny.simpleblog.domain.SelectData;
import github.xiny.simpleblog.mapper.AvatarMapper;
import github.xiny.simpleblog.service.SelectService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/avatar")
public class AvatarController {

    @Resource
    private AvatarMapper avatarMapper;
    @Resource
    private SelectService selectService;
    @RequestMapping("/get")
    public AjaxJson getUserList(@RequestBody Avatar VO) {
        final SelectData<Avatar> select = selectService.select(VO, avatarMapper);
        return AjaxJson.getPageData((long) select.getTotal(), select.getData());
    }

}
