package com.gome.splunk.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gome.splunk.common.constance.EnumInterface;
import com.gome.splunk.common.constance.RoleTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：枚举类型转JS
 * 作者： yangyan
 * 时间： 2014/11/23 .
 */
@Controller
@RequestMapping("/")
public class EnumController {
    static List<Class<? extends EnumInterface>> enums = new ArrayList<Class<? extends EnumInterface>>();

    static {

        enums.add(RoleTypeEnum.class);
    }

    ObjectMapper m = new ObjectMapper();

    /**
     * 输出常量、枚举类型JS全局变量
     *
     * @param request
     * @param response
     * @throws JsonProcessingException
     */
    @RequestMapping("/enums.do")
    @ResponseBody
    public void js(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

        response.setContentType("application/javascript;charset=utf-8");
        try {
            for (Class<? extends EnumInterface> anEnum : enums) {
                response.getWriter().println("F.defineEnum(");
                EnumInterface[] enumConstants = anEnum.getEnumConstants();
                List<EnumBean> list = new ArrayList<EnumBean>();
                for (EnumInterface enumConstant : enumConstants) {
                    EnumBean enumBean = new EnumBean();
                    enumBean.setDesc(enumConstant.getDesc());
                    enumBean.setId(enumConstant.getId());
                    enumBean.setName(enumConstant.getName());
                    enumBean.setOriginName(enumConstant.toString());
                    list.add(enumBean);
                }
                response.getWriter().println(m.writeValueAsString(list) + ", '" + anEnum.getSimpleName() + "');");
            }
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 预览用的
     *
     * @param request
     * @param response
     * @throws JsonProcessingException
     */
    @RequestMapping("/enums_view.do")
    @ResponseBody
    public void view(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        response.setContentType("text/html;charset=utf-8");
        try {

            for (Class<? extends EnumInterface> anEnum : enums) {
                response.getWriter().println(anEnum.getSimpleName() + ":</br>");
                EnumInterface[] enumConstants = anEnum.getEnumConstants();
                List<EnumBean> list = new ArrayList<EnumBean>();
                for (EnumInterface enumConstant : enumConstants) {
                    EnumBean enumBean = new EnumBean();
                    enumBean.setDesc(enumConstant.getDesc());
                    enumBean.setId(enumConstant.getId());
                    enumBean.setName(enumConstant.getName());
                    enumBean.setOriginName(enumConstant.toString());
                    list.add(enumBean);
                    response.getWriter().print(enumConstant.getId() + "(" + enumConstant.getName() + "),");
                }
                response.getWriter().print("</br>");
            }
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
