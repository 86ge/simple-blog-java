package github.xiny.simpleblog.domain;


import lombok.Data;

@Data
public class UserRole extends User {

    private int roleId;

    private String roleName;

    public UserRole() {
    }

    public UserRole(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public UserRole(int userid,int roleId) {
        super(userid);
        this.roleId = roleId;
    }
    public UserRole(int userid,int roleId,String roleName) {
        super(userid);
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public UserRole(int userid,int roleId, String roleName,String account) {
        super(userid,account);
        this.roleId = roleId;
        this.roleName = roleName;
    }

}
