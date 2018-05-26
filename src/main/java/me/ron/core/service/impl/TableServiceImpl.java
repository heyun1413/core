package me.ron.core.service.impl;

import com.google.common.base.Strings;
import me.ron.core.entity.Column;
import me.ron.core.entity.ColumnType;
import me.ron.core.entity.Table;
import me.ron.core.exception.ResourceNotFoundException;
import me.ron.core.mapper.ColumnMapper;
import me.ron.core.mapper.DbOperationMapper;
import me.ron.core.mapper.TableMapper;
import me.ron.core.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sharpron
 * @date 2018/5/10
 * @since 1.0
 */
@Service
public class TableServiceImpl implements TableService {


    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private ColumnMapper columnMapper;

    @Autowired
    private DbOperationMapper dbOperationMapper;

    @Transactional
    @Override
    public void save(Table table) {
        assert table.getId() == null;
        assert table.getName() != null;
        assert table.getColumns() != null;
        tableMapper.save(table);
        for (Column column : table.getColumns()) {
            column.setTable(table);
            if (column.isForeignKey()) {
                column.setForeignKeyName(column.generateForeignKeyName());
                column.setColumnType(ColumnType.INTEGER);
            }
            columnMapper.save(column);
        }
        dbOperationMapper.createTable(table);
        for (Column column : table.getColumns()) {
            if (column.isForeignKey()) {
                dbOperationMapper.addForeignKey(column);
            }
        }
    }

    @Transactional
    @Override
    public void update(Table table) {
        assert table != null;
        final Table old = get(table.getId());
        modifyTable(old, table);
        addOrModifyColumns(table);
        removeColumns(old, table);
    }

    private void modifyTable(Table old, Table newer) {
        assert newer.getId() != null;
        assert !Strings.isNullOrEmpty(newer.getName());
        dbOperationMapper.modifyTableName(old.getName(), newer.getName());
        tableMapper.update(newer);
    }

    private void addOrModifyColumns(Table table) {
        assert table.getColumns() != null;
        for (Column column : table.getColumns()) {
            column.setTable(table);
            if (column.getId() == null) {
                dbOperationMapper.addColumn(column);
                addForeignWhenNeed(column);
                columnMapper.save(column);
            } else {
                Column oldColumn = columnMapper.get(column.getId());
                if (oldColumn == null) {
                    throw new ResourceNotFoundException(String.format("table:column:%s not found!", column.getId()));
                }
                dbOperationMapper.modifyColumn(oldColumn, column);
                updateForeignKeyWhenNeed(oldColumn, column);
                columnMapper.update(column);
            }
        }
    }

    private void addForeignWhenNeed(Column column) {
        if (column.isForeignKey()) {
            column.setForeignKeyName(column.generateForeignKeyName());
            dbOperationMapper.addForeignKey(column);
        }
    }

    private void updateForeignKeyWhenNeed(Column old, Column newer) {
        if (old.canModifyForeignKey(newer)) {
            newer.setForeignKeyName(newer.generateForeignKeyName());
            dbOperationMapper.modifyColumn(old, newer);
        }
    }


    private void removeColumns(Table old, Table table) {
        for (Column column : getNeedRemoveByCompare(old, table)) {
            column.setTable(table);
            if (!Strings.isNullOrEmpty(column.getForeignKeyName())) {
                dbOperationMapper.removeForeignKey(column);
            }
            dbOperationMapper.removeColumn(column);
            columnMapper.remove(column);
        }
    }


    @Override
    public Table get(Integer id) {
        Table table = tableMapper.get(id);
        if (table == null) {
            throw new ResourceNotFoundException(String.format("table:%s not found!", id));
        }
        return table;
    }

    /**
     * 通过对比获取需要删除的列
     *
     * @param old
     * @param newer
     * @return
     */
    private static Collection<Column> getNeedRemoveByCompare(Table old, Table newer) {
        assert old.getColumns() != null;
        assert newer.getColumns() != null;
        Map<Integer, Column> map = listToMap(old.getColumns());
        for (Column currentColumn : newer.getColumns()) {
            if (currentColumn.getId() != null) {
                map.remove(currentColumn.getId());
            }
        }
        return map.values();
    }

    private static Map<Integer, Column> listToMap(List<Column> columns) {
        assert columns != null;
        final Map<Integer, Column> map = new HashMap<>();
        for (Column originColumn : columns) {
            map.put(originColumn.getId(), originColumn);
        }
        return map;
    }
}
