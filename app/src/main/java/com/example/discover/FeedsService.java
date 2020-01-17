package com.example.discover;

import com.example.FeedsMain;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FeedsService {
    @GET("top-headlines?")
    Call<FeedsMain> getFeeds(@Query("q")String q,
                             @Query("apiKey")String apiKey);
}
