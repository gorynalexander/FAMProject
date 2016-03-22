package com.odinn.anotherversion.adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.odinn.anotherversion.MainActivity;
import com.odinn.anotherversion.R;
import com.odinn.anotherversion.dto.SightsDTO;

import java.util.List;

public class SightListAdapter extends RecyclerView.Adapter<SightListAdapter.SightListHolder> {

    private List<SightsDTO> data;
    private Context context;


    public SightListAdapter(List<SightsDTO> data) {
        this.data = data;
    }
    public SightListAdapter(Context context){
        this.context = context;
    }


    @Override
    public SightListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sight_item, parent, false);

        return new SightListHolder(view);
    }

    public void load(){

    }

    @Override
    public void onBindViewHolder(final SightListHolder holder, final int position) {
        SightsDTO item = data.get(position);
        holder.title.setText(item.getTitle());
        holder.img.setImageResource(item.getImg());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                SharedPreferences sharedPreferences = context.getSharedPreferences("GPSData", Context.MODE_PRIVATE);
//               String a = sharedPreferences.getString("lat", "");
//                if (!a.equals("")) {
//                    Toast.makeText(v.getContext(), a, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(v.getContext(), "-", Toast.LENGTH_SHORT).show();
//                }

            } // чек геодаты если true то убрать карточку в 2 таб


        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SightListHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;
        ImageView img;
        Button button;
        public SightListHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
            img = (ImageView) itemView.findViewById(R.id.imageView);
            button = (Button) itemView.findViewById(R.id.check_btn);

        }
    }
}
