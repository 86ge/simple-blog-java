package github.xiny.simpleblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.xiny.simpleblog.domain.Comment;
import github.xiny.simpleblog.domain.CommentMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 86459
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2022-10-30 14:17:38
* @Entity github.xiny.myblog.admin.domain.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("""
        select `comment`.id,context,comment_blog_id,comment_user_id,`comment`.update_time,account,blog.title as blog_title,is_delete
        FROM user,comment,blog
        where comment_user_id=`user`.user_id and comment_blog_id = `blog`.id
        ORDER BY update_time
        """)
    List<CommentMessage> getAllCommentMessage();

    @Select("""
        select `comment`.id,context,comment_blog_id,comment_user_id,`comment`.update_time,account,blog.title as blog_title,comment_comment_id,is_delete,img_url as avatar
        FROM user,comment,blog,avatar
        where comment_user_id=`user`.user_id and
        comment_blog_id = #{blogId} and
        comment_blog_id = `blog`.id AND
				img_id=`user`.avatar
				ORDER BY update_time ASC
""")
    List<CommentMessage> getAllCommentForBlogId(String blogId);

    @Select("""
        select `comment`.id,context,comment_blog_id,comment_user_id,`comment`.update_time,account,blog.title as blog_title,comment_comment_id,is_delete
        FROM user,comment,blog
        where comment_user_id=#{userId} and
        comment_user_id=`user`.user_id  and
        comment_blog_id = `blog`.id and
        is_delete = 0
        ORDER BY update_time ASC
        """)
    List<CommentMessage> getMyComment(int userId);


    @Update("""
        update comment
        set is_delete = 1
        where id = #{commentId} and
        comment_user_id = #{userId}
        """)
    int deleteByCommentBlogId(int userId, String commentId);
}




