package me.ron.core.provider;

import com.google.common.base.Strings;
import me.ron.core.entity.Module;
import org.apache.ibatis.jdbc.SQL;

public class ModuleSqlProvider {


    public String update(final Module module) {
        return new SQL() {
            {
                UPDATE(Module.TABLE_NAME);

                if (!Strings.isNullOrEmpty(module.getName())) {
                    SET("name = #{name}");
                }

                if (!Strings.isNullOrEmpty(module.getTableName())) {
                    SET("name = #{table_name}");
                }

                WHERE("id = #{d}");
            }
        }.toString();
    }
}
