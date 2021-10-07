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
import android.widget.Toast;

import com.erickmxav.dogapi.R;
import com.erickmxav.dogapi.adapter.AdapterDog;
import com.erickmxav.dogapi.api.DogsService;
import com.erickmxav.dogapi.helper.RecyclerItemClickListener;
import com.erickmxav.dogapi.model.Dog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BreedsList extends AppCompatActivity {

    private RecyclerView recyclerListBreeds;
    private AdapterDog adapter;
    private List<String> dogsList = new ArrayList<>();
    private Dog dog;
    private Retrofit retrofit;
    private String selectedDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds_list);

        recyclerListBreeds = findViewById(R.id.recyclerListBreeds);

        //Config adapter
        adapter = new AdapterDog(dogsList, this);

        //Config RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerListBreeds.setLayoutManager(layoutManager);
        recyclerListBreeds.setHasFixedSize(true);
        recyclerListBreeds.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerListBreeds.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recoverList();

        //Config event click on recyclerview
        recyclerListBreeds.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerListBreeds,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                String selectedDog = dogsList.get( position );
                                
                                Intent sender = new Intent(getApplicationContext(), BreedRandomImages.class);

                                Bundle bundle = new Bundle();

                                bundle.putString("selDog", selectedDog);

                                sender.putExtras(bundle);

                                startActivity(sender);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }

    private void recoverList() {

        DogsService dogsService = retrofit.create(DogsService.class);
        Call<Dog> call = dogsService.recoverDogsList();

        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {

                if (response.isSuccessful()) {

                    dogsList.addAll(response.body().getMessage().keySet());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {

                Toast.makeText(BreedsList.this,
                        "Não foi possível", Toast.LENGTH_SHORT).show();
            }
        });
    }
}