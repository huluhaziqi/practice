package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {

    //数据库连接
    private Connection connection;
    //sql语句的执行对象
    private PreparedStatement ps;
    //查询返回结果集合
    private ResultSet resultSet;

    public JdbcUtil(String driver, String url, String password, String username) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, (password));
            System.out.println("数据库连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateByParams(String sql, List params) {
        int result = -1;
        try {
            ps = connection.prepareStatement(sql);
            if (null != params && !params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0 ? true : false;
    }

    public List<Map> selectByParams(String sql, List params){
        List<Map> result = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            if(null != params && !params.isEmpty()){
                for(int i = 0; i < params.size(); i++){
                    ps.setObject(i + 1, params.get(i));
                }
            }
            resultSet = ps.executeQuery();
           ResultSetMetaData resultSetMetaData =  resultSet.getMetaData();
            int columns = resultSetMetaData.getColumnCount();
            while (resultSet.next()){
                HashMap<String,Object> map = new HashMap<>();
                for(int i = 0; i < columns; i++){
                    String cols_name = resultSetMetaData.getColumnName(i + 1);
                    Object cols_object = resultSet.getObject(cols_name);
                    if(cols_name == null){
                        cols_object = "";
                    }
                    map.put(cols_name,cols_object);
                }
                result.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void release() {
        try {
            if (null != connection) {
                connection.close();
            }
            if (null != ps) {
                ps.close();
            }
            if (null != resultSet) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
