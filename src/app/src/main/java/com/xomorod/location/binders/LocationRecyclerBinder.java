package com.xomorod.location.binders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xomorod.location.R;
import com.xomorod.location.business.BinderViewType;
import com.xomorod.location.db.Location;

import jp.satorufujiwara.binder.recycler.RecyclerBinder;

/**
 * Created by 890683 on 5/25/2016.
 */
public class LocationRecyclerBinder extends RecyclerBinder<BinderViewType> {

    private final Location  location;

    public LocationRecyclerBinder(Activity activity, Location  location) {
        super(activity, BinderViewType.BASE_TYPE);
        this.location = location;
    }

    @Override
    public int layoutResId() {
        return R.layout.item_location;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.txtAccuracy.setText(location.getAccuracy());
        holder.txtLat.setText(location.getLatitude());
        holder.txtLong.setText(location.getLongitude());
        holder.txtLocationName.setText(location.getLocationName());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       TextView txtLat;
       TextView txtLong;
       TextView txtAccuracy;
       TextView txtLocationName;
        public ViewHolder(View view) {
            super(view);
            txtLat = (TextView) view.findViewById(R.id.txtLat);
            txtLong = (TextView) view.findViewById(R.id.txtLong);
            txtAccuracy = (TextView) view.findViewById(R.id.txtAccuracy);
            txtLocationName = (TextView) view.findViewById(R.id.txtLocationName);
         }
    }
}


