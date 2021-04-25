package vn.plusplus.spring.designpattern.interfaces;

import java.util.List;

public interface WeatherInterface {
    String getCurrentTime();
    WeatherIntraday getWeatherOneDay(String date, String location);
    List<WeatherIntraday> getWeatherWeek(String location, String startDate, String endDate);
}
