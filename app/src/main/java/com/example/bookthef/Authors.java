package com.example.bookthef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Authors extends Activity implements View.OnClickListener {

    private FirebaseDatabase mdatabase;
    private DatabaseReference mDatabaseReference;

    private ImageView chethanBhagat;
    private ImageView agathaCristie;
    private ImageView danBrown;
    private ImageView robinSharma;
    private ImageView jefferyArcher;;
    private ImageView pauloCoehlo;

    private  String[] string = new String[12];
    private ImageView[] imageViews =  new ImageView[12];
    private final String REFERENCE = "examCentral/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authors);

        mdatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mdatabase.getReference().child("examCentral/examCentralImageLinks");

        chethanBhagat = findViewById(R.id.author1);
        agathaCristie = findViewById(R.id.author2);
        pauloCoehlo = findViewById(R.id.author3);
        jefferyArcher = findViewById(R.id.author4);
        robinSharma = findViewById(R.id.author5);
        danBrown = findViewById(R.id.author6);

        chethanBhagat.setOnClickListener(this);
        agathaCristie.setOnClickListener(this);
        pauloCoehlo.setOnClickListener(this);
        jefferyArcher.setOnClickListener(this);
        robinSharma.setOnClickListener(this);
        danBrown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // switch should be used but it is giving constant expression required error

        if(id == chethanBhagat.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference",REFERENCE + "chethanBhagat"));
        }
        else if(id == agathaCristie.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "agathaCristie"));
        }
        else if(id == pauloCoehlo.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "pauloCoehlo"));
        }
        else if(id == robinSharma.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "robinSharma"));
        }
        else if(id == danBrown.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "danBrown"));
        }
        else if(id == jefferyArcher.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "jefferyArcher"));
        }

    }
}
