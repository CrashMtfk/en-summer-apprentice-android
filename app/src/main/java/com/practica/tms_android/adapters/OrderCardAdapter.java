package com.practica.tms_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practica.tms_android.R;
import com.practica.tms_android.models.OrderDTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderCardAdapter extends RecyclerView.Adapter<OrderCardAdapter.MyViewHolder> {
    Context context;
    List<OrderDTO> orderDTOList;
    private OnClickListener onClickListener;

    public OrderCardAdapter(Context context, List<OrderDTO> orderDTOList) {
        this.context = context;
        this.orderDTOList = orderDTOList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_card_view_row,parent,false);
        return new OrderCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderDTO order = orderDTOList.get(position);
        holder.numberOfTickets.setText("Number of tickets: " + Integer.toString(order.getNumberOfTickets()));
        holder.orderedTimeStamp.setText("Ordered at: " + formatDateString(order.getOrderedAt().toString()));
        holder.totalPrice.setText("Total: " + String.valueOf(order.getTotalPrice()) + " lei");
    }

    @Override
    public int getItemCount() {
        return orderDTOList == null ? 0 : orderDTOList.size();
    }

    public String formatDateString(String orderDate){
        String[] splitedDate = orderDate.split("T");
        return splitedDate[0];
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, OrderDTO model);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView numberOfTickets, orderedTimeStamp, totalPrice;
        Button editButton;
        ImageButton deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            numberOfTickets = itemView.findViewById(R.id.numberOfTickets);
            orderedTimeStamp = itemView.findViewById(R.id.orderTimeStamp);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            editButton = itemView.findViewById(R.id.editOrderButton);
            deleteButton = itemView.findViewById(R.id.deleteOrderButton);
        }
    }
}
