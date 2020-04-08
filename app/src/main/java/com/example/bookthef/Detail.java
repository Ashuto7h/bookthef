package com.example.bookthef;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class Detail extends BaseActivity {

    private ImageView imageView;
    private TextView title;
    private TextView author;
    private TextView publisher;
    private TextView isbn_10;
    private TextView isbn_13;
    private TextView ratings;
    private TextView description;
    private TextView coverType;
    private TextView condition;
    private TextView publishedDate;
    private Items item;

    private Intent intent;

    public Detail(){

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

          intent = getIntent();
//        item = new Items();
//        item = new Items(intent.getStringExtra("imageUrl"),intent.getStringExtra("publisher"),intent.getStringExtra("publishedDate"),intent.getStringExtra("pages"),intent.getStringExtra("language"),intent.getStringExtra("ratings"),intent.getStringExtra("coverType"),intent.getStringExtra("condition"),intent.getStringExtra("isbn_10"),intent.getStringExtra("isbn_13"),intent.getStringExtra("description"));
//        Toast.makeText(this, intent.getStringExtra("publisher"), Toast.LENGTH_SHORT).show();
//        item.setimage(intent.getStringExtra("imageUrl"));
//        item.setPublisher(intent.getStringExtra("publisher"));
//        item.setPublishedDate(intent.getStringExtra("publishedDate"));
//        item.setPages(intent.getStringExtra("pages"));
//        item.setLanguage(intent.getStringExtra("language"));
//        item.setRating(intent.getStringExtra("ratings"));
//        item.setCoverType(intent.getStringExtra("coverType"));
//        item.setCondition(intent.getStringExtra("condition"));
//        item.setIsbn_13(intent.getStringExtra("isbn_10"));
//        item.setIsbn_10(intent.getStringExtra("isbn_13"));
//        item.setDescription(intent.getStringExtra("description"));

        imageView = findViewById(R.id.book_image);
        title = findViewById(R.id.title_field);
        author = findViewById(R.id.author_field);
        publisher = findViewById(R.id.publisher_field);
        publishedDate = findViewById(R.id.published_field);
        description = findViewById(R.id.description_field);
        isbn_10 = findViewById(R.id.isbn_10);
        isbn_13 = findViewById(R.id.isbn_13);
        ratings = findViewById(R.id.book_rating);
        coverType = findViewById(R.id.binding_field);
        condition = findViewById(R.id.condition_field);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Picasso.with(this)
                .load(intent.getStringExtra("imageUrl"))
                .resize(150,200)
                .into(imageView);


        title.setText(intent.getStringExtra("title"));
        author.setText(intent.getStringExtra("author"));
        publisher.setText(intent.getStringExtra("publisher"));
        isbn_10.setText(intent.getStringExtra("isbn_10"));
        isbn_13.setText(intent.getStringExtra("isbn_13"));
        condition.setText(intent.getStringExtra("condition"));
        coverType.setText(intent.getStringExtra("coverType"));
        description.setText(intent.getStringExtra("description"));
        publishedDate.setText(intent.getStringExtra("publishedDate"));
        
    }


}
