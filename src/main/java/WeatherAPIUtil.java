import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WeatherAPIUtil {
    public static City getCity(String location) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("https://geoapi.qweather.com/v2/city/lookup?key=49a0b0d06a4d4685b84902497c425ff2&location=" + location);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");


                JSONObject jsonObject = JSONObject.parseObject(content.toString());
                JSONArray result = jsonObject.getJSONArray("location");
                JSONObject aaa = result.getJSONObject(0);
                City city = JSON.parseObject(aaa.toJSONString(), City.class);
                return city;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
            httpclient.close();
        }
return null;
    }
    public static Weather[] getWeather(int id) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("https://devapi.qweather.com/v7/weather/3d?key=49a0b0d06a4d4685b84902497c425ff2&location=" +id);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");


                JSONObject jsonObject = JSONObject.parseObject(content.toString());
                JSONArray result = jsonObject.getJSONArray("daily");
                JSONObject day1Weather = result.getJSONObject(0);
                JSONObject day2Weather = result.getJSONObject(1);
                JSONObject day3Weather = result.getJSONObject(2);
                Weather[]weathers=new Weather[3];
                 weathers[0] = JSON.parseObject(day1Weather.toJSONString(), Weather.class);
                 weathers[1] = JSON.parseObject(day2Weather.toJSONString(), Weather.class);
                 weathers[2] = JSON.parseObject(day3Weather.toJSONString(), Weather.class);
                return weathers;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
            httpclient.close();
        }
        return null;
    }
}
