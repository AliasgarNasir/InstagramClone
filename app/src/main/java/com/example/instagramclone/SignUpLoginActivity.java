package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUpLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail,edtLoginPassword;
    private Button btnUserLogin,btnUserSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        setTitle("Login");

        btnUserLogin = findViewById(R.id.btnUserLogin);
        btnUserSignUp = findViewById(R.id.btnUserSignup);

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        btnUserLogin.setOnClickListener(this);
        btnUserSignUp.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

      }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnUserLogin:
                if (edtLoginEmail.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("")){
                    FancyToast.makeText(SignUpLoginActivity.this,"E-mail and Password are required.",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();

                }else {
                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null && e == null) {
                                        FancyToast.makeText(SignUpLoginActivity.this, user.getUsername() + "  Login Successful.", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                                        transitionToSocialMediaActivity();
                                    } else {
                                        FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                                    }
                                }
                            });
                }
                break;
            case R.id.btnUserSignup:
                Intent intent = new Intent(SignUpLoginActivity.this,SignUp.class);
                startActivity(intent);
                break;
        }


    }

    public void rootLayoutIsTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void transitionToSocialMediaActivity(){
        Intent intent = new Intent(SignUpLoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}







//        edtUserName = findViewById(R.id.edtUserName);
//        edtUserPassword = findViewById(R.id.edtUserPassword);
//        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
//        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
//
//        btnLogin = findViewById(R.id.btnLogin);
//        btnSignUp = findViewById(R.id.btnSignUp);
//
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final ParseUser appUser = new ParseUser();
//                appUser.setUsername(edtUserName.getText().toString());
//                appUser.setPassword(edtUserPassword.getText().toString());
//                appUser.signUpInBackground(new SignUpCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if(e == null){
//                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("userName") + " Signed Up Successfully.",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false ).show();
//
//                        }else{
//                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
//                        }
//                    }
//                });
//
//            }
//        });
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
//                    @Override
//                    public void done(ParseUser user, ParseException e) {
//                        if(user != null && e == null){
//                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username") + " is Logged in Successfully.",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false ).show();
//
//                        }else{
//                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
//                        }
//                    }
//                });
//
//            }
//        });