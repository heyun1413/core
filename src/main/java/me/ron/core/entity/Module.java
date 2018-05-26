package me.ron.core.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

public class Module implements Serializable {

    public static final String TABLE_NAME = "t_module";

    private Integer id;

    @NotBlank(message = "模块名不能为空")
    private String name;

    @Null(message = "不能传递创建时间（会自动生成）")
    private Date createTime;
    @Null(message = "不能传递更新时间（会自动生成）")
    private Date updateTime;

    @Null(message = "不能传递表名（会自动生成）")
    private String tableName;

    private Status status;

    @NotNull
    @Valid
    private Table table;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
