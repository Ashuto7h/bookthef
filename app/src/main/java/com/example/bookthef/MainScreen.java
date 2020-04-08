package com.example.bookthef;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        List<Integer> list = new ArrayList<>();
        list.add(Color.BLUE);
                list.add(Color.BLACK);
                list.add(Color.GREEN);


        ImageView imageView = new ImageView(this);
//        imageView.setPadding(4,4,4,4);
//        for(int i = 0 ; i < 3 ; i++){
//            (imageView.setBackgroundColor(list.get(i)));
//        }
    }
}
