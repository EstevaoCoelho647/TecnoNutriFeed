package com.coelho.estevao.tecnonutrifeed.domain.repository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by estevao on 06/11/17.
 */

public class APIClient {
    private static final String API_BASE_URL = "http://api.tecnonutri.com.br/api/v4/";

    static class ServiceGenerator {

        private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        private static Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        private static Retrofit retrofit = builder.client(httpClient.addInterceptor(loggingInterceptor).build())
                .build();

        static <S> S createService(Class<S> serviceClass) {
            if (!httpClient.interceptors().contains(loggingInterceptor)) {

                //LOGGER INTERCEPTOR
                httpClient.addInterceptor(loggingInterceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();

            }
            return retrofit.create(serviceClass);
        }
    }
}
