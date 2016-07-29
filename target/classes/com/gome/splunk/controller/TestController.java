package com.gome.splunk.controller;

import com.gome.splunk.entity.RoleEntity;
import com.gome.splunk.entity.UserEntity;
import com.gome.splunk.entity.UserRoleEntity;
import com.gome.splunk.service.RoleService;
import com.gome.splunk.service.UserRoleService;
import com.gome.splunk.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liubing-ds3 on 2016/5/13.
 * 测试专用
 */
@Controller
public class TestController {

    private static final Logger log = Logger.getLogger(TestController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;



     @Value("#{config['username']}")
     private String username;



    @RequestMapping("/addUser.do")
    @ResponseBody
    public String addUser(Model model){
        UserEntity user = new UserEntity();
        user.setUsername("liangcaihong");
        user.setNickname("梁彩虹");
        user.setPassword("123456");
        user.setCreate_time(new Date());
        user.setCreate_user(0);
        userService.addUser(user);
        model.addAttribute("name",user.getUsername());
        log.debug("添加用户用户名为："+user.getNickname());
        return "userList";
    }


    @RequestMapping("/addRole.do")
    @ResponseBody
    public String addRole(Model model){
        RoleEntity role = new RoleEntity();
        role.setName("虚拟");
        role.setType_name("virtual");
        role.setCreate_time(new Date());
        role.setCreate_user(0);
        roleService.addRole(role);
        model.addAttribute("name",role.getName());
        log.debug("添加角色角色户名为："+role.getName());
        return "roleList";
    }

    @RequestMapping("/bindUserRole.do")
    @ResponseBody
    public String bindUserRole(Integer uid,Integer rid,Model model){
        UserEntity userEntityById = userService.findUserById(uid);
        RoleEntity roleEntityById = roleService.findRoleById(rid);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserByUserId(userEntityById);
        userRoleEntity.setRoleByRoleId(roleEntityById);
        userRoleService.addUserRole(userRoleEntity);
        model.addAttribute("name",userRoleEntity.getId());
        log.debug("绑定的用户Id："+userRoleEntity.getUserByUserId().getId()+" 绑定的角色Id："+userRoleEntity.getRoleByRoleId().getId());
        return "bindUserRole";
    }

    @RequestMapping("/getUserList.do")
    public String getUserList(){
        List<UserEntity> users = new ArrayList<UserEntity>();
        users = userService.findByUsername("lijie-ds1");
        System.out.println("***********************************长度"+users.size());
        if(users!=null && users.size()>0){
            System.out.println("***********************************值"+users.get(0).getNickname());
        }
        return "liub";
    }

    @RequestMapping("/getAndMethodList.do")
    @ResponseBody
    public String getAndMethodList(){
        List<UserEntity> users = new ArrayList<UserEntity>();
        users = userService.findByAndMethod("sys","系统");
        System.out.println("***********************************长度"+users.size());
        if(users!=null && users.size()>0){
            System.out.println("***********************************值"+users.get(0).getNickname());
        }
        return "userList";
    }


    @RequestMapping("/getSetList.do")
    @ResponseBody
    public ModelAndView getSetList(Model model){
        List<UserEntity> users = userService.findAll();
        System.out.println("***********************************长度"+users.size());
        model.addAttribute("users",users);
        return new ModelAndView("liub");
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(Model model) {
        List<UserEntity> users = userService.findAll();
        System.out.println("*******************长度" + users.size());
        model.addAttribute("size",users.size());
        return new ModelAndView("test");

    }

    @RequestMapping("/remove.do")
    @ResponseBody
    public String remove(Integer id) {
        boolean isSave = userService.remove(id);
        System.out.println("*******************是否删除" + isSave);
        return "userList";
    }


    @RequestMapping("/getJsonString.do")
    public ModelAndView getJsonString(Model model) throws Exception{
        URL url = new URL("http://preview.ds.gome.com.cn/stage-web/2015/index.jsp?sync=true&header=1&data=1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        //对应的字符编码转换
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str = null;
        StringBuffer sb = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        reader.close();
        connection.disconnect();
        System.out.println(sb.toString());
        model.addAttribute("data",sb.toString());
        return new ModelAndView("test");
    }

}
