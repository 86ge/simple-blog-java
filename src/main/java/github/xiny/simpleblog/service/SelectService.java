package github.xiny.simpleblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import github.xiny.simpleblog.domain.SelectData;
import github.xiny.simpleblog.domain.SelectVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SelectService{
    public <T extends SelectVO> SelectData<T> select(T VO, BaseMapper<T> mapper){
        int pageIndex =1, userVOPageSize =10;
        try{
            pageIndex  = VO.getPageIndex();
            userVOPageSize  = VO.getPageSize();
        }catch (Exception ignored){}
        VO.setPageSize(null);
        VO.setPageIndex(null);
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        final Map map = new ObjectMapper().convertValue(VO, Map.class);
        queryWrapper.allEq(map,false);
        final Page<Object> objectPage = PageHelper.startPage(pageIndex, userVOPageSize);
        final List<T> list = mapper.selectList(queryWrapper);
        System.out.println(objectPage);
        return new SelectData<>(objectPage.getPageNum(), (int) objectPage.getTotal(), list);
    }

}
