package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave,btnGetAllData;
    private TextView numPunchSpeed, numPunchPower, numKickSpeed, numKickPower, txtName;
    private TextView txtGetdata;

    private String allKickBoxers;

    private Button btnSwitchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnSwitchActivity = findViewById(R.id.btnSwitchActivity);

        numPunchSpeed = findViewById(R.id.numPunchSpeed);
        numPunchPower = findViewById(R.id.numPunchPower);
        numKickSpeed = findViewById(R.id.numKickSpeed);
        numKickPower = findViewById(R.id.numKickPower);
        txtName = findViewById(R.id.txtName);

        txtGetdata = findViewById(R.id.txtGetData);

       btnSave.setOnClickListener(SignUp.this);

       txtGetdata.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            //   ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
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
           }
       });

       btnGetAllData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               allKickBoxers = "";
               ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
               queryAll.whereGreaterThan("punch_power",3000);
               queryAll.findInBackground(new FindCallback<ParseObject>() {
                   @Override
                   public void done(List<ParseObject> objects, ParseException e) {
                       if(e ==  null){
                           if(objects.size() > 0){
                               for (ParseObject kickBoxer : objects){
                                   allKickBoxers = allKickBoxers + kickBoxer.get("Name") + "\n";
                               }
                               Toast.makeText(SignUp.this, allKickBoxers, Toast.LENGTH_SHORT).show();
                           }
                       }
                   }
               });

           }
       });

        btnSwitchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,
                        SignUpLoginActivity.class);
                 startActivity(intent);
            }
        });
    }




    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name",txtName.getText().toString());
            kickBoxer.put("punch_speed",Integer.parseInt(numPunchSpeed.getText().toString()));
            kickBoxer.put("punch_power",Integer.parseInt(numPunchPower.getText().toString()));
            kickBoxer.put("kick_speed",Integer.parseInt(numKickSpeed.getText().toString()));
            kickBoxer.put("kick_power",Integer.parseInt(numKickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        FancyToast.makeText(SignUp.this, kickBoxer.get("Name") + " saved to server.",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    } else {
                        FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    }
                }
            });
        }catch (Exception e){
            FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

        }
    }
}


