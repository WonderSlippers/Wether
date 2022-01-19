import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
    public static void insertInfo(Connection c,String location) throws Exception {
        City city= WeatherAPIUtil.getCity(location);

        DbUtil.insertDataOfCity(c,city.getId(),city.getName(),city.getLatitude(),city.getLongitude());
        Weather[]weathers=WeatherAPIUtil.getWeather(city.getId());
        for (int i=0;i<3;i++){
            DbUtil.insertDataOfWeather(c, city.getId(), weathers[i].getFxDate(),weathers[i].getTempMax(),weathers[i].getTempMin(),weathers[i].getTextDay());
        }
    }

    public static void updateInfo(Connection c,String location) throws Exception {
        City city= WeatherAPIUtil.getCity(location);
        Weather[]weathers=WeatherAPIUtil.getWeather(city.getId());
        DbUtil.updateDataOfWeather(c, city.getId(),weathers[2].getFxDate(),weathers[2].getTempMax(),weathers[2].getTempMin(),weathers[2].getTextDay(),weathers[0].getTextDay() );
    }


    public static void queryWeather(Connection c,String location) throws SQLException {
        ResultSet r=DbUtil.queryDataOfWeather(c,location);
        System.out.println("city_id:"+r.getInt("city_id"));
        System.out.println("name:"+r.getString("name"));
        System.out.println("lat:"+r.getString("lat"));
        System.out.println("lon:"+r.getString("lon"));
        System.out.println("fxDate:"+r.getString("fxDate"));
        System.out.println("tempMax:"+r.getString("tempMax"));
        System.out.println("tempMin:"+r.getString("tempMin"));
        System.out.println("textDay:"+r.getString("textDay"));
    }
}
