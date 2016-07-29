package com.gome.splunk.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */

@Entity
@Table(name="bo_user")
@NamedQueries({
        @NamedQuery(name="findAll",query="SELECT u FROM UserEntity u")
})
public class UserEntity {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "uid")
    private Integer id;

    @Basic
    @Column(name = "uno")
    private String uno;

    @Basic
    @Column(name = "name")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "nickname")
    private String nickname;

    @Basic
    @Column(name = "center")
    private String center;

    @Basic
    @Column(name = "department")
    private String department;

    @Basic
    @Column(name = "salt")
    private String salt;

    @Basic
    @Column(name ="locked")
    private String locked;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "phoneMac")
    private String phone_mac;

    @Basic
    @Column(name = "updateByUser")
    private Integer update_user;

    @Basic
    @Column(name = "updateTime")
    private Date update_time;

    @Basic
    @Column(name = "createByUser")
    private Integer create_user;

    @Basic
    @Column(name = "createTime")
    private Date create_time;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uno != null ? !uno.equals(that.uno) : that.uno != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (center != null ? !center.equals(that.center) : that.center != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (salt != null ? !salt.equals(that.salt) : that.salt != null) return false;
        if (locked != null ? !locked.equals(that.locked) : that.locked != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (phone_mac != null ? !phone_mac.equals(that.phone_mac) : that.phone_mac != null) return false;
        if (update_user != null ? !update_user.equals(that.update_user) : that.update_user != null) return false;
        if (update_time != null ? !update_time.equals(that.update_time) : that.update_time != null) return false;
        if (create_user != null ? !create_user.equals(that.create_user) : that.create_user != null) return false;
        return create_time != null ? create_time.equals(that.create_time) : that.create_time == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uno != null ? uno.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (center != null ? center.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (phone_mac != null ? phone_mac.hashCode() : 0);
        result = 31 * result + (update_user != null ? update_user.hashCode() : 0);
        result = 31 * result + (update_time != null ? update_time.hashCode() : 0);
        result = 31 * result + (create_user != null ? create_user.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", uno='" + uno + '\'' +
                ", username='" + username + '\'' +
                ", center='" + center + '\'' +
                ", department='" + department + '\'' +
                ", phone_mac='" + phone_mac + '\'' +
                ", locked='" + locked + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
