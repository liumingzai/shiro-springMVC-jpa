package com.gome.splunk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liubing-ds3 on 2016/6/1.
 * 导航入口
 */

@Controller
@RequestMapping("/splunk/index")
public class IndexController {


    @RequestMapping("menu.do")
    public String menu() {
        return "menu";
    }

    @RequestMapping("userList.do")
    public String userList() {
        return "userList";
    }

    @RequestMapping("roleList.do")
    public String roleList() {
        return "roleList";
    }


    @RequestMapping("resourceList.do")
    public String resourceList() {
        return "resourceList";
    }

    @RequestMapping("bindUserRole.do")
    public String bindUserRole() {
        return "bindUserRole";
    }

    @RequestMapping("editUser.do")
    public String editUser() {
        return "editUser";
    }

    @RequestMapping("editRole.do")
    public String editRole() {
        return "editRole";
    }

    @RequestMapping("editResource.do")
    public String editResource() {
        return "editResource";
    }

    @RequestMapping("bindRoleResource.do")
    public String bindRoleResource() {
        return "bindRoleResource";
    }


}
