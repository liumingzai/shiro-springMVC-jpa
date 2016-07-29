package com.gome.splunk.service.impl;

import com.gome.splunk.common.page.Page;
import com.gome.splunk.common.page.PageList;
import com.gome.splunk.dao.UserDao;
import com.gome.splunk.dao.UserRoleDao;
import com.gome.splunk.entity.UserEntity;
import com.gome.splunk.repository.UserRepository;
import com.gome.splunk.service.UserService;
import com.gome.splunk.util.DESUtils;
import com.gome.splunk.util.EndecryptUtils;
import com.gome.splunk.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */

@Service("UserService")
@Transactional(value = "jpaTransactionManager")
public class UserServiceImpl implements UserService {

    public static final String GOME_KEY = "gomedscn";

    @Autowired
    private UserRepository userRepository;

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    public void addUser(UserEntity user) {
        userRepository.save(user);
    }

    public Set<UserEntity> findByName(String username) {
        return userRepository.findSql(username);
    }

    public List<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> findByAndMethod(String username, String nickname) {
        return userRepository.findByUsernameAndNickname(username,nickname);
    }

    public UserEntity findUserById(Integer userId) {
        UserEntity userEntity = userRepository.findOne(userId);
        return userEntity;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public boolean remove(Integer id) {
        try{
            this.userRoleDao.deleteResultsByProperty("uid",id);
            this.userDao.deleteResultsByPropertyInValues("uid",id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public PageList<UserVo> selectPageList(Page page, String nickname) {
        String hql = "select new " + UserVo.class.getName() +
                "(" +
                "p.id,"+
                "p.uno,"+
                "p.username," +
                "p.nickname," +
                "p.center," +
                "p.department," +
                "p.phone_mac," +
                "p.update_time" +
                " ) from UserEntity as p " +
                "where 1=1 ";

        List<Object> params = new ArrayList<Object>();

        if (StringUtils.isNotBlank(nickname)) {
            hql += " and p.nickname = ? ";
            params.add(nickname);
        }
        hql += " order by p.update_time desc";
        return userDao.findPageList(page, UserVo.class, hql, params);
    }

    public Integer addUser(UserVo userVo) throws Exception {
        UserEntity entity = new UserEntity();
        /*UserEntity _user= EndecryptUtils.md5Password(entity.getUsername(), entity.getPassword());*/
        entity.setUno(userVo.getUno());
        entity.setUsername(userVo.getUsername());
        entity.setNickname(userVo.getNickname());
        entity.setCenter(userVo.getCenter());
        entity.setDepartment(userVo.getDepartment());
        entity.setPhone_mac(userVo.getPhone_mac());
        entity.setCreate_time(new Date(System.currentTimeMillis()));
        entity.setUpdate_time(new Date(System.currentTimeMillis()));
        userDao.save(entity);
        return entity.getId();
    }

    public boolean updateUser(UserVo userVo) throws Exception {
        UserEntity entity  = this.userDao.findListByProperty("uid",userVo.getId()).get(0);
       /* UserEntity _user= EndecryptUtils.md5Password(entity.getUsername(), entity.getPassword());*/
        entity.setUno(userVo.getUno());
        entity.setUsername(userVo.getUsername());
        entity.setNickname(userVo.getNickname());
     /*   entity.setPassword(_user.getPassword());
        entity.setSalt(_user.getSalt());*/
        entity.setCenter(userVo.getCenter());
        entity.setDepartment(userVo.getDepartment());
        entity.setPhone_mac(userVo.getPhone_mac());
        entity.setUpdate_time(new Date(System.currentTimeMillis()));
        userDao.update(entity);
        return true;
    }

    public void removeDatas() throws Exception {
        this.userDao.deleteAll();
    }

    public void saveDatas(List<UserEntity> info) throws Exception {
        for(UserEntity entity : info){
            userDao.save(entity);
        }
    }
}
