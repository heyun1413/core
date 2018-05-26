package me.ron.core.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author sharpron
 * @date 2018/5/11
 * @since 1.0
 */
@Repository
public interface SqlMapper {

    @Update("${sql}")
    void exec(@Param("sql") String sql);
}
