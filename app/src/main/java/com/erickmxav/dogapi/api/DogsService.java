package com.erickmxav.dogapi.api;

import com.erickmxav.dogapi.model.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogsService {

    @GET("/all")
    Call<List<Dog>> recoverDogsList();
}
