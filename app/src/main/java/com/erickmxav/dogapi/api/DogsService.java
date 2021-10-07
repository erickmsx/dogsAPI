package com.erickmxav.dogapi.api;

import com.erickmxav.dogapi.model.Dog;
import com.erickmxav.dogapi.model.RandomImageDog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogsService {

        @GET("/api/breeds/list/all")
        Call<Dog> recoverDogsList();

        @GET("/api/breed/{selecDog}/images/random")
        Call<RandomImageDog> recoverImage(@Path("selecDog") String selecDog);
}