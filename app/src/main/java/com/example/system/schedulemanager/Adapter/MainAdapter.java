package com.example.system.schedulemanager.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.system.schedulemanager.DTO.EvenByDayDTO;
import com.example.system.schedulemanager.R;
import com.example.system.schedulemanager.Tools;

import java.util.Date;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<EvenByDayDTO> list;
    private LayoutInflater layoutInflater;

    public MainAdapter(Context context, List<EvenByDayDTO> list) {
        this.context = context;
        this.list = list;

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_custom_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EvenByDayDTO evenByDayDTO = list.get(position);
        Date start = evenByDayDTO.getDate();

        int date = start.getDate();
        holder.txtDay.setText(String.valueOf(date < 10 ? "0" + date : date));

        String month = start.getMonth() < 10 ? "0" + (start.getMonth()) : "" + (start.getMonth());
        holder.txtYearMonth.setText((start.getYear()) + "." + month);

        EvenAdapter evenAdapter = new EvenAdapter(context, evenByDayDTO.getList());
        holder.lstEven.setAdapter(evenAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.lstEven.setLayoutManager(linearLayoutManager);

        holder.txtDayOfWeek.setText(Tools.getStringDayOfWeek(context, start));
        holder.txtDayOfWeek.setBackgroundResource(Tools.getBackground(start));
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
