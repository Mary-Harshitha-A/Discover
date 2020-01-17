package com.example.discover;

import com.example.SourceMain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SourceService {
    @GET("sources?")
    Call<SourceMain> getSource(@Query("language")String language,
                               @Query("apiKey")String apikey);
}
