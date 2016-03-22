package com.odinn.anotherversion.adapter;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.odinn.anotherversion.Constants;
import com.odinn.anotherversion.R;
import com.odinn.anotherversion.models.Sights;

import java.util.List;

public class SightListAdapter extends RecyclerView.Adapter<SightListAdapter.SightListHolder> {

    private List<Sights> sightsList;

    public SightListAdapter(List<Sights> sightsList) {
        this.sightsList = sightsList;
    }

    @Override
    public SightListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sight_item, parent, false);
        return new SightListHolder(view);
    }

    public void load() {

    }

    @Override
    public void onBindViewHolder(final SightListHolder holder, final int position) {
        Sights item = sightsList.get(position);
        holder.title.setText(item.getTitle());
        holder.img.setImageResource(item.getImg());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                String latitude = sharedPreferences.getString(Constants.PREF_LATITUDE, null);
                if (TextUtils.isEmpty(latitude)) {
                    Toast.makeText(view.getContext(), "-", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), latitude, Toast.LENGTH_SHORT).show();
                }
            } // чек геодаты если true то убрать карточку в 2 таб
        });


    }

    @Override
    public int getItemCount() {
        return sightsList.size();
    }

    public static class SightListHolder extends RecyclerView.ViewHolder {
//        CardView cardView;
        TextView title;
        ImageView img;
        Button button;

        public SightListHolder(View itemView) {
            super(itemView);
//            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
            img = (ImageView) itemView.findViewById(R.id.ivSightPhoto);
            button = (Button) itemView.findViewById(R.id.btnCheck);

        }
    }
}
