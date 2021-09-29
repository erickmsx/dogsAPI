package com.erickmxav.dogapi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.erickmxav.dogapi.R;
import com.erickmxav.dogapi.adapter.AdapterDog;
import com.erickmxav.dogapi.api.DogsService;
import com.erickmxav.dogapi.helper.RecyclerItemClickListener;
import com.erickmxav.dogapi.model.Dog;
import com.google.gson.Gson;
import com.google.gson.internal.GsonBuildConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerListBreeds;
    private AdapterDog adapter;
    private List<Dog> dogsList = new ArrayList<>();
    private Dog dog;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerListBreeds = findViewById(R.id.recyclerListBreeds);

        //Configurar adapter
        adapter = new AdapterDog (dogsList, this);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerListBreeds.setLayoutManager(layoutManager);
        recyclerListBreeds.setHasFixedSize(true);
        recyclerListBreeds.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerListBreeds.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breeds/list/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void recoverDogsList(){

        DogsService dogsService = retrofit.create( DogsService.class );
        Call<List<Dog>> call = dogsService.recoverDogsList();

        call.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {

                if ( response.isSuccessful() ){

                    dogsList = response.body();

                    for (int i=0; i<dogsList.size(); i++ ) {
                        Dog dog = dogsList.get(i);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {

            }
        });
    }
}

/*//Config event click on recyclerview
        recyclerListBreeds.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerListBreeds,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Dog selectedDog = dogsList.get( position );
                                Intent i = new Intent(getApplicationContext(), BreedsImages.class);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }

                )
        );*/