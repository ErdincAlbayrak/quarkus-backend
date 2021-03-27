package com.erdincalbayrak;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DatabaseDataSourceTest {

    public static final DatabaseDataSource DATABASE_DATA_SOURCE = new DatabaseDataSource();

    @Test
    public void canConnectToDatabase() {
        Map<String,String> queryParameters = new HashMap<>();

        final String result = DATABASE_DATA_SOURCE.getData(queryParameters);

        MatcherAssert.assertThat(result, Matchers.containsString("Ankara"));
        MatcherAssert.assertThat(result, Matchers.containsString("Istanbul"));
    }

    @Ignore
    @Test
    public void canFetchResultForAllCitiesForADate() {

        Map<String,String> queryParameters = new HashMap<>();

        final String result = DATABASE_DATA_SOURCE.getData(queryParameters);

        MatcherAssert.assertThat(result, Matchers.containsString("Ankara"));
        MatcherAssert.assertThat(result, Matchers.containsString("Istanbul"));
    }

    @Ignore
    @Test
    public void canFetchResultForACityForAllDates() {
        Map<String,String> queryParameters = new HashMap<>();

        final String result = DATABASE_DATA_SOURCE.getData(queryParameters);

        MatcherAssert.assertThat(result, Matchers.containsString("Ankara"));
        MatcherAssert.assertThat(result, Matchers.containsString("Istanbul"));
    }

    @Ignore
    @Test
    public void canFetchResultForACityAndADate() {
        Map<String,String> queryParameters = new HashMap<>();

        final String result = DATABASE_DATA_SOURCE.getData(queryParameters);

        MatcherAssert.assertThat(result, Matchers.containsString("Ankara"));
        MatcherAssert.assertThat(result, Matchers.containsString("Istanbul"));
    }

    @Test
    public void canConstructQueryWithoutParameters() {
        Map<String,String> queryParameters = new HashMap<>();

        String result = DATABASE_DATA_SOURCE.constructQuery(queryParameters);

        MatcherAssert.assertThat(result, Matchers.is("SELECT * FROM weather"));
    }

    @Test
    public void canConstructQueryWithCityOnly() {
        Map<String,String> queryParameters = new HashMap<>();
        queryParameters.put("city", "Ankara");

        String result = DATABASE_DATA_SOURCE.constructQuery(queryParameters);

        MatcherAssert.assertThat(result, Matchers.is("SELECT * FROM weather WHERE city = 'Ankara'"));
    }

    @Test
    public void canConstructQueryWithDateOnly() {
        Map<String,String> queryParameters = new HashMap<>();
        queryParameters.put("wdate", "2021-03-28");

        String result = DATABASE_DATA_SOURCE.constructQuery(queryParameters);

        MatcherAssert.assertThat(result, Matchers.is("SELECT * FROM weather WHERE wdate = '2021-03-28'"));
    }

    @Test
    public void canConstructQueryWithCityAndDate() {
        Map<String,String> queryParameters = new HashMap<>();
        queryParameters.put("wdate", "2021-03-28");
        queryParameters.put("city", "Ankara");

        String result = DATABASE_DATA_SOURCE.constructQuery(queryParameters);

        MatcherAssert.assertThat(result, Matchers.is("SELECT * FROM weather WHERE city = 'Ankara' and wdate = '2021-03-28'"));
    }
}