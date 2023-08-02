package com.practica.tms_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<EventCardModel> eventCardModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.eventCardRecycleView);
        setUpEventCardModels();
        EventCardAdapter adapter = new EventCardAdapter(this,eventCardModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpEventCardModels(){
        for(int i = 0; i < 10; i++){
            eventCardModels.add(new EventCardModel(Integer.toString(i), "Description", "Period", "Location"));
        }
    }
}
