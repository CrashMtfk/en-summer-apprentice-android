package com.practica.tms_android;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.practica.tms_android.models.EventDTO;

public class PlaceOrderActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_tickets_view);

        TextView eventOrderTitle = findViewById(R.id.placeOrderTitle);

        // Retrieve the JSON string from the Intent
        Intent intent = getIntent();
        String eventJsonString = intent.getStringExtra(MainActivity.NEXT_SCREEN);

        // Convert JSON string back to EventDTO object using Gson
        Gson gson = new Gson();
        EventDTO currentEvent = gson.fromJson(eventJsonString, EventDTO.class);

        if (currentEvent != null) {
            eventOrderTitle.setText("ORDER " + currentEvent.getEventName());
        } else {
            // Handle the case when there is no valid EventDTO data
            eventOrderTitle.setText("Invalid Event Data");
        }

        Button goBackButton = findViewById(R.id.placeOrderCancelButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
