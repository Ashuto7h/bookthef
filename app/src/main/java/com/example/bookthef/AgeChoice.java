package com.example.bookthef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AgeChoice extends Activity implements View.OnClickListener {

    private FirebaseDatabase mdatabase;
    private DatabaseReference mDatabaseReference;
    private ImageView upto2;
    private ImageView upto5;
    private ImageView upto8;
    private ImageView upto12;
    private ImageView upto16;

    private String[] string = new String[12];
    private ImageView[] imageViews = new ImageView[12];
    private final String REFERENCE = "age/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.age_activity);

        mdatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mdatabase.getReference().child("age/ageImageLinks");

        upto2 = findViewById(R.id.upto2);
        upto5 = findViewById(R.id.upto5);
        upto8 = findViewById(R.id.upto8);
        upto12 = findViewById(R.id.upto12);
        upto16 = findViewById(R.id.upto16);

        upto2.setOnClickListener(this);
        upto5.setOnClickListener(this);
        upto8.setOnClickListener(this);
        upto12.setOnClickListener(this);
        upto16.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    string[i] = d.getValue().toString();
                    i++;
                }
                go();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AgeChoice.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == upto2.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "02"));
        }
        else if(id == upto5.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "35"));
        }
        else if(id == upto8.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "upto8"));
        }
        else if(id == upto12.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "upto12"));
        }
        else if(id == upto16.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "upto16"));
        }
    }

    public void go() {
        Picasso.with(this)
                .load(string[0])
                .into(upto2);

        Picasso.with(this)
                .load(string[1])
                .into(upto5);

        Picasso.with(this)
                .load(string[2])
                .into(upto8);

        Picasso.with(this)
                .load(string[3])
                .into(upto12);

        Picasso.with(this)
                .load(string[4])
                .into(upto16);

    }
}
