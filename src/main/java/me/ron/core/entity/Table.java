package me.ron.core.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {

    private Integer id;
    @NotNull
    @Pattern(regexp = "^[0-9a-zA-Z_]{5,}$", message = "表名只能包括字母、数字、下划线！")
    private String name;

    @NotNull(message = "至少需要添加一个列")
    @Valid
    private List<Column> columns;

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

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}
