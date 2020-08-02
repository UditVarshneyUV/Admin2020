package com.droid.solver.admin2020.addplaces;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.droid.solver.admin2020.R;

public class AddPlacesViewHolder extends RecyclerView.ViewHolder {
    public TextView cityTitle,cityDescription;
    public ImageView imageView;

    public AddPlacesViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.city_image);
        cityDescription=itemView.findViewById(R.id.description);
        cityTitle=itemView.findViewById(R.id.city_title);
    }
}

