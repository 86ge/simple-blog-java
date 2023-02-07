package github.xiny.simpleblog.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import github.xiny.simpleblog.domain.AboutMe;
import github.xiny.simpleblog.domain.CommentMessage;
import github.xiny.simpleblog.domain.User;
import github.xiny.simpleblog.mapper.AboutMeMapper;
import github.xiny.simpleblog.mapper.CommentMapper;
import github.xiny.simpleblog.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {


    @Resource
    private UserMapper userMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private AboutMeMapper aboutMeMapper;

    public User login(String account, String password) throws Exception {
        try {
            final int userid = Integer.parseInt(account);
            final User user = userMapper.selectById(userid);
            if (user.getPassword().equals(password)) {
                StpUtil.login(userid);
                return user;
            }
        }catch (Exception ignored){}
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account);
        final User user = userMapper.selectOne(wrapper);
        try {
            if (user.getPassword().equals(password)) {
                StpUtil.login(user.getUserId());
                return user;
            }
        }catch (Exception e){
            throw new RuntimeException("账号或密码错误！");
        }
        throw new RuntimeException("账号或密码错误！");
    }

    public List<CommentMessage> getMyComment(){
        final int userId = StpUtil.getLoginIdAsInt();
        return commentMapper.getMyComment(userId);
    }

    public String register(String account,String password,Integer avatar){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account);
        final User user = userMapper.selectOne(wrapper);
        if (user != null){
            return "账号已存在";
        }
        User user1 = new User();
        user1.setAccount(account);
        user1.setPassword(password);
        user1.setAvatar(avatar);
        userMapper.insert(user1);
        return "success";
    }

    public String getAboutMe(){
        return aboutMeMapper.selectById(1).getMarkdown();
    }

    public String setAboutMe(String s){
        AboutMe aboutMe = new AboutMe();
        aboutMe.setId(1);
        aboutMe.setMarkdown(s);
        aboutMeMapper.updateById(aboutMe);
        return "success";
    }
}
