package com.example.system.schedulemanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.system.schedulemanager.AddEvenActivity;
import com.example.system.schedulemanager.DTO.EvenDTO;
import com.example.system.schedulemanager.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EvenAdapter extends RecyclerView.Adapter<EvenViewHolder> {
    Context context;
    List<EvenDTO> list;
    LayoutInflater inflater;

    public EvenAdapter(Context context, List<EvenDTO> list) {
        this.context = context;
        this.list = list;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EvenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_even_item, parent, false);
        return new EvenViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull EvenViewHolder holder, int position) {
        final EvenDTO evenDTO = list.get(position);
        Date start = evenDTO.getStartTime();
        Date end = evenDTO.getEndTime();
        String note = evenDTO.getNote();

        holder.txtEvenName.setText(evenDTO.getName());

        String timeFormat = "HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);

        holder.txtTime.setText(simpleDateFormat.format(start) + " - " + simpleDateFormat.format(end));
        if (note == null || note.equals("")) {
            holder.txtNote.setVisibility(View.GONE);
        } else {
            holder.txtNote.setText(note);
        }

        holder.evenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, AddEvenActivity.class);
                intent.putExtra("even",evenDTO);
                context.startActivity(intent);
            }
        });

        holder.colorBar.setBackgroundTintList(context.getResources().getColorStateList(evenDTO.getColor()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class EvenViewHolder extends RecyclerView.ViewHolder {
    public TextView txtEvenName, txtTime, txtNote;
    LinearLayout evenLayout, colorBar;

    public EvenViewHolder(View itemView) {
        super(itemView);
        txtEvenName = itemView.findViewById(R.id.txtEvenName);
        txtTime = itemView.findViewById(R.id.txtTime);
        txtNote = itemView.findViewById(R.id.txtNote);
        evenLayout=itemView.findViewById(R.id.evenLayout);
        colorBar=itemView.findViewById(R.id.colorBar);
    }
}
