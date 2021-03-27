package com.erdincalbayrak;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class WeatherService implements IService {
    @Inject
    DatabaseDataSource dataSource;

    public String weatherForecast(Map<String, String> queryParameters) {
        return dataSource.getData(queryParameters);
    }
}
