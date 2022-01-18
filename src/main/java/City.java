import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    @JSONField(name = "id")
    private int id;
    @JsonProperty("lat")
    @JSONField(name = "lat")
    private String latitude;
    @JsonProperty("lon")
    @JSONField(name = "lon")
    private String longitude;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public City(String name, int id, String latitude, String longitude) {
        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public City() {
this.id=1;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
