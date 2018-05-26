package me.ron.core.provider;

import com.google.common.base.Strings;
import me.ron.core.entity.Column;
import me.ron.core.entity.ColumnType;
import me.ron.core.entity.Table;

/**
 * @author sharpron
 * @date 2018/5/11
 * @since 1.0
 */
public class DbOperationProvider {

    public static String addColumn(Column column) {
        assert !Strings.isNullOrEmpty(column.getTable().getName());
        assert !Strings.isNullOrEmpty(column.getName());
        assert column.getColumnType() != null;
        return String.format("alter table %s add column %s %s %s",
                column.getTable().getName(),
                column.getName(),
                column.getColumnType().name(),
                column.isNullable() ? "" : "not null");
    }

    public static String updateColumn(Column origin, Column newColumn) {
        String sql = "alter table %s change %s %s %s %s";
        return String.format(sql,
                origin.getTable().getName(),
                origin.getName(),
                newColumn.getName(), newColumn.getColumnType().name(),
                newColumn.isNullable() ? "" : "not null");
    }

    /**
     * 创建表
     * @param table
     * @return
     */
    public static String createTable(final Table table) {
        StringBuilder sql = new StringBuilder("\ncreate table ");
        sql.append(table.getName())
                .append("(")
                .append("\n\tid integer primary key auto_increment,");
        for (Column column : table.getColumns()) {
            sql.append("\n\t")
                    .append(byColumn(column))
                    .append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        return sql.append("\n);").toString();
    }


    /**
     * 对象生成sql列
     * @param column
     * @return sql field
     */
    private static String byColumn(Column column) {
        return column.getName() + " " +
                byType(column.getColumnType()) +
                (column.isNullable() ? "" : " not null");
    }

    /**
     * 类型映射
     * @param columnType
     * @return
     */
    private static String byType(ColumnType columnType) {
        switch (columnType) {
            case TEXT: return "varchar(255)";
            case DOUBLE: return "double";
            case INTEGER: return "integer";
            case LONGTEXT: return "longtext";
            default: throw new AssertionError();
        }
    }
}
