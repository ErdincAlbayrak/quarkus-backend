package com.erdincalbayrak;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherService implements IService {
    public String weatherOfDay() {
        return "a";
    }
}
