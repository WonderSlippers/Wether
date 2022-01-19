

//1.创建连接池


import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbUtil {

    public static Connection getConnection() throws SQLException {


        //数据源配置
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1/weather?serverTimezone=UTC");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //这个可以缺省的，会根据url自动识别
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //下面都是可选的配置
        dataSource.setInitialSize(10);  //初始连接数，默认0
        dataSource.setMaxActive(30);  //最大连接数，默认8
        dataSource.setMinIdle(10);  //最小闲置数
        dataSource.setMaxWait(2000);  //获取连接的最大等待时间，单位毫秒
        dataSource.setPoolPreparedStatements(true); //缓存PreparedStatement，默认false
        dataSource.setMaxOpenPreparedStatements(20); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement，所以可以省略上一句代码

        //获取连接
        Connection connection = dataSource.getConnection();
        return connection;
    }

    //PreparedStatement接口
    public static void insertDataOfCity(Connection connection, int id, String name, String lat, String lon) throws SQLException {
        String sql2 = "insert into cities (city_id,name,lat,lon) values (" + id + ",'" + name + "','" + lat + "','" + lon + "')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.execute();


    }

    public static void insertDataOfWeather(Connection connection, int id, String fxDate, int tempMax, int tempMin, String textDay) throws SQLException {
        String sql2 = "insert into cities (city_id,fxDate,tempMax,tempMin,textDay,) values (" + id + ",'" + tempMax + "','" + tempMin + "'" + ",'" + textDay + "')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.execute();

    }
    public static void updateDataOfWeather(Connection connection, int id, String fxDate, int tempMax, int tempMin, String textDay,String oldTextDay) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE weathers SET fxDate=?,tempMax=?,tempMin=?，textDay=? WHERE city_id=?&&textDay=?")) {
            ps.setObject(1, fxDate); // 注意：索引从1开始
            ps.setObject(2, tempMax);
            ps.setObject(3, tempMin);
            ps.setObject(4, textDay);
            ps.setObject(5, id);
            ps.setObject(6, oldTextDay);
            int n = ps.executeUpdate(); // 返回更新的行数
        }

    }

    public static ResultSet queryDataOfWeather(Connection connection, String location) throws SQLException {
        String sql2 = "select * from cities as t1,weathers as t2 where t1.name=" + location + "&&t1.city_id=t2.city_id";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        ResultSet r = preparedStatement.executeQuery();
        return r;
    }
}
