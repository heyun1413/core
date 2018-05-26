package me.ron.core.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class Column implements Serializable {

    private Integer id;

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z_]{5,}$", message = "列名只能包括字母、数字、下划线！")
    private String name;
    private boolean nullable;
    private ColumnType columnType = ColumnType.TEXT;

    @Null
    private String foreignKeyName;
    private Table table;
    private Table relationTable = new Table();

    private String label;
    private boolean readable;
    private InputType inputType;

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

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public void setForeignKeyName(String foreignKeyName) {
        this.foreignKeyName = foreignKeyName;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getRelationTable() {
        return relationTable;
    }

    public void setRelationTable(Table relationTable) {
        this.relationTable = relationTable;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public boolean isForeignKey() {
        return getRelationTable() != null && getRelationTable().getId() != null;
    }

    public boolean canModifyForeignKey(Column newer) {
        return newer.isForeignKey() && !newer.getRelationTable().getId().equals(getRelationTable().getId());
    }

    public String generateForeignKeyName() {
        assert getRelationTable() != null;
        return String.format("fk_reference_%s", getRelationTable().getName());
    }

    @Override
    public String toString() {
        return "Column{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nullable=" + nullable +
                ", columnType=" + columnType +
                ", foreignKeyName='" + foreignKeyName + '\'' +
                '}';
    }
}
