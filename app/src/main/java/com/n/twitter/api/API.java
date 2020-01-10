package com.n.twitter.api;

import java.util.List;

import model.Check;
import model.ImageModel;
import model.SignUpResponse;
import model.TweetResponse;
import model.User;
import model.UserInfoList;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {
    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("email") String username, @Field("password") String password);


    @POST("users/signup")
    Call<SignUpResponse> register(@Body User cud);

    @Multipart
    @POST("upload")
    Call<ImageModel> uploadImage(@Part MultipartBody.Part imageFile);

    @POST("users/check")
    Call<Check> check(@Body User email);

    @GET("tweet/tweetList")
    Call<List<TweetResponse>> GetTweet(@Header("Authorization") String token);

    @GET("users/me")
    Call<UserInfoList> getUser(@Header("Authorization") String token);
}
