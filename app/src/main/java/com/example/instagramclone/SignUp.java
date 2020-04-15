package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private TextView numPunchSpeed, numPunchPower, numKickSpeed, numKickPower, txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        numPunchSpeed = findViewById(R.id.numPunchSpeed);
        numPunchPower = findViewById(R.id.numPunchPower);
        numKickSpeed = findViewById(R.id.numKickSpeed);
        numKickPower = findViewById(R.id.numKickPower);
        txtName = findViewById(R.id.txtName);

       btnSave.setOnClickListener(SignUp.this);

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
