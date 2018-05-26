package me.ron.core.dto;

import java.io.Serializable;

/**
 * @author sharpron
 * @date 2018/5/15
 * @since 1.0
 */
public class TableHeader implements Serializable {

    private String name;
    private String desc;

    public TableHeader() {

    }

    public TableHeader(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
