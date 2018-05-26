package me.ron.core.mapper;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableUtilTest {

    @Autowired
    private TableUtil tableUtil;

    @Test
    public void testQueryTable() {
        Assert.assertNotNull(tableUtil.queryTable("t_module"));
    }

    @Test
    public void testCount() {
        Assert.assertEquals(tableUtil.count("t_module"), 0);
    }

    @Test
    public void testDropTable() {
        tableUtil.deleteTable("t_module");
        Assert.assertNull(tableUtil.queryTable("t_module"));
    }

    @Test
    public void testTruncate() {
        tableUtil.truncate("t_module");
        Assert.assertEquals(0, tableUtil.count("t_module"));
    }

}
