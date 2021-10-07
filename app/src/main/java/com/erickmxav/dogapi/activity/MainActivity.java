package com.erickmxav.dogapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.erickmxav.dogapi.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRecuperar = findViewById(R.id.buttonRecuperar);
    }

    public void openBreeds(View view){
        startActivity(new Intent(this, BreedsList.class));
    }
}