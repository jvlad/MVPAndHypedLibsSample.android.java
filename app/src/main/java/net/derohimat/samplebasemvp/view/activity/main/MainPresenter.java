package net.derohimat.samplebasemvp.view.activity.main;

import android.content.Context;
import android.support.v4.media.MediaBrowserCompat;

import net.derohimat.baseapp.presenter.BasePresenter;
import net.derohimat.samplebasemvp.BaseApplication;
import net.derohimat.samplebasemvp.R;
import net.derohimat.samplebasemvp.data.remote.APIService;
import net.derohimat.samplebasemvp.events.MessagesEvent;
import net.derohimat.samplebasemvp.model.air.AirQualityPojo;
import net.derohimat.samplebasemvp.model.weather.WeatherPojo;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class MainPresenter implements BasePresenter<MainMvpView> {



    @Inject
    public MainPresenter(Context context) {
        ((BaseApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Inject
    APIService mAPIService;
    @Inject
    EventBus mEventBus;

    private MainMvpView mMainMvpView;
    private Subscription weatherResponseSubscription;
    private Subscription airQualityResponseSubscription;
    private WeatherPojo mWeatherPojo;
    public AirQualityPojo mAirQualityPojo;

    @Override
    public void attachView(MainMvpView view) {
        mMainMvpView = view;
    }

    @Override
    public void detachView() {
        mMainMvpView = null;
        if (weatherResponseSubscription != null) weatherResponseSubscription.unsubscribe();
    }

    public void loadWeather(String from_where) {

        String weatherFromWhere = from_where.trim();
        if (weatherFromWhere.isEmpty()) return;

        mMainMvpView.showProgress();
        if (weatherResponseSubscription != null) weatherResponseSubscription.unsubscribe();

        BaseApplication baseApplication = BaseApplication.get(mMainMvpView.getContext());

        weatherResponseSubscription = mAPIService.getWeatherForCity(weatherFromWhere, "metric")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(baseApplication.getSubscribeScheduler())
                .subscribe(new Subscriber<WeatherPojo>() {
                    @Override
                    public void onCompleted() {
                        Timber.i("Weather loaded " + mWeatherPojo);
                        // TODO: 06/10/16 Why we are callind "show..." twice?
                        mMainMvpView.showWeather(mWeatherPojo);
                        mMainMvpView.hideProgress();
                        mMainMvpView.showWeather(mWeatherPojo);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.e("Error loading weather", error);
                        if (isHttp404(error)) {
                            mEventBus.post(new MessagesEvent(false, baseApplication.getString(R.string.error_not_found)));
                        } else {
                            mEventBus.post(new MessagesEvent(false, baseApplication.getString(R.string.error_loading_weather)));
                        }

                        mMainMvpView.hideProgress();
                    }

                    @Override
                    public void onNext(WeatherPojo weatherPojo) {
                        mWeatherPojo = weatherPojo;
                    }
                });
    }

    public void loadAirQuality() {
        mMainMvpView.showProgress();
        if (airQualityResponseSubscription != null) airQualityResponseSubscription.unsubscribe();

        BaseApplication baseApplication = BaseApplication.get(mMainMvpView.getContext());
        // TODO: 06/10/16 replace harcoded Lat and Long request parameters
        airQualityResponseSubscription = mAPIService.getAirQualityByLatLon(40.7324296, -73.9977264)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(baseApplication.getSubscribeScheduler())
                .subscribe(new Subscriber<AirQualityPojo>() {

                    @Override
                    public void onCompleted() {
                        Timber.i("Air loaded " + mAirQualityPojo);
                        // TODO: 06/10/16 Why we are callind "show..." twice?
                        mMainMvpView.showAirQuality(mAirQualityPojo);
                        mMainMvpView.hideProgress();
                        mMainMvpView.showAirQuality(mAirQualityPojo);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.e("Error loading Air Quality", error);
                        if (isHttp404(error)) {
                            mEventBus.post(new MessagesEvent(false, baseApplication.getString(R.string.error_not_found)));
                        } else {
                            mEventBus.post(new MessagesEvent(false, baseApplication.getString(R.string.error_loading_air_quality)));
                        }

                        mMainMvpView.hideProgress();
                    }

                    @Override
                    public void onNext(AirQualityPojo airQualityPojo) {
                        mAirQualityPojo = airQualityPojo;
                    }
                });
    }

    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }
}