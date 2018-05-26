package me.ron.core.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface TableUtil {

    @Select("SELECT table_name FROM information_schema.TABLES WHERE table_name = #{tableName};")
    String queryTable(String tableName);

    @Select("select count(*) from ${tableName}")
    int count(@Param("tableName") String tableName);

    @Update("drop table if exists ${tableName}")
    void deleteTable(@Param("tableName") String tableName);

    @Update("truncate table ${tableName}")
    void truncate(@Param("tableName") String tableName);

    @Update("${sql}")
    void exec(@Param("sql") String sql);
}