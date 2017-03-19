package com.odinn.anotherversion.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.odinn.anotherversion.helper.Constants;
import com.odinn.anotherversion.FullScreenActivity;
import com.odinn.anotherversion.helper.OnSightMovedListener;
import com.odinn.anotherversion.R;
import com.odinn.anotherversion.fragments.ExistSightsFragment;
import com.odinn.anotherversion.models.Sight;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.util.List;

public class SightListAdapter extends RecyclerView.Adapter<SightListAdapter.SightListHolder> {

    private List<Sight> sightList;

    private OnSightMovedListener listener;

    private double latitudeTest;

    public void setLatitudeTest(double latitudeTest) {
        this.latitudeTest = latitudeTest;
    }

    public SightListAdapter(List<Sight> sightList) {
        this.sightList = sightList;
    }

    public void setListener(OnSightMovedListener listener) {
        this.listener = listener;
    }

    public void add(Sight sight) {
        sightList.add(sight);
        notifyItemInserted(sightList.size() - 1);
    }

    public void remove(Sight sight) {
        int pos = sightList.indexOf(sight);
        sightList.remove(sight);
        notifyItemRemoved(pos);
    }

    private void move(Sight sight){
        if (listener != null){
            remove(sight);
            listener.onSightMoved(sight);
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
        final Sight item = sightList.get(position);

        holder.title.setText(item.getTitle());
        Picasso.with(holder.title.getContext()).load(item.getImg()).into(holder.ivSightPhoto);
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

                       // Toast.makeText(view.getContext(), " DONE", Toast.LENGTH_SHORT).show();


                    } else {
                       // Toast.makeText(view.getContext(), " NO", Toast.LENGTH_SHORT).show();
                    //    makeAlert("SORRY", "You haven't found the "+item.getTitle()+ ". \n Try more!", view.getContext());
                        makeAlert("Неудача", "Ваши текущие координаты не совпадают с координатами "+item.getTitle()+ ".  Попытайтесь еще раз, или проверьте подключение к GPS", view.getContext());
                    }
                    makeAlert("Удача!!!", "У Вас получилось найти " + item.getTitle() + ". Ваши результаты будут внесены в Достижения", view.getContext());
                    remove(item);
                }



            } // чек геодаты если true то убрать карточку в 2 таб

        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TweetComposer.Builder builder = new TweetComposer.Builder(v.getContext())
                        .text("I want to find the " + item.getTitle()+ " in Find&Mark application");

                builder.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return sightList.size();
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
        Button btnShare;


        public SightListHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            ivSightPhoto = (ImageView) itemView.findViewById(R.id.ivSightPhoto);
            btnCheck = (Button) itemView.findViewById(R.id.btnCheck);
            btnShare = (Button) itemView.findViewById(R.id.btnShare);

        }
    }

    private void makeAlert(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }

                        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
