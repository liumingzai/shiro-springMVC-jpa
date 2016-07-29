package com.gome.splunk.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */

@Entity
@Table(name="bo_resource")
public class ResourceEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rsid")
    private Integer id;

    @Basic
    @Column(name = "rsname")
    private String rsname;

    @Basic
    @Column(name = "permission")
    private String permission;

    @Basic
    @Column(name = "parentid")
    private Integer parentid;

    @Basic
    @Column(name = "url")
    private String url;

    @Basic
    @Column(name = "level")
    private Integer level;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "updateByUser")
    private Integer update_user;

    @Basic
    @Column(name = "updatetime")
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

    public String getRsname() {
        return rsname;
    }

    public void setRsname(String rsname) {
        this.rsname = rsname;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceEntity that = (ResourceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rsname != null ? !rsname.equals(that.rsname) : that.rsname != null) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;
        if (parentid != null ? !parentid.equals(that.parentid) : that.parentid != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (update_user != null ? !update_user.equals(that.update_user) : that.update_user != null) return false;
        if (update_time != null ? !update_time.equals(that.update_time) : that.update_time != null) return false;
        if (create_user != null ? !create_user.equals(that.create_user) : that.create_user != null) return false;
        return create_time != null ? create_time.equals(that.create_time) : that.create_time == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rsname != null ? rsname.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (parentid != null ? parentid.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (update_user != null ? update_user.hashCode() : 0);
        result = 31 * result + (update_time != null ? update_time.hashCode() : 0);
        result = 31 * result + (create_user != null ? create_user.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResourceEntity{" +
                "rsid=" + id +
                ", rsname='" + rsname + '\'' +
                ", permission='" + permission + '\'' +
                ", parentid=" + parentid +
                ", url='" + url + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", update_user=" + update_user +
                ", update_time=" + update_time +
                ", create_user=" + create_user +
                ", create_time=" + create_time +
                '}';
    }
}
