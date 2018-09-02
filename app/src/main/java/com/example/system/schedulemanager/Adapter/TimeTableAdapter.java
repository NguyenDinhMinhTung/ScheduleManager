package com.example.system.schedulemanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.system.schedulemanager.DTO.ObjectDTO;
import com.example.system.schedulemanager.DTO.TimeTableDTO;
import com.example.system.schedulemanager.ObjectActivity;
import com.example.system.schedulemanager.R;
import com.example.system.schedulemanager.Tools;

import java.util.Date;
import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableViewHolder> {
    private Context context;
    private List<TimeTableDTO> list;
    private LayoutInflater layoutInflater;

    public TimeTableAdapter(Context context, List<TimeTableDTO> list) {
        this.context = context;
        this.list = list;

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.layout_time_table_item,parent,false);
        return new TimeTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableViewHolder holder, int position) {
        final TimeTableDTO timeTableDTO=list.get(position);
        holder.txtTitle.setText(timeTableDTO.getTitle());
        holder.txtDay.setText(timeTableDTO.getStart());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ObjectActivity.class);
                intent.putExtra("timetableid", timeTableDTO.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class TimeTableViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle, txtDay;
    LinearLayout layout;

    public TimeTableViewHolder(View itemView) {
        super(itemView);
        txtTitle=itemView.findViewById(R.id.txtTitleTimeTableItem);
        txtDay=itemView.findViewById(R.id.txtDayTimeTableItem);

        layout=itemView.findViewById(R.id.layoutTimeTableItem);
    }
}
