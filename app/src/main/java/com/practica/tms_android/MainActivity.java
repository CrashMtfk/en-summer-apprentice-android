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
import com.practica.tms_android.adapters.EventCardAdapter;
import com.practica.tms_android.adapters.OrderCardAdapter;
import com.practica.tms_android.models.EventDTO;
import com.practica.tms_android.models.OrderDTO;
import com.practica.tms_android.retrofit.EventApi;
import com.practica.tms_android.retrofit.OrderApi;
import com.practica.tms_android.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private int idLoggedCustomer = 3;
    public static final String NEXT_SCREEN = "PLACE_ORDER_KEY";
    private RecyclerView recyclerView;
    private RecyclerView ordersRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button eventsButton = findViewById(R.id.showEventsButton);
        Button ordersButton = findViewById(R.id.showOrdersButton);

        recyclerView = findViewById(R.id.eventCardRecycleView);
        ordersRecyclerView = findViewById(R.id.orderCardRecycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setUpEventCardModels();
        setUpOrderCardModels();

        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                ordersRecyclerView.setVisibility(View.GONE);
            }
        });

        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.GONE);
                ordersRecyclerView.setVisibility(View.VISIBLE);
            }
        });

    }

    private void setUpEventCardModels(){
        RetrofitService retrofitService = new RetrofitService();
        EventApi eventApi = retrofitService.getRetrofit().create(EventApi.class);
        eventApi.getAllEvents()
                .enqueue(new Callback<List<EventDTO>>() {
                    @Override
                    public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
                        populateEvents(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<EventDTO>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed to load events", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void setUpOrderCardModels(){
        RetrofitService retrofitService = new RetrofitService();
        OrderApi orderApi = retrofitService.getRetrofit().create(OrderApi.class);
        orderApi.getAllOrders(idLoggedCustomer)
                .enqueue(new Callback<List<OrderDTO>>() {
                    @Override
                    public void onResponse(Call<List<OrderDTO>> call, Response<List<OrderDTO>> response) {
                        populateOrders(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<OrderDTO>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
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

    private void populateOrders(List<OrderDTO> orderDTOList){
        OrderCardAdapter orderCardAdapter = new OrderCardAdapter(this,orderDTOList);
        ordersRecyclerView.setAdapter(orderCardAdapter);
    }
}
