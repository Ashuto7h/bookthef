package com.example.bookthef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BaseActivity extends Activity implements View.OnClickListener {

    private int i = 0;
    private final String[] strings = {"action & Adventure" , "Arts, film & Photography", "Biographies, Diaries & True Accounts", "Business & Economics", "Children's & Young Adult", "Comics & Mangas", "Computing, Internet & Digital Media", "Crafts, Home & Lifestyle", "Crime, Thriller & Mystery" , "Exam Preparation" , "fantasy, Horror & Science Fiction" , "Health, Family & Personal" , "Development" , "Historical Fiction" , "History", "Humour" , "Language, Linguistics & Writing" , "Law" , "Literature & Fiction" , "Maps & Atlases" , "Politics" , "Reference", "Religion", "Romance" , "Science, Technoloty & Medicine" , "Society & Social Sciences" , "Sports" , "Textbooks & Study Guides", "Travel"};
    private FloatingActionButton goAhead;
    private int colorIds[] = {R.color.r1,R.color.r2,R.color.r3,R.color.r6,R.color.r7,R.color.r8,R.color.r9,R.color.r10,R.color.r11,R.color.r12,R.color.r13,R.color.r1,R.color.r2,R.color.r3,R.color.r6,R.color.r7,R.color.r8,R.color.r9,R.color.r10,R.color.r11,R.color.r12,R.color.r13,R.color.r1,R.color.r2,R.color.r3,R.color.r6,R.color.r7,R.color.r8,R.color.r9,R.color.r10,R.color.r11,R.color.r12,R.color.r13};
    private LinearLayout linearLayout;
    private ChipGroup chipGroup;
    private Chip[] chips = new Chip[29];
    private boolean[] chipStatus = new boolean[29];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_saperator);

        goAhead = findViewById(R.id.floating_go_ahead);
        linearLayout = findViewById(R.id.lLayout);

        goAhead.setOnClickListener(this);
        chipGroup = new ChipGroup(this);

        for(String string : strings){
            Chip chip = new Chip(this);
            chip.setText(string);
            chip.setOnClickListener(this);
            chip.setTextColor(getResources().getColor(colorIds[i]));
            chip.setChipBackgroundColorResource(R.color.r17);
            chip.setId(i);
            chips[i] = chip;
            chipStatus[i] = false;
            i++;
            chipGroup.addView(chip);
        }
        linearLayout.addView(chipGroup);
    }

    @Override
    public void onClick(View v) {
        int ii = v.getId();

        if(ii == goAhead.getId()){
            goFindNoToChipsClicked();
        }
        else {
            if(chipStatus[ii] == false) {
                chips[ii].setTextColor(getResources().getColor(R.color.white));
                chips[ii].setChipBackgroundColorResource(colorIds[ii]);
                chipStatus[ii] = true;
            }
            else{
                chips[ii].setTextColor(getResources().getColor(colorIds[ii]));
                chips[ii].setChipBackgroundColorResource(R.color.r17);
                chipStatus[ii] = false;
            }
        }
    }

    public void goFindNoToChipsClicked(){
        String[] string = new String[29];
        boolean result = false;

        for(int i = 0 ; i < 29 ; i++){
            if(chipStatus[i] == true){
                string[i] = chips[i].getText().toString();
                result = true;
            }
            else{
                string[i] = null;
            }
            if(result){
                startActivity(new Intent(this, MainActivity.class).putExtra("categories",string));
            }
            else{
                Toast.makeText(this, "Make Selection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
