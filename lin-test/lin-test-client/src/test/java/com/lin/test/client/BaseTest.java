package com.lin.test.client;

import com.lin.test.client.shiro.chapter6.realm.entity.Permission;
import com.lin.test.client.shiro.chapter6.realm.entity.Role;
import com.lin.test.client.shiro.chapter6.realm.entity.User;
import com.lin.test.client.shiro.chapter6.realm.service.*;
import com.lin.test.client.shiro.chapter6.realm.util.JdbcTemplateUtils;
import com.lin.test.client.shiro.chapter6.realm.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseTest {

    protected UserService userService = new UserServiceImpl();

    protected PermissionService permissionService = new PermissionServiceImpl();

    protected RoleService roleService = new RoleServiceImpl();

    protected JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    protected PasswordHelper passwordHelper = new PasswordHelper();

    protected Permission p1;
    protected Permission p2;
    protected Permission p3;

    protected Role r1;
    protected Role r2;
    protected Role r3;

    protected User u1;
    protected User u2;
    protected User u3;
    protected User u4;

    @Before
    public void setUp() {
        jdbcTemplate.update("delete from sys_users");
        jdbcTemplate.update("delete from sys_roles");
        jdbcTemplate.update("delete from sys_permissions");
        jdbcTemplate.update("delete from sys_users_roles");
        jdbcTemplate.update("delete from sys_roles_permissions");
        //新增权限
        permissionService.createPermission(p1 = new Permission("user:create", "用户模块新增", true));
        permissionService.createPermission(p2 = new Permission("user:update", "用户模块新增", true));
        permissionService.createPermission(p3 = new Permission("menu:create", "菜单模块新增", true));
        //新增角色
        roleService.createRole(r1 = new Role("admin", "管理员", true));
        roleService.createRole(r2 = new Role("user", "用户", true));
        roleService.createRole(r3 = new Role("super admin", "超级管理员", true));

        //新增角色权限
        roleService.correlationPermissions(r1.getId(), p1.getId(), p2.getId());
        roleService.correlationPermissions(r2.getId(), p1.getId());
        roleService.correlationPermissions(r3.getId(), p1.getId(), p2.getId(), p3.getId());

        //新增用户

        userService.createUser(u1 = new User("zhang", "123"));
        u2 = new User("lin", "123");
        userService.createUser(u2);
        userService.createUser(u3 = new User("wang", "123"));
        u4 = new User("wu", "123");
        u4.setLocked(true);
        userService.createUser(u4);

        //添加用户-角色
        userService.correlationRoles(u1.getId(), r1.getId());
        userService.correlationRoles(u2.getId(), r2.getId());

    }

    @After
    public void tearDown() {
        ThreadContext.unbindSubject();
    }

    public void login(String file, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(file);
        SecurityManager securityManager = factory.getInstance();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        subject.login(token);
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
