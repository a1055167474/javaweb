package cn.swu.edu;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.swu.edu.JdbcUtils;

/**
 * 封装了基本的增删改查的方法，以供子类继承使用。
 * 当前DAO 直接在方法中获取数据库连接。
 * 整个DAO 采取DBUtils 解决方案。
 * @author User
 *
 * @param <T> 当前 DAO 处理的实体类的类型是什么
 */
public class Dao<T> {

    private QueryRunner queryRunner = new QueryRunner();
    
    private Class<T> clazz;
    
    /**
     * 需要确定 clazz
     */
    public Dao(){
        //由得到子类的 Class得到父类 带泛型的那个类型。
        Type superClass = getClass().getGenericSuperclass();
        //先判断是不是那个类型
        if(superClass instanceof ParameterizedType){
            //是的话，强转一下
            ParameterizedType parameterizedType = (ParameterizedType) superClass;
            //获取真正的泛型的参数
            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            if(typeArgs != null && typeArgs.length >0){
                if(typeArgs[0] instanceof Class){
                    clazz = (Class<T>) typeArgs[0];
                }
            }
        }
    }
    
    
    /**
     * 查询，返回某一个字段的值
     */
    public <E> E getForValue(String sql, Object...args){
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.releaseConnection(connection);
        }
        return null;
    }
    
    /**
     * 查询，返回T 所对应的 List
     */
    public List<T> getForList(String sql, Object...args){
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.releaseConnection(connection);
        }
        return null;
    }
    
    /**
     * 查询,返回一个T 的实体类对象
     */
    public T get(String sql, Object...args){
        Connection connection = null;
        
        try {
            connection = JdbcUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.releaseConnection(connection);
        }
        return null;
    }
    
    /**
     * 通用的 insert 、delete、update 方法
     */
    public void update(String sql, Object...args){
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            queryRunner.update(connection, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.releaseConnection(connection);
        }
    }
}
