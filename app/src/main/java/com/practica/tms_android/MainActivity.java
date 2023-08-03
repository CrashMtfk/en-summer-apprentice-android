package com.practica.tms_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.practica.tms_android.models.EventDTO;
import com.practica.tms_android.retrofit.EventApi;
import com.practica.tms_android.retrofit.RetrofitService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<EventDTO> eventCardModels = new ArrayList<>();
    public static final String NEXT_SCREEN = "PLACE_ORDER_KEY";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.eventCardRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setUpEventCardModels();

    }

    private void setUpEventCardModels(){
        RetrofitService retrofitService = new RetrofitService();
        EventApi eventApi = retrofitService.getRetrofit().create(EventApi.class);
        eventApi.getAllEvents()
                .enqueue(new Callback<List<EventDTO>>() {
                    @Override
                    public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
                        List<EventDTO> events = response.body();
                        Log.d("MainActivity", "Received " + events.size());
                        populateEvents(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<EventDTO>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed to load events", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", t.toString());
                    }
                });

    }

    private void populateEvents(List<EventDTO> eventsList) {
        EventCardAdapter eventCardAdapter = new EventCardAdapter(this,eventsList);
        recyclerView.setAdapter(eventCardAdapter);

        eventCardAdapter.setOnClickListener(new EventCardAdapter.OnClickListener() {
            @Override
            public void onClick(int position, EventDTO model) {
                Gson gson = new Gson();
                String eventJsonString = gson.toJson(model);

                Intent intent = new Intent(MainActivity.this, PlaceOrderActivity.class);
                intent.putExtra(NEXT_SCREEN, eventJsonString);
                startActivity(intent);
            }
        });
    }
}
