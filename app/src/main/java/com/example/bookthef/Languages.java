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

public class Languages extends ExtendMe implements View.OnClickListener {

    private FirebaseDatabase mdatabase;
    private DatabaseReference mDatabaseReference;
    private ImageView hindi;
    private ImageView tamil;
    private ImageView marathi;
    private ImageView telugu;
    private ImageView malayalam;
    private ImageView gujarati;
    private ImageView bengali;
    private ImageView kannada;

    private String[] string = new String[12];
    private ImageView[] imageViews = new ImageView[12];
    private final String REFERENCE = "languages/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.languages_activity);

        mdatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mdatabase.getReference().child("languages/languagesImageLinks");

        //todo progressBar
        setProgressDialog(this, "Loading...");
        hindi =  findViewById(R.id.languages_hindi);
        tamil = findViewById(R.id.languages_tamil);
        marathi = findViewById(R.id.languages_marathi);
        telugu = findViewById(R.id.languages_telugu);
        malayalam = findViewById(R.id.languages_malayalam);
        gujarati = findViewById(R.id.languages_gujarati);
        bengali = findViewById(R.id.languages_bengali);
        kannada = findViewById(R.id.languages_kannada);

        hindi.setOnClickListener(this);
        tamil.setOnClickListener(this);
        marathi.setOnClickListener(this);
        telugu.setOnClickListener(this);
        malayalam.setOnClickListener(this);
        gujarati.setOnClickListener(this);
        bengali.setOnClickListener(this);
        kannada.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showBar();
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    string[i] = d.getValue().toString();
                    i++;
                }
                hideBar();
                go();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Languages.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == hindi.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "hindi"));
        }
        else if(id == tamil.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "tamil"));
        }
        else if(id == marathi.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "marathi"));
        }
        else if(id == telugu.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "telugu"));
        }
        else if(id == malayalam.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "malayalam"));
        }
        else if(id == gujarati.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "gujarati"));
        }
        else if(id == bengali.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "bengali"));
        }
        else if(id == kannada.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "kannada"));
        }
    }

    public void go() {
        Picasso.with(this)
                .load(string[0])
                .into(hindi);

        Picasso.with(this)
                .load(string[1])
                .into(tamil);

        Picasso.with(this)
                .load(string[2])
                .into(marathi);

        Picasso.with(this)
                .load(string[3])
                .into(telugu);

        Picasso.with(this)
                .load(string[4])
                .into(malayalam);
        Picasso.with(this)
                .load(string[2])
                .into(gujarati);

        Picasso.with(this)
                .load(string[3])
                .into(bengali);

        Picasso.with(this)
                .load(string[4])
                .into(kannada);

    }
}
