package com.practica.tms_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<EventCardModel> eventCardModels = new ArrayList<>();
    public static final String NEXT_SCREEN = "PLACE_ORDER_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.eventCardRecycleView);
        setUpEventCardModels();
        EventCardAdapter adapter = new EventCardAdapter(this,eventCardModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnClickListener(new EventCardAdapter.OnClickListener() {
            @Override
            public void onClick(int position, EventCardModel model) {
                Intent intent = new Intent(MainActivity.this, PlaceOrderActivity.class);
                intent.putExtra(NEXT_SCREEN,model);
                startActivity(intent);
            }
        });
    }

    private void setUpEventCardModels(){
        for(int i = 0; i < 10; i++){
            eventCardModels.add(new EventCardModel(Integer.toString(i), "Description", "Period", "Location"));
        }
    }
}
