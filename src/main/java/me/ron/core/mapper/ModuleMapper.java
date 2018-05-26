package me.ron.core.mapper;

import me.ron.core.entity.Module;
import me.ron.core.provider.ModuleSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ModuleMapper {

    @Select("select * from " + Module.TABLE_NAME)
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = Date.class),
            @Result(column = "update_time", property = "updateTime", javaType = Date.class),
            @Result(column = "table_name", property = "tableName", javaType = String.class)
    })
    List<Module> getModules();

    @UpdateProvider(type = ModuleSqlProvider.class, method = "update")
    void update(Module module);

    @Insert("insert into "+ Module.TABLE_NAME + "(name, table_name) values(#{name}, #{tableName})")
    void insert(Module module);

    @Delete("delete from " + Module.TABLE_NAME + " where id=#{id}")
    void delete(int id);

    @Select("select * from " + Module.TABLE_NAME + " where id = #{id}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = Date.class),
            @Result(column = "update_time", property = "updateTime", javaType = Date.class),
            @Result(column = "table_name", property = "tableName", javaType = String.class)
    })
    Module get(Integer id);

    @Select("select * from " + Module.TABLE_NAME + " where id = #{id}")
    @Results({
            @Result(column = "create_time", property = "createTime", javaType = Date.class),
            @Result(column = "update_time", property = "updateTime", javaType = Date.class),
            @Result(column = "table_name", property = "tableName", javaType = String.class),
            @Result(column = "table_id", property = "table", one = @One(select = "me.ron.core.mapper.TableMapper.get"))
    })
    Module getByCascade(Integer id);

}
