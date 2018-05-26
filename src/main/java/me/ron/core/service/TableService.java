package me.ron.core.service;

import me.ron.core.entity.Table;

/**
 * @author sharpron
 * @date 2018/5/10
 * @since 1.0
 */
public interface TableService {

    /**
     * 保存
     * @param table 表
     */
    void save(Table table);

    /**
     * 更新
     * @param table 表
     */
    void update(Table table);

    /**
     * 获取
     * @param id 表id
     * @return 表
     */
    Table get(Integer id);
}
