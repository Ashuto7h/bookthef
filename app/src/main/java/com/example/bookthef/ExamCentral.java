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

public class ExamCentral extends ExtendMe implements View.OnClickListener {

    private FirebaseDatabase mdatabase;
    private DatabaseReference mDatabaseReference;
    private ImageView civilServices;
    private ImageView governmentVacancy;
    private ImageView banking;
    private ImageView mba;
    private ImageView engineeringEntrance;
    private ImageView law;
    private ImageView medical;
    private ImageView softwareCertification;
    private ImageView finance;
    private ImageView international;
    private ImageView stateEntranceExam;
    private ImageView defence;

    private  String[] string = new String[12];
    private ImageView[] imageViews =  new ImageView[12];
    private final String REFERENCE = "examCentral/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_central);

        mdatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mdatabase.getReference().child("examCentral/examCentralImageLinks");

        defence = findViewById(R.id.examcentral_defence);
        civilServices = findViewById(R.id.examcentral_civil_services);
        engineeringEntrance = findViewById(R.id.examcentral_engineering_entrance);
        international = findViewById(R.id.examcentral_international);
        stateEntranceExam = findViewById(R.id.examcentral_statelevelexam);
        governmentVacancy = findViewById(R.id.examcentral_government_vacancy);
        finance = findViewById(R.id.examcenter_finance);
        medical = findViewById(R.id.examcentral_medical);
        softwareCertification = findViewById(R.id.examcentral_software_certificate);
        law = findViewById(R.id.examcentral_law);
        mba = findViewById(R.id.examcentral_management);
        banking = findViewById(R.id.examcentral_banking);

        //Todo setprogressbar
        setProgressDialog(this,"Loading...");
        defence.setOnClickListener(this);
        civilServices.setOnClickListener(this);
        engineeringEntrance.setOnClickListener(this);
        international.setOnClickListener(this);
        stateEntranceExam.setOnClickListener(this);
        governmentVacancy.setOnClickListener(this);
        finance.setOnClickListener(this);
        medical.setOnClickListener(this);
        law.setOnClickListener(this);
        softwareCertification.setOnClickListener(this);
        mba.setOnClickListener(this);
        banking.setOnClickListener(this);
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
                //todo bar
                hideBar();
                go();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ExamCentral.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void go(){
        Picasso.with(this)
                .load(string[0])
                .into(civilServices);

        Picasso.with(this)
                .load(string[1])
                .into(banking);

        Picasso.with(this)
                .load(string[2])
                .into(governmentVacancy);

        Picasso.with(this)
                .load(string[3])
                .into(stateEntranceExam);

        Picasso.with(this)
                .load(string[4])
                .into(engineeringEntrance);

        Picasso.with(this)
                .load(string[5])
                .into(mba);

        Picasso.with(this)
                .load(string[6])
                .into(medical);

        Picasso.with(this)
                .load(string[7])
                .into(law);

        Picasso.with(this)
                .load(string[8])
                .into(international);

        Picasso.with(this)
                .load(string[9])
                .into(defence);

        Picasso.with(this)
                .load(string[10])
                .into(softwareCertification);

        Picasso.with(this)
                .load(string[11])
                .into(finance);

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        // switch should be used but it is giving constant expression required error

        if(id == civilServices.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference",REFERENCE + "civilServices"));
        }
        else if(id == governmentVacancy.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "governmentVacancy"));
        }
        else if(id == finance.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "finance"));
        }
        else if(id == law.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "law"));
        }
        else if(id == mba.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "mba"));
        }
        else if(id == medical.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "medical"));
        }
        else if(id == banking.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "banking"));
        }
        else if(id == engineeringEntrance.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "engineeringEntrance"));
        }
        else if(id == stateEntranceExam.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "stateEntranceExam"));
        }
        else if(id == defence.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "defence"));
        }
        else if(id == international.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "international"));
        }
        else if(id == softwareCertification.getId()){
            startActivity(new Intent(this, MainActivity.class).putExtra("reference", REFERENCE + "softwareCertification"));
        }
    }
}
