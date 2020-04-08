package com.example.bookthef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ExtendMe implements View.OnClickListener{

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private List<Items> items = new ArrayList<>();


    private CardView byPopularSeries;
    private CardView byUniversities;
    private CardView byAuthor;
    private CardView byTextBooks;
    private CardView byExamCentral;
    private CardView byAge;
    private CardView byDeals;
    private CardView byLanguage;
    private CardView byTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setProgressDialog(this,"Loading...");
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        byAge = findViewById(R.id.age_main_activity);
        byAuthor = findViewById(R.id.authors_main_activity);
//        byDeals = findViewById(R.id.best_deals_main_activity);
        byLanguage = findViewById(R.id.indian_languages_main_activity);
//        byTop = findViewById(R.id.top_50_main_activity);
        byExamCentral = findViewById(R.id.examcenter_main_activity);
        byTextBooks = findViewById(R.id.textbooks_main_activity);
//        byUniversities = findViewById(R.id.universities_main_activity);
        byPopularSeries = findViewById(R.id.popular_series);

        byAge.setOnClickListener(this);
        byAuthor.setOnClickListener(this);
//        byDeals.setOnClickListener(this);
        byLanguage.setOnClickListener(this);
//        byTop.setOnClickListener(this);
        byTextBooks.setOnClickListener(this);
//        byUniversities.setOnClickListener(this);
        byExamCentral.setOnClickListener(this);
        byPopularSeries.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        String ref = intent.getStringExtra("reference");
        if(ref == null)
            databaseReference = firebaseDatabase.getReference().child("computerScience/dataStructures");
        else
            databaseReference = firebaseDatabase.getReference().child(ref);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getData();
   }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI(FirebaseAuth.getInstance().getCurrentUser());
    }

    private void getData() {
        // TODO: 2020-04-04 set progress
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot children : dataSnapshot.getChildren()) {
                        Items item = children.getValue(Items.class);
//                        String string =  item.getImage().substring(item.getImage().indexOf("\""), item.getImage().indexOf(":["));
//                        item.setImage(string.replaceAll("\"",""));
                        items.add(item);

                    }

            adapter = new MyAdapter(items, MainActivity.this);
            recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(MainActivity.this, Detail.class);
                        intent.putExtra("title", items.get(position).get_title());
                        intent.putExtra("author", items.get(position).getAuthor());
                        intent.putExtra("ratings", items.get(position).getRating());
                        intent.putExtra("publisher", items.get(position).getPublisher());
                        intent.putExtra("publishedDate", items.get(position).getPublished_date());
                        intent.putExtra("isbn_10", items.get(position).getIsbn_10());
                        intent.putExtra("isbn_13", items.get(position).getIsbn_13());
                        intent.putExtra("description", items.get(position).getDescription());
                        intent.putExtra("coverType", items.get(position).getCoverType());
                        intent.putExtra("Condition", items.get(position).getCondition());
                        intent.putExtra("pages", items.get(position).getPages());
                        intent.putExtra("imageUrl", items.get(position).getImage());
                        startActivity(intent);
                    }

                    @Override
                    public void onCartClick(final int position) {
                        final String user = mAuth.getUid();
                        final boolean[] flag = new boolean[1] ;
                        databaseReference.child("cart/").child(user).orderByChild("isbn_10").equalTo(items.get(position).getIsbn_10()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    items.get(position).setQuantity((Integer.parseInt(items.get(position).getQuantity()) + 1) + "");
                                    System.out.println("Quantity Increased!");
                                    databaseReference.child("cart/").child(user).child(items.get(position).getIsbn_10()).setValue(items.get(position));
                                }
                                else{
                                    System.out.println("flag");
                                    Toast.makeText(MainActivity.this, "newly added", Toast.LENGTH_SHORT).show();
                                    databaseReference.child("cart/").child(user).child(items.get(position).getIsbn_10()).setValue(items.get(position));

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.d("MainActivityOnCartClick", databaseError.getMessage());
                            }
                        });

                    }

                    @Override
                    public void onFavoriteClick(int position) {
                        Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("mainActivity", "OnCancelled Database ");
                Toast.makeText(MainActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == byAge.getId()){
            startActivity(new Intent(this, AgeChoice.class));
        }
        else if(id == byAuthor.getId()){
            startActivity(new Intent(this, Authors.class));
        }
        else if(id == byPopularSeries.getId()){
            startActivity(new Intent(this, Series.class));
        }
        else if(id == byTextBooks.getId()){
            startActivity(new Intent(this, TextBooks.class));
        }
        else if(id == byExamCentral.getId()){
            startActivity(new Intent(this, ExamCentral.class));
        }
//        else if(id == byUniversities.getId()){
//            Toast.makeText(this, "byUniversities", Toast.LENGTH_SHORT).show();
//        }
//        else if(id == byDeals.getId()){
//            Toast.makeText(this, "byDeals", Toast.LENGTH_SHORT).show();
//        }
        else if(id == byLanguage.getId()){
            startActivity(new Intent(this, Languages.class));
        }
//        else if(id == byTop.getId()){
//            Toast.makeText(this, "bytop50", Toast.LENGTH_SHORT).show();
//        }

    }

    private void updateUI(FirebaseUser user){
        if(user == null){
//             user is already logged in take him in
            Log.d("SignIn" , "user is already loggedin");
            Intent intent = new Intent(this,SignIn.class);
            startActivity(intent);
            finish();
        }
        else{
//             user is not logged in yet !
            return ;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         if(item.getItemId() == (R.id.cart_collection)){
             startActivity(new Intent(this, CartActivity.class));
         }
         if(item.getItemId() == (R.id.log_out)){
             showBar();
             FirebaseAuth.getInstance().signOut();
             startActivity(new Intent(this,MainActivity.class));
             finish();
             hideBar();

         }

         return true;
    }
}