package net.derohimat.samplebasemvp.model.air;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.derohimat.samplebasemvp.model.weather.Clouds;
import net.derohimat.samplebasemvp.model.weather.Coord;
import net.derohimat.samplebasemvp.model.weather.Main;
import net.derohimat.samplebasemvp.model.weather.Sys;
import net.derohimat.samplebasemvp.model.weather.Weather;
import net.derohimat.samplebasemvp.model.weather.Wind;

import java.util.ArrayList;
import java.util.List;

public class AirQualityPojo {

    @SerializedName("country_description")
    @Expose
    private String airQualityDescription;

    @SerializedName("breezometer_color")
    @Expose
    private String airQualityColorIndicator;

    @SerializedName("breezometer_aqi")
    @Expose
    private Integer airQualityNumberIndicator;

    @Override
    public String toString() {
        return "WeatherPojo {" +
                "airQualityDescription= " + airQualityDescription +
                ", airQualityColorIndicator= " + airQualityColorIndicator +
                ", airQualityNumberIndicator= " + airQualityNumberIndicator +
                '}';
    }

    public String getAirQualityDescription() {
        return airQualityDescription;
    }

    public void setAirQualityDescription(String airQualityDescription) {
        this.airQualityDescription = airQualityDescription;
    }

    public String getAirQualityColorIndicator() {
        return airQualityColorIndicator;
    }

    public void setAirQualityColorIndicator(String airQualityColorIndicator) {
        this.airQualityColorIndicator = airQualityColorIndicator;
    }

    public Integer getAirQualityNumberIndicator() {
        return airQualityNumberIndicator;
    }

    public void setAirQualityNumberIndicator(Integer airQualityNumberIndicator) {
        this.airQualityNumberIndicator = airQualityNumberIndicator;
    }
}