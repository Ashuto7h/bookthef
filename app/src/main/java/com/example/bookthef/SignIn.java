package com.example.bookthef;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends ExtendMe  implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button login_button;
    private TextView goto_signUp;
    private EditText emailField;
    private EditText passField;
    private TextView privacy_policy;
    private TextView terms_of_use;
    private TextView forgot_pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        privacy_policy = findViewById(R.id.privacy_policy);
        terms_of_use = findViewById(R.id.terms_of_use);
        forgot_pass = findViewById(R.id.forgot_password);

        login_button = findViewById(R.id.log_in_button);
        goto_signUp = findViewById(R.id.goto_signup);
        emailField = findViewById(R.id.email_field);
        passField = findViewById(R.id.pass_field);
        setProgressDialog(this,"Signing In...");

        privacy_policy.setOnClickListener(this);
        terms_of_use.setOnClickListener(this);
        forgot_pass.setOnClickListener(this);
        login_button.setOnClickListener(this);
        goto_signUp.setOnClickListener(this);
        emailField.setOnClickListener(this);
        passField.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUi(FirebaseAuth.getInstance().getCurrentUser());
    }

    private void login(){
        // start progress bar
        if(!validation()){
            Log.d("Invalid String:" , "Fill All Field's Correctly!");
            Toast.makeText(this,"Missing value of circumstances" , Toast.LENGTH_SHORT);
        }
        else{
            showBar();
            if (login_button.isEnabled())
                login_button.setEnabled(false);

            String email  = emailField.getText().toString().trim();
            String pass = passField.getText().toString().trim();

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d("Login Success:" , "logged in successfully");
                        Toast.makeText(SignIn.this,"LoggedIn" , Toast.LENGTH_SHORT).show();
                        hideBar();
                        Intent intent = new Intent(SignIn.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        Log.d("Login Failed:" , task.getResult().toString());
                        Toast.makeText(SignIn.this,"Login Failed" , Toast.LENGTH_SHORT).show();
                    }
                    login_button.setEnabled(true);
                }

                // progress bar stops here //
            });

            //end progress bar
        }

    }


    private boolean validation(){
        boolean result = true;
        String email = emailField.getText().toString();
        String pass = passField.getText().toString();

        if(!(email.contains("@") && email.contains(".com"))){
            emailField.setError("Invalid Email Address");
            result = false;
        }
        else{
            emailField.setError(null);
        }
        if(pass.length() < 8){
            passField.setError("Pass Must Contain 8 Characters");
            result = false;
        }
        /*else if(pass.matches()){
            passField.setError("Pass Must Be Like (ro34re)");
            result = false;
        }*/
        else{
            passField.setError(null);
        }
        return result;

    }

    public void updateUi(FirebaseUser user){
        if(user != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == privacy_policy.getId()){
            Toast.makeText(this, "Privacy Policy", Toast.LENGTH_SHORT).show();
        }
        else if(id == terms_of_use.getId()){
            Toast.makeText(this, "Terms Of Use", Toast.LENGTH_SHORT).show();
        }
        else if(id == forgot_pass.getId()){
            Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show();
        }
        else if(id == goto_signUp.getId()){
            startActivity(new Intent(this, SignUp.class));
        }
        else if(id == login_button.getId()){
            login();
        }
    }
}
