package com.example.system.schedulemanager.Adapter;

import android.app.Activity;
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

import com.example.system.schedulemanager.AddObjectActivity;
import com.example.system.schedulemanager.DAO.TimeTableDAO;
import com.example.system.schedulemanager.DTO.ObjectDTO;
import com.example.system.schedulemanager.ObjectDetail;
import com.example.system.schedulemanager.R;
import com.example.system.schedulemanager.Tools;

import java.util.Date;
import java.util.List;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectViewHolder> {
    private Context context;
    private List<List<ObjectDTO>> list;
    private LayoutInflater layoutInflater;
    private int timetableid;

    public ObjectAdapter(Context context, List<List<ObjectDTO>> list, int timetableid) {
        this.context = context;
        this.list = list;
        this.timetableid = timetableid;

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_object_item, parent, false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectViewHolder holder, final int position) {
        TextView[] txtObject = {holder.txtObject1, holder.txtObject2, holder.txtObject3, holder.txtObject4, holder.txtObject5};
        LinearLayout[] layouts = {holder.layoutjigen1, holder.layoutjigen2, holder.layoutjigen3, holder.layoutjigen4, holder.layoutjigen5};

        if (position==5){
            for(LinearLayout layout:layouts){
                layout.setVisibility(View.GONE);
            }

            holder.txtDay.setText("DELETE");
            holder.txtDay.setBackgroundColor(context.getResources().getColor(R.color.red));

            holder.txtDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimeTableDAO timeTableDAO=new TimeTableDAO(context);

                    timeTableDAO.delete(timetableid);
                    ((Activity) context).finish();
                }
            });

            return;
        }

        final List<ObjectDTO> objectDTOs = list.get(position);

        holder.txtDay.setText(getDayOfWeekFromPos(position));

        for (ObjectDTO e : objectDTOs) {
            txtObject[e.getJigen() - 1].setText(e.getObjectName());
        }

        for (int i = 0; i < 5; i++) {
            final int jigen = i + 1;
            layouts[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;

                    ObjectDTO objectDTO = getObjectDTO(objectDTOs, jigen);

                    if (objectDTO != null) {
                        intent=new Intent(context, ObjectDetail.class);
                        intent.putExtra("objectdto", objectDTO);
                    } else {
                        intent= new Intent(context, AddObjectActivity.class);
                        intent.putExtra("timetableid", timetableid);
                        intent.putExtra("dayofweek", position + 1);
                        intent.putExtra("jigen", jigen);
                    }

                    context.startActivity(intent);
                }
            });
        }
    }

    ObjectDTO getObjectDTO(List<ObjectDTO> list, int jigen) {
        for (ObjectDTO objectDTO : list) {
            if (objectDTO.getJigen() == jigen) {
                return objectDTO;
            }
        }

        return null;
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
        return list.size();
    }
}

class ObjectViewHolder extends RecyclerView.ViewHolder {
    TextView txtDay, txtObject1, txtObject2, txtObject3, txtObject4, txtObject5;
    LinearLayout layoutjigen1, layoutjigen2, layoutjigen3, layoutjigen4, layoutjigen5;

    public ObjectViewHolder(View itemView) {
        super(itemView);
        txtDay = itemView.findViewById(R.id.txtDay);
        txtObject1 = itemView.findViewById(R.id.txtObject1);
        txtObject2 = itemView.findViewById(R.id.txtObject2);
        txtObject3 = itemView.findViewById(R.id.txtObject3);
        txtObject4 = itemView.findViewById(R.id.txtObject4);
        txtObject5 = itemView.findViewById(R.id.txtObject5);

        layoutjigen1 = itemView.findViewById(R.id.layoutjigen1);
        layoutjigen2 = itemView.findViewById(R.id.layoutjigen2);
        layoutjigen3 = itemView.findViewById(R.id.layoutjigen3);
        layoutjigen4 = itemView.findViewById(R.id.layoutjigen4);
        layoutjigen5 = itemView.findViewById(R.id.layoutjigen5);
    }
}
