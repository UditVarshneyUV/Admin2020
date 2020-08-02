package com.droid.solver.admin2020.addplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.droid.solver.admin2020.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class AddPlacesAdapter extends RecyclerView.Adapter {

    private List<AddPlacesModel> list;
    private Context context;
    private LayoutInflater inflater;
    public AddPlacesAdapter(Context context,List<AddPlacesModel> list){
        this.list=list;
        this.context=context;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.add_places_item,parent,false);
        return new AddPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof AddPlacesViewHolder){
            AddPlacesModel model=list.get(position);

            Picasso.get().load(model.getImageUrl()).placeholder(R.drawable.image_gradient).
                    into(((AddPlacesViewHolder) holder).imageView);

            String title=model.getCityName();
            title=title.substring(0,1).toUpperCase()+title.substring(1);
            ((AddPlacesViewHolder) holder).cityTitle.setText(title);
            ((AddPlacesViewHolder) holder).cityDescription.setText(model.description);
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
}
