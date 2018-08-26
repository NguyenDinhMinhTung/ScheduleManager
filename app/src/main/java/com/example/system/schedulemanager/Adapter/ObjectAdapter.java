package com.example.system.schedulemanager.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.system.schedulemanager.DTO.ObjectDTO;
import com.example.system.schedulemanager.R;
import com.example.system.schedulemanager.Tools;

import java.util.Date;
import java.util.List;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectViewHolder> {
    private Context context;
    private List<List<ObjectDTO>> list;
    private LayoutInflater layoutInflater;

    public ObjectAdapter(Context context, List<List<ObjectDTO>> list) {
        this.context = context;
        this.list = list;

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_object_item, parent, false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectViewHolder holder, int position) {
        List<ObjectDTO> objectDTOs = list.get(position);
        TextView[] txtObject = {holder.txtObject1, holder.txtObject2, holder.txtObject3, holder.txtObject4, holder.txtObject5};

        holder.txtDay.setText(getDayOfWeekFromPos(position));

        for (ObjectDTO e : objectDTOs) {
            txtObject[e.getJigen() - 1].setText(e.getObjectName());
        }
    }

    private String getDayOfWeekFromPos(int pos) {
        Resources resources = context.getResources();

        switch (pos) {
            case 0:
                return resources.getString(R.string.monday);

            case 1:
                return resources.getString(R.string.tuesday);

            case 2:
                return resources.getString(R.string.wednesday);

            case 3:
                return resources.getString(R.string.thursday);

            case 4:
                return resources.getString(R.string.friday);

            default:
                return "";
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class ObjectViewHolder extends RecyclerView.ViewHolder {
    TextView txtDay, txtObject1, txtObject2, txtObject3, txtObject4, txtObject5;

    public ObjectViewHolder(View itemView) {
        super(itemView);
        txtDay = itemView.findViewById(R.id.txtDay);
        txtObject1 = itemView.findViewById(R.id.txtObject1);
        txtObject2 = itemView.findViewById(R.id.txtObject2);
        txtObject3 = itemView.findViewById(R.id.txtObject3);
        txtObject4 = itemView.findViewById(R.id.txtObject4);
        txtObject5 = itemView.findViewById(R.id.txtObject5);

    }
}
