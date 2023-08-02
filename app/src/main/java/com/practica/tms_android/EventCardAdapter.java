package com.practica.tms_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.MyViewHolder> {

    Context context;
    ArrayList<EventCardModel> eventCardModels;

    public EventCardAdapter(Context context, ArrayList<EventCardModel> eventCardModels){
            this.context = context;
            this.eventCardModels = eventCardModels;
    }

    @NonNull
    @Override
    public EventCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_card_view_row, parent, false);
        return new EventCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventCardAdapter.MyViewHolder holder, int position) {
        holder.title.setText(eventCardModels.get(position).getEventName());
        holder.description.setText(eventCardModels.get(position).getEventDescription());
        holder.period.setText(eventCardModels.get(position).getEventPeriod());
        holder.location.setText(eventCardModels.get(position).getEventLocation());
    }

    @Override
    public int getItemCount() {
        return eventCardModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, period, location;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.eventCardTitle);
            description = itemView.findViewById(R.id.eventCardDescription);
            period = itemView.findViewById(R.id.eventCardPeriod);
            location = itemView.findViewById(R.id.eventCardLocation);
        }
    }
}
