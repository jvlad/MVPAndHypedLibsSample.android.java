package net.derohimat.samplebasemvp.view.activity.main;

import net.derohimat.samplebasemvp.model.air.AirQualityPojo;
import net.derohimat.samplebasemvp.model.weather.WeatherPojo;
import net.derohimat.samplebasemvp.view.MvpView;

public interface MainMvpView extends MvpView {

    void showWeather(WeatherPojo weatherPojo);

    void showAirQuality (AirQualityPojo pojo);

    void showProgress();

    void hideProgress();
}