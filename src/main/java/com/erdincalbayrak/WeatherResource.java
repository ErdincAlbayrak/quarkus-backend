package com.erdincalbayrak;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/weather")
public class WeatherResource implements IResource {
    @Inject
    WeatherService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String fullWeatherForecast() {
        return service.weatherForecast(new HashMap<>());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/city/{city}")
    public String forecastForCity(@PathParam("city") String city) {
        final Map<String,String> queryParameters = new HashMap();
        queryParameters.put("city", city);
        return service.weatherForecast(queryParameters);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/date/{date}")
    public String forecastForDate(@PathParam("date") String date) {
        final Map<String,String> queryParameters = new HashMap();
        queryParameters.put("wdate", date);
        return service.weatherForecast(queryParameters);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/city/{city}/date/{date}")
    public String forecastForCityAndDate(@PathParam("city") String city, @PathParam("date") String date) {
        final Map<String,String> queryParameters = new HashMap();
        queryParameters.put("city", city);
        queryParameters.put("wdate", date);
        return service.weatherForecast(queryParameters);
    }

}
