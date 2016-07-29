package com.gome.splunk.controller;

import com.gome.splunk.common.util.AppContext;
import com.gome.splunk.vo.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liubing-ds3 on 2016/5/17.
 * 权限管理模快
 */
@Controller
public class LoginController {


    @RequestMapping("login/login.do")
    @ResponseBody
    public Map<String,Object> index(Login user) {
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("status",true);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                //token.setRememberMe(true);
                subject.login(token);
                // 在web应用中,subject.getSession()和request.getSession()作用一样
                // session也一样,放入subject.getSession()和request.getSession()中的对象是共享的
                // 但是为了兼容性,推荐使用subject.getSession()
                Session session = subject.getSession(false);
                user.setUsername("admin".equals(user.getUsername()) ? "系统管理员" : "访客");
                session.setAttribute("USER", user);
//				HttpSession httpSession = request.getSession(false);
//				System.out.println(httpSession.getAttribute("USER"));
            } catch (Exception e) {
                data.put("status",false);
                e.printStackTrace();
            }
        }
        return data;
    }

    @RequestMapping("/index.do")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "/menu";
    }

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        try{
            if(session!=null){
                session.invalidate();
            }
            if(currentUser!=null){
                currentUser.logout();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);

        currentUser.logout();
        return "login";
    }

    @RequestMapping("/editPwd.do")
    public String editPwd(HttpServletRequest request, HttpServletResponse response) {
        return "editPwd";
    }

   @RequestMapping("/newPwd.do")
   @ResponseBody
    public Map<String,Object> newPwd(HttpServletRequest request) throws IOException {
       Map<String,Object> data = new HashMap<String, Object>();
       data.put("status",true);
       Properties ps = new Properties();
       String password0 = request.getParameter("password0");
       AppContext.password = password0;
       System.out.println("*************************打印路径："+ URLDecoder.decode(LoginController.this.getClass().getClassLoader().getResource("config_dev.properties").getPath(),"UTF-8"));
       try {
           FileInputStream inputStream;
           if("Windows 7".equals(System.getProperty("os.name"))){
                inputStream =new FileInputStream(new File("E:/splunk-web/src/main/resources/config.properties"));
           }else{
                inputStream = new FileInputStream(new File(LoginController.this.getClass().getClassLoader().getResource("config_dev.properties").getFile()));
           }
           try {
               ps.load(inputStream);
           } catch (IOException e) {
               e.printStackTrace();
           } finally {
               inputStream.close();
           }
           OutputStream fos;
           if("Windows 7".equals(System.getProperty("os.name"))){
               fos = new FileOutputStream(new File("E:/splunk-web/src/main/resources/config.properties"));
           }else{
               fos = new FileOutputStream(new File(LoginController.this.getClass().getClassLoader().getResource("config_dev.properties").getFile()));
           }
           ps.setProperty("password",password0);

           ps.store(fos,"UTF-8");
           fos.flush();
           fos.close();
       } catch (IOException e) {
           data.put("status",false);
           e.printStackTrace();
       }
        return data;
    }

    @RequestMapping("/error.do")
    public String error(HttpServletRequest request, HttpServletResponse response) {
        return "error";
    }

    @RequestMapping("/success.do")
    public String admin(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("adminView", true);
        return "index";
    }

    @RequestMapping("/noAuth.do")
    public String noAuth(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

}
