package com.example.system.schedulemanager.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.system.schedulemanager.DTO.EvenDTO;
import com.example.system.schedulemanager.R;

import java.util.Date;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<EvenDTO> list;
    private LayoutInflater layoutInflater;

    public MainAdapter(Context context, List<EvenDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_custom_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EvenDTO evenDTO = list.get(position);
        Date start=evenDTO.getStartTime();

        holder.txtDay.setText(start.getDay());
        holder.txtYearMonth.setText(start.getYear()+"."+start.getMonth());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView txtDay, txtYearMonth, txtDayOfWeek;
    public RecyclerView lstEven;

    public ViewHolder(View itemView) {
        super(itemView);
        txtDay = itemView.findViewById(R.id.txtDay);
        txtYearMonth = itemView.findViewById(R.id.txtYearMonth);
        txtDayOfWeek = itemView.findViewById(R.id.txtDayOfWeek);

        lstEven = itemView.findViewById(R.id.lstEven);
    }
}
