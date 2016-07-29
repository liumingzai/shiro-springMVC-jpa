package com.gome.splunk.vo;

import java.util.Date;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */
public class UserVo {

    private Integer id;
    private String uno;
    private String username;
    private String nickname;
    private String password;
    private String center;
    private String department;
    private String  salt;
    private Boolean locked;
    private String description;
    private String phone_mac;
    private Integer update_user;
    private Date update_time;
    private Integer create_user;
    private Date create_time;

    public UserVo() {
    }

    public UserVo(Integer id) {
        this.id = id;
    }

    public UserVo(Integer id, String uno, String username, String nickname, String center, String department,String phone_mac, Date update_time) {
        this.id = id;
        this.uno = uno;
        this.username = username;
        this.nickname = nickname;
        this.center = center;
        this.department = department;
        this.phone_mac = phone_mac;
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUno() {
        return uno;
    }

    public void setUno(String uno) {
        this.uno = uno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone_mac() {
        return phone_mac;
    }

    public void setPhone_mac(String phone_mac) {
        this.phone_mac = phone_mac;
    }

    public Integer getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(Integer update_user) {
        this.update_user = update_user;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
