package com.example.bookthef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Series extends Activity implements View.OnClickListener {

    private FirebaseDatabase mdatabase;
    private DatabaseReference mDatabaseReference;

    private ImageView harrypotter;
    private ImageView geronimoStilton;
    private ImageView AmarChitraKatha;
    private ImageView shivaTrilogy;
    private ImageView dairyWimpyKid;;
    private ImageView gameOfThrones;

    private  String[] string = new String[12];
    private ImageView[] imageViews =  new ImageView[12];
    private final String REFERENCE = "examCentral/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.series);

        mdatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mdatabase.getReference().child("examCentral/examCentralImageLinks");

        harrypotter = findViewById(R.id.series1);
        geronimoStilton = findViewById(R.id.series2);
        gameOfThrones = findViewById(R.id.series3);
        dairyWimpyKid = findViewById(R.id.series4);
        shivaTrilogy = findViewById(R.id.series5);
        AmarChitraKatha = findViewById(R.id.series6);

        harrypotter.setOnClickListener(this);
        geronimoStilton.setOnClickListener(this);
        gameOfThrones.setOnClickListener(this);
        dairyWimpyKid.setOnClickListener(this);
        shivaTrilogy.setOnClickListener(this);
        AmarChitraKatha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // switch should be used but it is giving constant expression required error

        if(id == harrypotter.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference",REFERENCE + "harrypotter"));
        }
        else if(id == geronimoStilton.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "geronimoStilton"));
        }
        else if(id == gameOfThrones.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "gameOfThrones"));
        }
        else if(id == shivaTrilogy.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "shivaTrilogy"));
        }
        else if(id == AmarChitraKatha.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "AmarChitraKatha"));
        }
        else if(id == dairyWimpyKid.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "dairyWimpyKid"));
        }

    }
}
