package com.practica.tms_android.retrofit;

import com.practica.tms_android.models.OrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderApi {
    @GET("/orders/{customerID}")
    Call<List<OrderDTO>> getAllOrders(@Path("customerID")int customerID);
}
