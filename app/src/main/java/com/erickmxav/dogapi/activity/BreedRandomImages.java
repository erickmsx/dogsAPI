package com.erickmxav.dogapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.erickmxav.dogapi.R;
import com.erickmxav.dogapi.api.DogsService;
import com.erickmxav.dogapi.model.Dog;
import com.erickmxav.dogapi.model.RandomImageDog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BreedRandomImages extends AppCompatActivity {

    Retrofit retrofit;
    private Button changeImage;
    private String selecDog;
    private RandomImageDog imageRandom;
    private ImageView dogImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_random_images);

        changeImage = findViewById(R.id.buttonRandomImage);
        dogImage = findViewById(R.id.dogImage);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Intent receiver = getIntent();
        Bundle bundle = receiver.getExtras();
        selecDog = bundle.getString("selDog");

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoverRandomImage();
            }
        });
    }

    public void recoverRandomImage() {

        DogsService dogsService = retrofit.create(DogsService.class);
        Call<RandomImageDog> call = dogsService.recoverImage(selecDog);

        call.enqueue(new Callback<RandomImageDog>() {
            @Override
            public void onResponse(Call<RandomImageDog> call, Response<RandomImageDog> response) {
                if( response.isSuccessful() ){

                    RandomImageDog randomImageDog = response.body();
                    Uri uri = Uri.parse(randomImageDog.getMessage());
                    Glide.with(getApplicationContext()).load( uri ).into(dogImage);
                }
            }

            @Override
            public void onFailure(Call<RandomImageDog> call, Throwable t) {

                Toast.makeText(BreedRandomImages.this,
                        "Não foi possível", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        recoverRandomImage();
    }
}