package net.derohimat.samplebasemvp.model.air;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirQualityPojo {

    @SerializedName("country_description")
    @Expose
    private String description;

    @SerializedName("breezometer_color")
    @Expose
    private String colorIndicator;

    @SerializedName("breezometer_aqi")
    @Expose
    private Integer numberIndicator;

    @Override
    public String toString() {
        return "WeatherPojo {" +
                "description= " + description +
                ", colorIndicator= " + colorIndicator +
                ", numberIndicator= " + numberIndicator +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColorIndicator() {
        return colorIndicator;
    }

    public void setColorIndicator(String colorIndicator) {
        this.colorIndicator = colorIndicator;
    }

    public Integer getNumberIndicator() {
        return numberIndicator;
    }

    public void setNumberIndicator(Integer numberIndicator) {
        this.numberIndicator = numberIndicator;
    }
}