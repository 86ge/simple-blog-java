package github.xiny.simpleblog.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import github.xiny.simpleblog.domain.AjaxJson;
import github.xiny.simpleblog.domain.ApiLog;
import github.xiny.simpleblog.domain.SelectData;
import github.xiny.simpleblog.mapper.ApilogMapper;
import github.xiny.simpleblog.service.SelectService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@SaCheckPermission("admin.list")
@RequestMapping("/admin/log")
public class ApiLogController {

    @Resource
    private ApilogMapper apiLogMapper;
    @Resource
    private SelectService selectService;

    @RequestMapping("/get")
    public AjaxJson get(@RequestBody ApiLog VO){
        final SelectData<ApiLog> select = selectService.select(VO, apiLogMapper);
        return AjaxJson.getPageData((long) select.getTotal(), select.getData());
    }

    @RequestMapping("/delete")
    @SaCheckPermission("admin.delete")
    public AjaxJson delete(@RequestBody List<String> idList){
        apiLogMapper.deleteBatchIds(idList);
        return AjaxJson.getSuccess();
    }
}
