package com.odinn.anotherversion.adapter;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.odinn.anotherversion.Constants;
import com.odinn.anotherversion.FullScreenActivity;
import com.odinn.anotherversion.OnSightMovedListener;
import com.odinn.anotherversion.R;
import com.odinn.anotherversion.fragments.ExistSightsFragment;
import com.odinn.anotherversion.models.Sights;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SightListAdapter extends RecyclerView.Adapter<SightListAdapter.SightListHolder> {

    private List<Sights> sightsList;

    private OnSightMovedListener listener;

    public SightListAdapter(List<Sights> sightsList) {
        this.sightsList = sightsList;
    }

    public void setListener(OnSightMovedListener listener) {
        this.listener = listener;
    }

    public void add(Sights sights) {
        sightsList.add(sights);
        notifyItemInserted(sightsList.size() - 1);
    }

    public void remove(Sights sights) {
        int pos = sightsList.indexOf(sights);
        sightsList.remove(sights);
        notifyItemRemoved(pos);
    }

    private void move(Sights sights){
        if (listener != null){
            remove(sights);
            listener.onSightMoved(sights);
        }
    }

    @Override
    public SightListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sight_item, parent, false);
        return new SightListHolder(view);
    }

    @Override
    public void onBindViewHolder(final SightListHolder holder, final int position) {
        final double lat;
        final double lng;


        final Sights item = sightsList.get(position);

        holder.title.setText(item.getTitle());
        Picasso.with(holder.title.getContext()).load(item.getImg()).into(holder.ivSightPhoto);
//        holder.ivSightPhoto.setImageResource(item.getImg());
        lat = item.getLat();
        lng = item.getLng();


        holder.ivSightPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FullScreenActivity.class);
                intent.putExtra("id", item.getImg());
                v.getContext().startActivity(intent);

            }
        });

        if (listener != null && listener instanceof ExistSightsFragment){
            holder.btnCheck.setVisibility(View.GONE);
            return;
        }

        holder.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());

                double curLatitude = Double.parseDouble(sharedPreferences.getString(Constants.PREF_LATITUDE, "0"));
                double curLongitude = Double.parseDouble(sharedPreferences.getString(Constants.PREF_LONGITUDE, "0"));


                //  Toast.makeText(view.getContext(),""+ curLatitude + " "+ lat, Toast.LENGTH_SHORT).show();
                if (curLatitude == 0 && curLongitude == 0) {
                    Toast.makeText(view.getContext(), "-", Toast.LENGTH_SHORT).show();
                } else {
                    if (curLatitude <= lat + 0.0002 &&
                            curLongitude <= lng + 0.0003 &&
                            curLatitude >= lat - 0.0002 &&
                            curLongitude >= lng - 0.0003) {

                        Toast.makeText(view.getContext(), " DONE", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(view.getContext(), " NO", Toast.LENGTH_SHORT).show();
                    }

                }

                move(item);

            } // чек геодаты если true то убрать карточку в 2 таб

        });


    }

    @Override
    public int getItemCount() {
        return sightsList.size();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        listener = null;
        super.onDetachedFromRecyclerView(recyclerView);
    }

    protected static class SightListHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView ivSightPhoto;
        Button btnCheck;


        public SightListHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            ivSightPhoto = (ImageView) itemView.findViewById(R.id.ivSightPhoto);
            btnCheck = (Button) itemView.findViewById(R.id.btnCheck);


        }
    }
}
