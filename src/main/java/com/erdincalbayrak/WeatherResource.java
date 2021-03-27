package com.erdincalbayrak;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/weather")
public class WeatherResource implements IResource {

    @Inject
    WeatherService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String weatherOfDay() {
        return service.weatherOfDay();
    }


//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/greeting/{name}")
//    public String greeting(@PathParam String name) {
//        return service.greeting(name);
//    }
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello() {
//        return "Hello RESTEasy";
//    }
}
