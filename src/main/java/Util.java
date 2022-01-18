import java.sql.Connection;
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

   public void queryWeather(Connection c,String location) throws SQLException {
   DbUtil.queryDataOfWeather(c,location);
   }
}
