package com.example.bookthef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TextBooks extends ExtendMe implements View.OnClickListener {

    private FirebaseDatabase mdatabase;
    private DatabaseReference mDatabaseReference;

    private ImageView textBooks;
    private ImageView engineering;
    private ImageView english;
    private ImageView computerScience;
    private ImageView medical;;
    private ImageView scienceMaths;

    private  String[] string = new String[12];
    private ImageView[] imageViews =  new ImageView[12];
    private final String REFERENCE = "examCentral/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_books);

        //todo bar
        setProgressDialog(this, "Loading Items...");

        mdatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mdatabase.getReference().child("examCentral/examCentralImageLinks");

        textBooks = findViewById(R.id.schooltext_textbooks);
        engineering = findViewById(R.id.engineering_textbooks);
        scienceMaths = findViewById(R.id.science_maths_textbooks);
        medical = findViewById(R.id.medical_textbooks);
        computerScience = findViewById(R.id.computer_science_textbooks);
        english = findViewById(R.id.english_textbooks);

        textBooks.setOnClickListener(this);
        engineering.setOnClickListener(this);
        scienceMaths.setOnClickListener(this);
        medical.setOnClickListener(this);
        computerScience.setOnClickListener(this);
        english.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // switch should be used but it is giving constant expression required error

        if(id == textBooks.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference",REFERENCE + "textBooks"));
        }
        else if(id == engineering.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "engineering"));
        }
        else if(id == scienceMaths.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "scienceMaths"));
        }
        else if(id == computerScience.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "computerScience"));
        }
        else if(id == english.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "english"));
        }
        else if(id == medical.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "medical"));
        }

    }
}
