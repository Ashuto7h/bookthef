package com.example.bookthef;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends ExtendMe implements View.OnClickListener {

    private EditText email_field;
    private EditText pass_field;
    private EditText user_name;
    private EditText phone_field;
    private Button signup_button;
    private TextView goto_login;
    private TextView privacy_policy;
    private TextView terms_of_use;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        privacy_policy = findViewById(R.id.privacy_policy);
        terms_of_use = findViewById(R.id.terms_of_use);
        email_field = findViewById(R.id.email_field);
        pass_field = findViewById(R.id.pass_field);
        user_name = findViewById(R.id.name_field);
        phone_field = findViewById(R.id.phone_field);
        signup_button = findViewById(R.id.signup_button);
        goto_login = findViewById(R.id.goto_signin);

        setProgressDialog(this,"Signing Up...");

        privacy_policy.setOnClickListener(this);
        terms_of_use.setOnClickListener(this);
        email_field.setOnClickListener(this);
        pass_field.setOnClickListener(this);
        user_name.setOnClickListener(this);
        phone_field.setOnClickListener(this);
        signup_button.setOnClickListener(this);
        goto_login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == signup_button.getId()){
            signUp();
        }
        else if(id == goto_login.getId()){
            startActivity(new Intent(SignUp.this, SignIn.class));
        }
        else if(id == privacy_policy.getId()){
            Toast.makeText(this, "Privacy Policy", Toast.LENGTH_SHORT).show();
        }
        else if(id == terms_of_use.getId()){
            Toast.makeText(this, "Terms Of Use", Toast.LENGTH_SHORT).show();
        }
    }

    public void signUp(){
        // set progress bar here!

        if(!validation()){
            Log.d("SignUp" , "Fill All Fields Correctly.");
            Toast.makeText(SignUp.this, "Fields Error" , Toast.LENGTH_SHORT).show();
        }
        else{
            if(signup_button.isEnabled())
                signup_button.setEnabled(false);

            // TODO: 2020-04-04 set progress bar
            showBar();

            String email = email_field.getText().toString().trim();
            String pass = pass_field.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d("SignUp:" , "SignUp Successfully!");
                        Toast.makeText(SignUp.this,"SignUp Successfully" , Toast.LENGTH_SHORT).show();
                        hideBar();
                        Intent intent = new Intent(SignUp.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Log.d("SignUp:" , "SignUp Failed");
                        Toast.makeText(SignUp.this,"SignUp Failed" , Toast.LENGTH_SHORT).show();
                    }
                }
                // hide progress bar here //
            });

            if(!signup_button.isEnabled())
                signup_button.setEnabled(true);
        }
    }

    private boolean validation(){
        boolean result = true;
        String phone = phone_field.getText().toString();
        String email = email_field.getText().toString();
        String pass = pass_field.getText().toString();
        String username = user_name.getText().toString();

        if(phone.length() != 10){
            phone_field.setError("Invalid Phone Number");
            result = false;
        }
        else{
            phone_field.setError(null);
        }
        if(!(email.contains("@") && email.contains(".com"))){
            email_field.setError("Invalid Email Address");
            result = false;
        }
        else{
            phone_field.setError(null);
        }
        if(username.isEmpty()){
            user_name.setError("Empty Field");
            result = false;
        }
        else{
            user_name.setError(null);
        }
        if(pass.length() < 8){
            pass_field.setError("Pass Must Contain 8 Characters");
            result = false;
        }
        /*else if(pass.matches()){
            passField.setError("Pass Must Be Like (ro34re)");
            result = false;
        }*/
        else{
            pass_field.setError(null);
        }

        return result ;
    }

}
