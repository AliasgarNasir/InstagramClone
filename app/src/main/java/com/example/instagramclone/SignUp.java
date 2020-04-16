package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin,btnSignUp;
    private EditText edtUserEmail, edtUserName, edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SignUp");

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        edtUserEmail = findViewById(R.id.edtUserEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }
                return false;
            }
        });

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }


    }


    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnLogin:
                Intent intent = new Intent(SignUp.this,SignUpLoginActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSignUp:
                if(edtUserEmail.getText().toString().equals("")
                        || edtUserName.getText().toString().equals("")
                        || edtPassword.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this,"E-mail, Username and Password are required.",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();


                }else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtUserEmail.getText().toString());
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());
                    ProgressDialog progressDialog = new ProgressDialog(this);

                    progressDialog.setMessage("Signing Up " + edtUserName.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this,appUser.getUsername() + "  SignUp Successful.",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                            }

                        }
                    });
                    progressDialog.dismiss();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view);
        }

    }

    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            //to hide the keyboard by tapping in the layout.
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void transitionToSocialMediaActivity(){
        Intent intent = new Intent(SignUp.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}














//       txtGetdata.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
//               parseQuery.getInBackground("RON0DLjnFf", new GetCallback<ParseObject>() {
//                   @Override
//                   public void done(ParseObject object, ParseException e) {
//                       if (object != null && e == null){
//                           txtGetdata.setText(object.get("Name") + " - " + "Punch Power: " + object.get("punch_power"));
//                       }else{
//                           FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
//                       }
//                   }
//               });
//           }
//       });
//
//       btnGetAllData.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               allKickBoxers = "";
//               ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
//               queryAll.whereGreaterThan("punch_power",3000);
//               queryAll.findInBackground(new FindCallback<ParseObject>() {
//                   @Override
//                   public void done(List<ParseObject> objects, ParseException e) {
//                       if(e ==  null){
//                           if(objects.size() > 0){
//                               for (ParseObject kickBoxer : objects){
//                                   allKickBoxers = allKickBoxers + kickBoxer.get("Name") + "\n";
//                               }
//                               Toast.makeText(SignUp.this, allKickBoxers, Toast.LENGTH_SHORT).show();
//                           }
//                       }
//                   }
//               });
//
//           }
//       });
//
//        btnSwitchActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignUp.this,
//                        SignUpLoginActivity.class);
//                 startActivity(intent);
//            }
//        });


//    @Override
//    public void onClick(View v) {
//        try {
//            final ParseObject kickBoxer = new ParseObject("KickBoxer");
//            kickBoxer.put("Name",txtName.getText().toString());
//            kickBoxer.put("punch_speed",Integer.parseInt(numPunchSpeed.getText().toString()));
//            kickBoxer.put("punch_power",Integer.parseInt(numPunchPower.getText().toString()));
//            kickBoxer.put("kick_speed",Integer.parseInt(numKickSpeed.getText().toString()));
//            kickBoxer.put("kick_power",Integer.parseInt(numKickPower.getText().toString()));
//            kickBoxer.saveInBackground(new SaveCallback() {
//                @Override
//                public void done(ParseException e) {
//                    if(e==null){
//                        FancyToast.makeText(SignUp.this, kickBoxer.get("Name") + " saved to server.",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
//                    } else {
//                        FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
//                    }
//                }
//            });
//        }catch (Exception e){
//            FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
//
//        }
//    }