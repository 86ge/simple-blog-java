package github.xiny.simpleblog.mapper;


import github.xiny.simpleblog.domain.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRoleMapper {


    @Select("select userid,role.role_id,role_name from user_role,role where userid = #{userId} and user_role.role_id = role.role_id")
    List<UserRole> getUserRoleByUserId(String userId);

    @Select("select user.user_id,role.role_id,role_name,account from user_role,role,user where  user_role.role_id = role.role_id and user_role.userid=`user`.user_id")
    List<UserRole> getUserMsg();

    @Update("update user_role set role_id = #{roleId} where userid = #{userId}")
    int updateUserRole(UserRole userRole);

    @Insert("insert into user_role(userid,role_id) values(#{userId},#{roleId})")
    int insert(UserRole userRole);

    @Delete("delete from user_role where userid = #{userId}")
    int deleteUserRole(String userId);
}
