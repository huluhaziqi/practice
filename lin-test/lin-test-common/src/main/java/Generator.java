import com.lin.test.common.util.MyBatisGeneratorUtil;
import com.lin.test.common.util.PropertiesFileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Generator {
    private static String driver = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private static String jdbc_url = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private static String database = "lin";
    private static String password =  PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
    private static String username = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private static String table_prefix = "upms_";
    private static String package_name = "com.lin.test";
    private static String module = "lin-test";
    private static Map<String, String> last_insert_id_tables = new HashMap<>();
    static {
        last_insert_id_tables.put("upms_user", "user_id");
    }

    public static void main(String[] args) throws IOException {
        MyBatisGeneratorUtil.generator(module,driver,jdbc_url,database,password,username,table_prefix,package_name,last_insert_id_tables);
    }
}
