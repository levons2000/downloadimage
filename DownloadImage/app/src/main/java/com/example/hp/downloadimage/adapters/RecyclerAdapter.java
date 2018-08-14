package com.example.hp.downloadimage.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.downloadimage.R;
import com.example.hp.downloadimage.activities.MainActivity;
import com.example.hp.downloadimage.models.RecyclerModel;
import com.example.hp.downloadimage.utils.DataProvider;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private List<RecyclerModel> list = DataProvider.list;
    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getImgUrl()).into(holder.imageView);
        holder.textView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataProvider.position = getAdapterPosition();
                    ((MainActivity) context).textView.setText(list.get(getAdapterPosition()).getImgUrl());
                }
            });
        }
    }
}
