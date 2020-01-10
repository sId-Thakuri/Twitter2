package com.n.twitter;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.biplav.twitterassignment.api.Reftofit;

import model.Check;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText signup_email, signup_username;
    ImageView signup_Us, signUp_Em, back;
    Button btnNext;
    int countUsername = 6;
    int initialInteger = 0;
    String method = "email";
    String Email = "";
    String Username = "";
    boolean chekU=false;
    boolean chekE=false;
    TextView tvChange, snEmError, snUsError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enables fullscreen
        setContentView(R.layout.activity_register);


        signup_email = findViewById( R.id.SN_email );
        signup_username = findViewById( R.id.SN_usernmae );
        back=findViewById( R.id.SN_back );
        snEmError = findViewById( R.id.SN_pass_error );
        snUsError = findViewById( R.id.SN_username_error );
        signup_Us = findViewById( R.id.SN_userP );
        signUp_Em = findViewById( R.id.SN_emailP );
        btnNext = findViewById( R.id.btn_FS_signup );
        tvChange = findViewById( R.id.textView9 );
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            Email = bundle.getString( "email" );
//            Username = bundle.getString( "username" );
//            signup_email.setText( bundle.getString( "email" ) );
//            signup_username.setText( bundle.getString( "username" ) );
//        }
        back.setOnClickListener( new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent back=new Intent( SignUp.this,MainActivity.class );
                                         startActivity( back );
                                     }
                                 }
        );
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chekE==true&&chekU==true){

                  User user = new User( Email );
                   Checkuser( user );

                }else{
                    Toast.makeText( SignUp.this, "fill require field with valid information", Toast.LENGTH_SHORT ).show();
                    return;
                }

            }
        } );
        signup_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int countL = signup_username.length();
                if (count > 6) {
                    if (countUsername >= 6) {
                        countUsername = 50 - countL;
                        snUsError.setTextColor( Color.BLACK );
                        snUsError.setText( "" + countUsername );
                        signup_Us.setImageResource( R.drawable.ic_checked );
                        chekU=true;
                        Username = signup_username.getText().toString();
                        return;
                    } else if (countUsername < 6) {
                        countUsername = 50 - countL;
                        snUsError.setTextColor( Color.RED );
                        snUsError.setText( "Must be 50 characters or fewer." );
                        snUsError.append( "      " + countUsername );
                        chekU=false;
                        signup_Us.setImageResource( R.drawable.ic_clear );
                        return;


                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        tvChange.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initialInteger == 0) {
                    method = "phone";
                    signup_email.setText( "" );
                    initialInteger++;
                    signup_email.setHint( "used Phone number" );
                    signup_email.setInputType( InputType.TYPE_CLASS_PHONE );
                    signup_email.setMaxLines( 13 );
                    tvChange.setText( "use email instead" );
                    return;
                } else {
                    method = "email";
                    signup_email.setText( "" );
                    initialInteger = 0;
                    signup_email.setInputType( InputType.TYPE_CLASS_TEXT );
                    signup_email.setHint( "used Email" );
                    tvChange.setText( "use phone instead" );
                    return;
                }

            }
        } );
        signup_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                switch (method) {
                    case "email":
                        snEmError.setText( "" );
                        if ((signup_email.getText().toString().toLowerCase().contains( "@" )) && (signup_email.getText().toString().toLowerCase().contains( ".com" ))) {
                            signUp_Em.setImageResource( R.drawable.ic_checked );
                            Email = signup_email.getText().toString();
                            chekE=true;
                        } else {
                            snEmError.setText( "check your email" );
                            signUp_Em.setImageResource( R.drawable.ic_clear );
                            chekE=false;

                        }
                        break;
                    case "phone":
                        snEmError.setText( "" );
                        if ((signup_email.length() != 10)) {
                            snEmError.setText( "check your number" );
                            signUp_Em.setImageResource( R.drawable.ic_clear );
                            chekE=false;
                            return;

                        } else {
                            signUp_Em.setImageResource( R.drawable.ic_checked );
                            Email = signup_email.getText().toString();
                            chekE=true;
                            return;

                        }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

    }

    void Checkuser(User us) {
        Reftofit apiClass = new Reftofit();
        Call<Check> checkCall = apiClass.calls().check( us );
        checkCall.enqueue( new Callback<Check>() {
            @Override
            public void onResponse(Call<Check> call, Response<Check> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText( SignUp.this, "error" + response.code(), Toast.LENGTH_SHORT ).show();
                    Log.d( "error", "error" + response.code() );
                    return;
                }
                Check check = response.body();
                //Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
                if (check.getStatus().equals( "go ahead" )) {
                    Intent next = new Intent( SignUp.this, CustomizeExperience.class );
                    next.putExtra( "email", Email );
                    next.putExtra( "username", Username );
                    startActivity( next );
                    return;
                } else {
                    //Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
                    snEmError.setText( "exited" );
                    snEmError.setTextColor( Color.RED );
                }
            }

            @Override
            public void onFailure(Call<Check> call, Throwable t) {
                Toast.makeText( SignUp.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                Log.d( "error", "error   " + t.getLocalizedMessage() );

            }
        } );
    }




}



