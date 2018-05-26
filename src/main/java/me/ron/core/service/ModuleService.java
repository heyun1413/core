package me.ron.core.service;

import me.ron.core.bean.Pager;
import me.ron.core.entity.Module;

/**
 * @author ron
 */
public interface ModuleService {

    /**
     * 通过页号和页大小分页获取数据
     * @param pageNum 页大小
     * @param pageSize
     * @return
     */
    Pager<Module> getModules(int pageNum, int pageSize);

    void add(Module module);

    void remove(Integer id);

    void update(Module module);

    Module get(Integer id);

    Module getByCascade(Integer id);

}
