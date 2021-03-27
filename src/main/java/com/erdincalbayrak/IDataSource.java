package com.erdincalbayrak;

import java.util.Map;

public interface IDataSource {
    String getData(Map<String, String> queryParameters);
}
