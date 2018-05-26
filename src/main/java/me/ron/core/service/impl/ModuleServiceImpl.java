package me.ron.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import me.ron.core.bean.Pager;
import me.ron.core.entity.Module;
import me.ron.core.mapper.ModuleMapper;
import me.ron.core.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ron
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public Pager<Module> getModules(int pageNum, int pageSize) {
        Page<Module> modulePage = PageHelper.startPage(pageNum, pageSize);
        return new Pager<>(pageNum, modulePage.getTotal(), moduleMapper.getModules());
    }

    @Override
    public void add(Module module) {
        moduleMapper.insert(module);
    }

    @Override
    public void remove(Integer id) {
        moduleMapper.delete(id);
    }

    @Override
    public void update(Module module) {
        moduleMapper.update(module);
    }

    @Override
    public Module get(Integer id) {
        return moduleMapper.get(id);
    }

    @Override
    public Module getByCascade(Integer id) {
        return moduleMapper.getByCascade(id);
    }
}
