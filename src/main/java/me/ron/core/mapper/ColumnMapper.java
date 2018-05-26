package me.ron.core.mapper;

import me.ron.core.entity.Column;
import me.ron.core.entity.ColumnType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnMapper {


    @Select("select * from t_column where id = #{id}")
    @Results({
            @Result(column = "column_type", property = "columnType", javaType = ColumnType.class),
            @Result(column = "table_id", property = "table",
                one = @One(select = "me.ron.core.mapper.TableMapper.get")),
            @Result(column = "foreign_key_name", property = "foreignKeyName")
    })
    Column get(@Param("id") Integer id);

    @Insert("insert into t_column values(default, #{name}, #{nullable}, #{columnType}, #{table.id}, #{foreignKeyName}, #{relationTable.id})")
    void save(Column column);

    @Update("update t_column set name = #{name}, nullable = #{nullable}, column_type = #{columnType}, table_id = #{table.id}, foreign_key_name = #{foreignKeyName}, relation_table_id = #{relationTable.id} where id = #{id}")
    void update(Column column);

    @Update("delete from t_column where id = #{id}")
    void remove(Column column);

    @Select("select * from t_column where table_id = #{tableId}")
    @Results({
            @Result(column = "column_type", property = "columnType", javaType = ColumnType.class),
            @Result(column = "table_id", property = "table",
                    one = @One(select = "me.ron.core.mapper.TableMapper.get")),
            @Result(column = "foreign_key_name", property = "foreignKeyName")
    })
    List<Column> getColumnsByTableId(@Param("tableId") String tableId);
}
