package com.practica.tms_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practica.tms_android.models.EventDTO;

import java.util.ArrayList;
import java.util.List;

public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.MyViewHolder> {

    Context context;
    List<EventDTO> eventCardModels;
    private OnClickListener onClickListener;

    public EventCardAdapter(Context context, List<EventDTO> eventCardModels){
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
        EventDTO event = eventCardModels.get(position);
        holder.title.setText(event.getEventName());
        holder.description.setText(event.getEventDescription());
        holder.period.setText(formatDateString(event.getStartDate().toString(), event.getEndDate().toString()));
        Log.d("EventCartAdapter", event.getVenue() + "object");
        holder.location.setText(event.getVenue().getVenueLocation());
        holder.buyTicketsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (onClickListener != null){
                    onClickListener.onClick(position, event);
                }
            }
        });
    }

    public String formatDateString(String startDate, String endDate){
        String[] splitedStartDate = startDate.split("T");
        String[] splitedEndDate = endDate.split("T");
        String displayedText = "Period: " + splitedStartDate[0] + "-" + splitedEndDate[0];
        return displayedText;
    }
    @Override
    public int getItemCount() {
        return eventCardModels == null ? 0 : eventCardModels.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, EventDTO model);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, period, location;
        Button buyTicketsButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.eventCardTitle);
            description = itemView.findViewById(R.id.eventCardDescription);
            period = itemView.findViewById(R.id.eventCardPeriod);
            location = itemView.findViewById(R.id.eventCardLocation);
            buyTicketsButton = itemView.findViewById(R.id.eventCardBuyButton);
        }
    }
}
