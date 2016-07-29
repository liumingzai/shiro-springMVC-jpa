package com.gome.splunk.controller;

import com.gome.splunk.common.page.Page;
import com.gome.splunk.common.page.PageList;
import com.gome.splunk.entity.ResourceEntity;
import com.gome.splunk.entity.RoleEntity;
import com.gome.splunk.service.ResourceService;
import com.gome.splunk.vo.ResourceRoleVo;
import com.gome.splunk.vo.ResourceVo;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liubing-ds3 on 2016/6/1.
 * 资源管理模块
 */
@Controller
@RequestMapping("/splunk/resource")
public class ResourceController {


    private static final Logger log = Logger.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    /**
     * 资源列表
     * @param page
     * @param rsname
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.do")
    public Map<String, Object> list(Page page, String rsname) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageList<ResourceVo> resourceVoPageList = resourceService.selectPageList(page, rsname);
        List<ResourceVo> res = new ArrayList<ResourceVo>();
        for(ResourceVo rv : resourceVoPageList.getList()){
           if(rv.getParentid()!=-1){
               rv.setPermission(resourceService.findResourceById(rv.getParentid()).getRsname());
           }
            res.add(rv);
        }
        PageList<ResourceVo> pageList = new PageList(resourceVoPageList.getPage(),res);
        map.put("pageList", pageList);
        map.put("success", true);
        map.put("msg", "操作成功！");
        return map;
    }

    /**
     * 编辑资源
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit.do")
    public Map<String, Object> edit(Integer id)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        ResourceEntity resource = resourceService.findResourceById(id);
        map.put( "resource",resource );
        return map;
    }

    /**
     * 添加资源
     * @param resource
     * @return
     */
    @ResponseBody
    @RequestMapping("/add.do")
    public Map<String, Object> add(ResourceVo resource) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        String msg = null;
        try {
            resourceService.addResource(resource);
            success = true;
        } catch (Exception e) {
            log.error("添加资源出错", e);
            success = false;
            msg = e.getMessage();
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    /**
     * 修改资源
     * @param resource
     * @return
     */

    @ResponseBody
    @RequestMapping("/update.do")
    public Map<String, Object> update(ResourceVo resource) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        String msg = null;
        try {
            resourceService.updateResource(resource);
            success = true;
        } catch (Exception e) {
            log.error("修改资源出错", e);
            success = false;
            msg = e.getMessage();
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }


    /**
     * 删除资源
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/remove.do")
    public Map<String, Object> remove(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        String msg = null;
        try {
            resourceService.remove(id);
            success = true;
        } catch (Exception e) {
            log.error("删除资源出错", e);
            success = false;
            msg = e.getMessage();
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }


    /**
     * 备用
     * @param request
     * @return
     */
    @RequestMapping("/getResource")
    @ResponseBody
    public String getResource(HttpServletRequest request) {

        List<Map<String, String>> maplists = new ArrayList<Map<String, String>>();
        List<ResourceEntity> lists = resourceService.findAll();
        if (lists != null) {
            for (ResourceEntity res : lists) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id",res.getId().toString());
                map.put( "name",res.getRsname());
                map.put( "level",res.getLevel().toString());
                maplists.add(map);
            }
        }
        Gson gson = new Gson();
        String ajson = gson.toJson( maplists );
        return ajson;
    }


    @RequestMapping("/getParentName")
    @ResponseBody
    public String getParentName(HttpServletRequest request){
        Integer rsid = Integer.valueOf(request.getParameter("parentid"));
        ResourceEntity resourceEntity = resourceService.findResourceById(rsid);
        if(resourceEntity!=null){
            return resourceEntity.getRsname();
        }
        return "";
    }


    @RequestMapping("/getChirlds")
    @ResponseBody
    public String getChirlds(HttpServletRequest request) {
        List<Map<String, Object>> maplists = new ArrayList<Map<String, Object>>();
        Integer rid = request.getParameter("rid")==null?0:Integer.valueOf(request.getParameter("rid"));
        List<ResourceRoleVo> resourceRoleVos = resourceService.findChildResourceByRoleId(rid);
        List<ResourceVo> lists = resourceService.getChirlds();
        if (lists != null) {
            for (ResourceVo res : lists) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id",res.getId());
                map.put( "name",res.getRsname());
                map.put( "parentid",res.getParentid());
                for(ResourceRoleVo resourceRoleVo: resourceRoleVos){
                    if(res.getId()==resourceRoleVo.getRsid()){
                        map.put("checked",true);
                        break;
                    }
                }
                maplists.add(map);
            }
        }
        Gson gson = new Gson();
        String ajson = gson.toJson( maplists );
        System.out.println("子节点："+ajson);
        return ajson;
    }

    @RequestMapping("/getParentResources")
    @ResponseBody
    public String getParentResources(HttpServletRequest request) {
        List<Map<String, Object>> maplists = new ArrayList<Map<String, Object>>();
        Integer rid = request.getParameter("rid")==null?0:Integer.valueOf(request.getParameter("rid"));
        List<ResourceRoleVo> resourceRoleVos = resourceService.findResourceByRoleId(rid);
        List<ResourceVo> lists = resourceService.findParentResources();
        if (lists != null) {
            for (ResourceVo res : lists) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id",res.getId());
                map.put( "name",res.getRsname());
                for(ResourceRoleVo resourceRoleVo: resourceRoleVos){
                    if(res.getId()==resourceRoleVo.getRsid()){
                        map.put("checked",true);
                        break;
                    }
                }
                maplists.add(map);
            }
        }
        Gson gson = new Gson();
        String ajson = gson.toJson( maplists );
        System.out.println("父节点："+ajson);
        return ajson;
    }
}
