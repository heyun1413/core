package me.ron.core.entity;

/**
 * @author sharpron
 * @date 2018/5/15
 * @since 1.0
 */
public class InputType {

    private Integer id;
    private String label;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
