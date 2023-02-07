package github.xiny.simpleblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.xiny.simpleblog.domain.AboutMe;
import github.xiny.simpleblog.service.AboutMeService;
import github.xiny.simpleblog.mapper.AboutMeMapper;
import org.springframework.stereotype.Service;

/**
* @author 86459
* @description 针对表【about_me】的数据库操作Service实现
* @createDate 2023-02-04 14:26:11
*/
@Service
public class AboutMeServiceImpl extends ServiceImpl<AboutMeMapper, AboutMe>
    implements AboutMeService{

}




