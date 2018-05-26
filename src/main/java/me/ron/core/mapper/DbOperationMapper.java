package me.ron.core.mapper;

import me.ron.core.entity.Column;
import me.ron.core.entity.Table;
import me.ron.core.provider.DbOperationProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

/**
 * @author sharpron
 * @date 2018/5/11
 * @since 1.0
 */
@Repository
public interface DbOperationMapper {

    @UpdateProvider(type = DbOperationProvider.class, method = "createTable")
    void createTable(Table table);


    @Update("alter table ${originTableName} rename ${newTableName}")
    void modifyTableName(@Param("originTableName") String originTableName,
                         @Param("newTableName") String newTableName);

    @Update("alter table ${table.name} add constraint ${foreignKeyName} foreign key(${name}) references ${relationTable.name}(id)")
    void addForeignKey(Column column);

    @Update("alter table ${table.name} drop foreign key ${foreignKeyName}")
    void removeForeignKey(Column column);

    @UpdateProvider(type = DbOperationProvider.class, method = "addColumn")
    void addColumn(Column column);

    @UpdateProvider(type = DbOperationProvider.class, method = "updateColumn")
    void modifyColumn(Column origin, Column newColumn);

    @Update("alter table ${table.name} drop column ${name}")
    void removeColumn(Column column);
}
