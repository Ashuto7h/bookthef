package com.example.bookthef;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Items> items ;
    private Context context;
    private OnItemClickListener mListener;
    private static boolean flag;

    public  interface OnItemClickListener{
         void onItemClick(int position);
         void onCartClick(int position);
         void onFavoriteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private RatingBar ratings;
        private TextView originalPrice;
        private TextView bookTitle;
        private TextView bookAuthor;
        private ImageView bookCover;
        private ImageView addToCart;
        private ImageView favorite;
        private TextView outOfStock;

        public MyViewHolder(View v,final OnItemClickListener listener) {
            super(v);
            outOfStock = v.findViewById(R.id.out_of_stock);
            bookTitle = v.findViewById(R.id.book_title);
            bookAuthor = v.findViewById(R.id.book_author);
            originalPrice = v.findViewById(R.id.book_price);
            ratings = v.findViewById(R.id.book_rating);
            ratings.setNumStars(5);
            bookCover = v.findViewById(R.id.book_image);


            addToCart = v.findViewById(R.id.add_to_cart); // disable it
            favorite = v.findViewById(R.id.add_to_favorite);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onCartClick(position);
                        }
                    }
                }
            });

            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onFavoriteClick(position);
                    }
                }
            });
        }
    }

    public MyAdapter(List<Items> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        MyViewHolder vh = new MyViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.originalPrice.setText(items.get(position).getPrice());
        holder.bookTitle.setText(items.get(position).get_title());
        holder.bookAuthor.setText(items.get(position).getAuthor());
        holder.originalPrice.setText(items.get(position).getOriginalPrice());
        if(items.get(position).getRating() != null)
            holder.ratings.setRating( Float.parseFloat(items.get(position).getRating()));
        else
            holder.ratings.setRating(0);
        Picasso.with(context)
                .load(items.get(position).getImage())
                .into(holder.bookCover);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}