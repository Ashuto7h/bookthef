package com.example.bookthef;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private DatabaseReference databaseReference;
    private OnItemClickListener mListner;
    private List<Items> items;
    public interface OnItemClickListener{
        void removeItemFromCart(int position,Context contextView);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListner = listener;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView author;
        private TextView price;
        private ImageView image;
        private Button removeItemFromCart;
        private TextView outOfStoke;

        public CartViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            outOfStoke = itemView.findViewById(R.id.out_of_stock);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
            price = itemView.findViewById(R.id.book_price);
            image = itemView.findViewById(R.id.book_image);
            removeItemFromCart = itemView.findViewById(R.id.remove);

            removeItemFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.removeItemFromCart(position,removeItemFromCart.getContext());
                        }
                    }
                }
            });

        }
    }

    public CartAdapter(final Context context, List<Items> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // bind all values to view
        if(Integer.parseInt(items.get(position).getQuantity()) < 1){
            holder.price.setText("out of stock!");
            holder.price.setTextColor(Color.RED);
        }
        else{
            holder.price.setText(items.get(position).getPrice());
        }
        holder.title.setText(items.get(position).get_title());
        holder.author.setText(items.get(position).getAuthor());
        Picasso.with(context)
                .load(items.get(position).getImage())
                .into(holder.image);
    }


    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card, parent, false);

        CartViewHolder vh = new CartViewHolder(view,mListner);
        return vh;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}

