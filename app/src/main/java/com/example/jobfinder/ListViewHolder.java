package com.example.jobfinder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import Interface.ItemClickListener;

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtTitle, txtText;
    public ImageView imageView;
    public ItemClickListener listener;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);


//        imageView = (ImageView) itemView.findViewById(R.id.post_image);
//        txtTitle = (TextView) itemView.findViewById(R.id.post_name);
//        txtText = (TextView) itemView.findViewById(R.id.post_text);
    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;

    }

    @Override
    public void onClick(View view)
    {
        listener.onClick(view, getAdapterPosition(), false);

    }
}
