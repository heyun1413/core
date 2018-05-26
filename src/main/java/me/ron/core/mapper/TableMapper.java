package me.ron.core.mapper;

import me.ron.core.entity.Table;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TableMapper {

    @Insert("insert into t_table values(default, #{name})")
    @Options(useGeneratedKeys = true)
    void save(Table table);

    @Update("update t_table set name=#{name} where id = #{id}")
    void update(Table table);

    @Select("select id, name from t_table where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "columns", many = @Many(select = "me.ron.core.mapper.ColumnMapper.getColumnsByTableId"))
    })
    Table get(@Param("id") int id);
}
