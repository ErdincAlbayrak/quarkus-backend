package com.erdincalbayrak;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Map;

@ApplicationScoped
public class DatabaseDataSource implements IDataSource{
    @ConfigProperty(name = "database.username")
    public String USERNAME;

    @ConfigProperty(name = "database.password")
    public String PASSWORD;

    @ConfigProperty(name = "database.name")
    public String DATABASE_NAME;

    @ConfigProperty(name = "database.ip")
    public String DATABASE_IP;

    @ConfigProperty(name = "database.port")
    public String DATABASE_PORT;

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final String SELECT_QUERY = "SELECT * FROM weather";

    @Override
    public String getData(Map<String, String> queryParameters) {
        final String query = constructQuery(queryParameters);

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Connection failure, details: ", e);
        }

        try (final Connection connection = createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return constructJSONStringFromResultSet(resultSet);
        }  catch (SQLException e) {
            throw new RuntimeException("SQL failure, details: ", e);
        }
    }

    public String constructQuery(Map<String, String> queryParameters) {
        String query = SELECT_QUERY;
        if(!queryParameters.isEmpty()) {
            query += " WHERE ";
            boolean firstParam = true;
            for (Map.Entry<String,String> param:queryParameters.entrySet()) {
                if (!firstParam ) {
                    query += " and ";
                }
                query += param.getKey() + " = '" + param.getValue() + "'";
                firstParam = false;
            }
        }
        return query;
    }

    private Connection createConnection() throws SQLException {
        final String connectionURL = "jdbc:postgresql://" + DATABASE_IP + ":" + DATABASE_PORT + "/" + DATABASE_NAME;
        return DriverManager.getConnection(connectionURL, USERNAME, PASSWORD);
    }

    private String constructJSONStringFromResultSet(ResultSet resultSet) {
        final JsonArray jsonArray = new JsonArray();
        try {
            while(resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("city",resultSet.getString("city") );
                jsonObject.put("wdate", SIMPLE_DATE_FORMAT.format(resultSet.getDate("wdate")));
                jsonObject.put("forecast", resultSet.getString("forecast"));
                jsonObject.put("high", resultSet.getInt("high"));
                jsonObject.put("low",resultSet.getInt("low"));

                jsonArray.add(jsonObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jsonArray.toString();
    }
}
