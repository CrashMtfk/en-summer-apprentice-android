package com.practica.tms_android.retrofit;

import com.practica.tms_android.models.EventDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventApi {

    @GET("/events")
    Call<List<EventDTO>> getAllEvents();
}
