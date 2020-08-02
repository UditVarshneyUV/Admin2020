package com.droid.solver.admin2020.suggestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.droid.solver.admin2020.R;

import java.util.List;

public class SuggestionAdapter extends RecyclerView.Adapter {

    private List<String> list;
    private LayoutInflater inflater;
    private Context context;

    public SuggestionAdapter(Context context, List<String> list){
        this.context=context;
        this.list=list;
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.suggestion_item,parent,false);
        return new SuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SuggestionViewHolder){
            ((SuggestionViewHolder) holder).textView.setText(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
}

class SuggestionViewHolder extends RecyclerView.ViewHolder{

    public TextView textView;
    public SuggestionViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.text_view);
    }
}
