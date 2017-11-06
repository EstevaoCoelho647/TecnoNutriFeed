package com.coelho.estevao.tecnonutrifeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by estevao on 06/11/17.
 */

public interface APIInterface {
    @GET("/feed")
    Call<List<FeedItem>> findFeedItems();

}
