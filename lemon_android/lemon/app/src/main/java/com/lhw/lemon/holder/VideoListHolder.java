package com.lhw.lemon.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.lemon.R;

public class VideoListHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public ImageView imageView;
    public TextView textView;

    public VideoListHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.video_item_cardView);
        imageView = itemView.findViewById(R.id.video_item_imageView);
        textView = itemView.findViewById(R.id.video_item_textView);
    }
}
