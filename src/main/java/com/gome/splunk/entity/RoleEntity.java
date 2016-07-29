package com.gome.splunk.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */

@Entity
@Table(name="bo_role")
public class RoleEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rid")
    private Integer id;

    @Basic
    @Column(name = "rname")
    private String name;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "typeName")
    private String type_name;


    @Basic
    @Column(name = "description")
    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (update_user != null ? !update_user.equals(that.update_user) : that.update_user != null) return false;
        if (update_time != null ? !update_time.equals(that.update_time) : that.update_time != null) return false;
        if (create_user != null ? !create_user.equals(that.create_user) : that.create_user != null) return false;
        return create_time != null ? create_time.equals(that.create_time) : that.create_time == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (update_user != null ? update_user.hashCode() : 0);
        result = 31 * result + (update_time != null ? update_time.hashCode() : 0);
        result = 31 * result + (create_user != null ? create_user.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", update_user=" + update_user +
                ", update_time=" + update_time +
                ", create_user=" + create_user +
                ", create_time=" + create_time +
                '}';
    }
}
