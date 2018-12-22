package com.company.myapplication.api;

import com.company.myapplication.config.Define;

public class ApiUtils {

    private static final String BASE_URL = Define.API.BASE_URL;

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
