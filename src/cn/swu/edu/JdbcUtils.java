package cn.swu.edu;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC 操作的工具类
 * @author User
 *
 */
public class JdbcUtils {

    /**
     * 释放连接
     */
    public static void releaseConnection(Connection connection){
        
        try {
            if(connection != null){
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static DataSource dataSource = null;
    static{
        dataSource = new ComboPooledDataSource("mvcapp"); //传入的是 configName
    }
    /**
     * 获取连接
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        
        return dataSource.getConnection();
    }
}
