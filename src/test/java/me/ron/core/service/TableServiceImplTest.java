package me.ron.core.service;

import me.ron.core.entity.Column;
import me.ron.core.entity.Table;
import me.ron.core.mapper.SqlMapper;
import me.ron.core.mapper.TableUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author sharpron
 * @date 2018/5/11
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TableServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(TableServiceImplTest.class);
    private static final String TEST_TABLE_NAME = "t_test";
    private static final String TEST_NEWER_TABLE_NAME = "t_test_newer";

    @Autowired
    private TableService tableService;

    @Autowired
    private SqlMapper sqlMapper;

    @Autowired
    private TableUtil tableUtil;

    private Integer id;

    @Before
    public void onBefore() {
        tableUtil.deleteTable(TEST_TABLE_NAME);
        tableUtil.deleteTable(TEST_NEWER_TABLE_NAME);
        sqlMapper.exec("delete from t_column");
        sqlMapper.exec("delete from t_table");
        sqlMapper.exec("drop table if exists t_student");
        sqlMapper.exec("drop table if exists t_teacher");
        sqlMapper.exec("drop table if exists t_test");
    }

    @After
    public void onAfter() {

    }

    @Test
    public void testSave() {
        Table table = new Table();
        table.setName(TEST_TABLE_NAME);
        Column column = new Column();
        column.setName("name");

        Column column2 = new Column();
        column2.setName("name2");
        table.setColumns(Arrays.asList(column, column2));

        tableService.save(table);
        id = table.getId();
    }

    @Test
    public void testUpdate() {
        Table table = tableService.get(id);
        table.setName(TEST_NEWER_TABLE_NAME);
        Column column = new Column();
        column.setName("abc");
        table.getColumns().get(1).setName("name2_new");
        table.getColumns().add(column);
        table.getColumns().remove(0);
        tableService.update(table);
    }


    public int testRelation() {
        Table teacherTable = new Table();
        teacherTable.setName("t_teacher");
        Column column = new Column();
        column.setName("name");
        teacherTable.setColumns(Arrays.asList(column));
        tableService.save(teacherTable);

        Table studentTable = new Table();
        studentTable.setName("t_student");
        Column relationColumn = new Column();
        relationColumn.setName("teacher_id");
        relationColumn.setRelationTable(teacherTable);
        studentTable.setColumns(Arrays.asList(column, relationColumn));
        tableService.save(studentTable);
        return studentTable.getId();
    }

    @Test
    public void testRelationUpdate() {
        int i = testRelation();
        Table table = tableService.get(i);
        table.getColumns().remove(table.getColumns().size() - 1);
        tableService.update(table);
    }

}
