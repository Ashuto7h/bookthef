package com.example.bookthef;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CartActivity extends ExtendMe {
    private TextView shippingCharges;
    private TextView totalAmmount;
    private TextView booksCost;
    private TextView totalItems;
    private  Button proceedToCheckout;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView cartRecycler;
    private CartAdapter cartAdapter;
    private boolean flag ;
    private int shippingCost = 70;

    private List<Items> items ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        // firebase connectivity
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("computerScience/dataStructures/").child("cart/").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        //todo progressbar
        setProgressDialog(this,"Loading Items...");
        cartRecycler = findViewById(R.id.cart_recycler);
        
        proceedToCheckout = findViewById(R.id.proceed_to_checkout);
        proceedToCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Proceed", Toast.LENGTH_SHORT).show();
            }
        });
        
        shippingCharges = findViewById(R.id.shipping_charges_cart_activity);
        totalAmmount = findViewById(R.id.total_cost_cart_activity);
        totalItems = findViewById(R.id.total_items_cart_activity);
        booksCost = findViewById(R.id.books_cost_cart_activity);

        cartRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cartRecycler.setLayoutManager(layoutManager);


        goGetData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        totalItems.setText(0 + "");
        totalAmmount.setText(0 + "");
        booksCost.setText(0+"");
        shippingCharges.setText(0+"");
    }

    public void goGetData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int cost = 0;
                int t = 0;
                items = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    items.add(child.getValue(Items.class));
                    cost += Integer.parseInt(child.getValue(Items.class).getPrice());
                    if (Integer.parseInt(child.getValue(Items.class).getQuantity()) < 1) {
                        flag = false;
                        t = 1;
                    }

                }

                if (t == 0) {
                    flag = true;
                }


                totalItems.setText(items.size() + "");
                booksCost.setText(cost + "");
                shippingCharges.setText(shippingCost + "");
                totalAmmount.setText(cost + shippingCost + "");

                if ((items.isEmpty() || !flag) && proceedToCheckout.isEnabled()) {
                    proceedToCheckout.setEnabled(false);
                    proceedToCheckout.setBackgroundColor(Color.GRAY);
                } else if (!proceedToCheckout.isEnabled()) {
                    proceedToCheckout.setEnabled(true);
                    proceedToCheckout.setBackgroundColor(getResources().getColor(R.color.blue));
                }
//                 set the data to array of type here
                CartAdapter adapter = new CartAdapter(CartActivity.this, items);
                cartRecycler.setAdapter(adapter);
                adapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
                    @Override
                    public void removeItemFromCart(final int position, Context contextView) {
                        Snackbar.make(findViewById(R.id.item_container), "Want To Remove From Cart!", BaseTransientBottomBar.LENGTH_SHORT).setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                databaseReference.child(items.get(position).getIsbn_10()).removeValue();
                            }
                        }).show();
                    }
                });

                //todo bar
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("CartActivity", databaseError.getMessage());
            }
        });
    }

}
