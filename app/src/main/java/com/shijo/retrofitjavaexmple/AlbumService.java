package com.shijo.retrofitjavaexmple;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumService {
    @GET("photos")
    Call<List<Photo>> getPhotos();
}
