package vn.plusplus.spring.interfaces;

import java.util.Date;
import java.util.List;

public class WeatherBackend implements WeatherInterface {
    @Override
    public String getCurrentTime() {
        return String.valueOf(new Date());
    }

    @Override
    public WeatherIntraday getWeatherOneDay(String date, String location) {
        WeatherIntraday response = new WeatherIntraday();
        //Truy van database lay thong tin location
        //Truy van database lay thoi tiet temp, hum trong bang temp_hum cua ngay date
        //Truy van database lay thoi tiet cloud trong bang temp_hum cua ngay date

        return response;
    }

    @Override
    public List<WeatherIntraday> getWeatherWeek(String location, String startDate, String endDate) {
        return null;
    }
}
