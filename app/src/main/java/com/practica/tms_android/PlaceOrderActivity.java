package com.practica.tms_android;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.practica.tms_android.models.EventDTO;

public class PlaceOrderActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_tickets_view);
        Intent intent = getIntent();
        EventDTO currentEvent = null;
        TextView eventOrderTitle = findViewById(R.id.placeOrderTitle);
        if(intent.hasExtra(MainActivity.NEXT_SCREEN)){
            currentEvent = (EventDTO) intent.getSerializableExtra(MainActivity.NEXT_SCREEN);
            eventOrderTitle.setText("ORDER " + currentEvent.getEventName());
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
