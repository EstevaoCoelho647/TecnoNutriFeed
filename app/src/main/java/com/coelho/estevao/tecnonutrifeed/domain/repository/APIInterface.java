package com.coelho.estevao.tecnonutrifeed.domain.repository;

import com.coelho.estevao.tecnonutrifeed.domain.entity.FeedItem;
import com.coelho.estevao.tecnonutrifeed.domain.entity.FeedItems;
import com.coelho.estevao.tecnonutrifeed.domain.entity.ProfileRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by estevao on 06/11/17.
 */

public interface APIInterface {

    @GET("feed")
    Call<FeedItems> findFeedItems(@Query("p") Integer p, @Query("t") Long t);

    @GET("feed/{hash}")
    Call<FeedItem> findFeedItem(@Path("hash") String hash);

    @GET("profile/{id}")
    Call<ProfileRequest> findProfileInformation(@Path("id") Long id, @Query("p") Integer p, @Query("t") Long t);

}
