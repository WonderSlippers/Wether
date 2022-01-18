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

/**
 *普通的GET请求
 */

public class DoGET {
    public static void main(String[] args) throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("https://geoapi.qweather.com/v2/city/lookup?key=49a0b0d06a4d4685b84902497c425ff2&location=福州");

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //内容写入文件

                System.out.println("内容长度："+content.length());
                System.out.println(content);
                City a=JSON.parseObject(content.toString(), City.class);
                City b=new City("a",1,"b","c");
                String json1 = JSON.toJSONString(b);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                City c = objectMapper.readValue(content.toString(), City.class);
                System.out.println(json1);
                System.out.println(c);
                System.out.println(a.toString());

                JSONObject jsonObject = JSONObject.parseObject(content.toString());
                JSONArray result = jsonObject.getJSONArray("location");
                JSONObject aaa=result.getJSONObject(0);
                a=JSON.parseObject(aaa.toJSONString(), City.class);
                System.out.println(a.toString());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
            httpclient.close();
        }
    }
}