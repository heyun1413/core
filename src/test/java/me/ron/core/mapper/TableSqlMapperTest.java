package me.ron.core.mapper;

import me.ron.core.entity.Column;
import me.ron.core.entity.Table;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableSqlMapperTest {

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private TableUtil tableUtil;


    private static final String TEST_TABLE_NAME = "test_table";



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

    @Test
    public void testSave() {
        int count = tableUtil.count("t_table");
        tableMapper.save(getTestTable());
        int count1 = tableUtil.count("t_table");
        Assert.assertEquals(1, count1 - count);
    }

    @Test(expected = BadSqlGrammarException.class)
    public void testRepeatSave() {
        tableUtil.truncate("t_table");
        tableMapper.save(getTestTable());
        tableMapper.save(getTestTable());
    }

    @Test
    public void testGet() {
        tableUtil.deleteTable("t_column");
        tableUtil.truncate("t_table");
        tableMapper.save(getTestTable());
        Table table = tableMapper.get(1);
        Assert.assertNotNull(table);
        Assert.assertNotNull(table.getName());
    }

    @Test
    public void testUpdate() {
        tableUtil.deleteTable("t_column");
        tableUtil.truncate("t_table");
        tableMapper.save(getTestTable());
        Table table = new Table();
        table.setId(1);
        table.setName("hello");
        tableMapper.update(table);
        Assert.assertEquals("hello", tableMapper.get(1).getName());

    }


}
