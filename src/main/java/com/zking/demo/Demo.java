package com.zking.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class Demo {
    public static void main(String[] args) {
        //读取配置文件
        IniSecurityManagerFactory ini=new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        //获取安全管理器
        SecurityManager securityManager = ini.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //创建令牌进行登录判断
        UsernamePasswordToken token=new UsernamePasswordToken(
                "zs","123"
        );
        //登录判断
        try {
            subject.login(token);
            System.out.println("登录成功！");
          if(subject.isPermitted("user:view")){
              System.out.println("具备查询所有的权限");
          }
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }catch(UnknownAccountException e){
            System.out.println("账号不存在");
        }



    }
}
