package me.ron.core.mapper;

import me.ron.core.entity.Column;
import me.ron.core.entity.Table;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author sharpron
 * @date 2018/5/10
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbOperationMapperTest {

    @Autowired
    private DbOperationMapper dbOperationMapper;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private TableUtil tableUtil;

    private static final String TEST_TABLE_NAME = "db_test_table";

    @Before
    public void setUp() {
        tableUtil.exec("create table test_column(id int, module_id int)");
    }

    @After
    public void finish() {
        tableUtil.deleteTable("test_column");
    }

    @Test
    public void testAddForeignKey() {
        Column column = new Column();
        column.setName("module_id");

        Table table = new Table();
        table.setName("test_column");
        column.setTable(table);

        Table relation = new Table();
        relation.setName("t_module");
        column.setForeignKeyName(table.getName() + "_" + relation.getName());
        column.setRelationTable(relation);
        dbOperationMapper.addForeignKey(column);
    }

    @Test
    public void testRemoveForeignKey() {
        Column column = new Column();
        column.setName("module_id");

        Table table = new Table();
        table.setName("test_column");
        column.setTable(table);

        Table relation = new Table();
        relation.setName("t_module");
        column.setForeignKeyName(table.getName() + "_" + relation.getName());
        column.setRelationTable(relation);
        dbOperationMapper.addForeignKey(column);
        dbOperationMapper.removeForeignKey(column);
    }

    @Test
    public void testAddColumn() {
        Column column = new Column();
        column.setName("new_column");

        Table table = new Table();
        table.setName("test_column");
        column.setTable(table);
        dbOperationMapper.addColumn(column);
    }

    @Test
    public void testChangeColumn() {
        testAddColumn();
        Column column = new Column();
        column.setName("new_column");

        Table table = new Table();
        table.setName("test_column");
        column.setTable(table);

        Column newCol = new Column();
        newCol.setName("new_column_a");

        newCol.setTable(table);
        dbOperationMapper.modifyColumn(column, newCol);
    }

    @Test
    public void testRemoveColumn() {
        testAddColumn();
        Column column = new Column();
        column.setName("new_column");
        Table table = new Table();
        table.setName("test_column");
        column.setTable(table);
        dbOperationMapper.removeColumn(column);
    }

    @Test
    public void testCreateTable() {
        dbOperationMapper.createTable(getTestTable());
        String test_table = tableUtil.queryTable(TEST_TABLE_NAME);
        Assert.assertNotNull(test_table);
    }

    private Table getTestTable() {
        Table table = new Table();
        table.setName(TEST_TABLE_NAME);
        table.setColumns(Arrays.asList(
                createColumn()
        ));
        return table;
    }

    private Column createColumn() {
        Column column = new Column();
        column.setName("test1");
        column.setNullable(false);
        return column;
    }

}
