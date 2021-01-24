package com.example.licdatamanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class UpdateUserActivity extends AppCompatActivity {
    EditText NameETUP,mobileETUP,emailETUP,DOCETUP,pptETUP,branchETUP,finalamountETUP,premiumETUP,policyETUP;
    Button buttonUpdate;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        final Intent intent = getIntent();
        Gson gson = new Gson();
        user = gson.fromJson(intent.getStringExtra("myjson"),User.class);
        NameETUP = findViewById(R.id.NameETUP);
        mobileETUP = findViewById(R.id.mobileETUP);
        emailETUP = findViewById(R.id.emailETUP);
        DOCETUP = findViewById(R.id.DOCETUP);
        pptETUP = findViewById(R.id.pptETUP);
        branchETUP = findViewById(R.id.branchETUP);
        finalamountETUP = findViewById(R.id.finalamountETUP);
        premiumETUP = findViewById(R.id.premiumETUP);
        policyETUP = findViewById(R.id.policyETUP);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        setData();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = user.getId();
                User newUser = new User();
                newUser.setId(id);
                newUser.setUserName(NameETUP.getText().toString().trim());
                newUser.setMobileNO(mobileETUP.getText().toString().trim());
                newUser.setEmail(emailETUP.getText().toString().trim());
                newUser.setDOB(DOCETUP.getText().toString().trim());
                newUser.setPPT(pptETUP.getText().toString().trim());
                newUser.setBranchName(branchETUP.getText().toString().trim());
                newUser.setFinalAmount(finalamountETUP.getText().toString().trim());
                newUser.setPremiumAmount(premiumETUP.getText().toString().trim());
                newUser.setPolicyNumber(policyETUP.getText().toString().trim());
                MainActivity.myAppDatabase.myDeo().updateUser(newUser);
                Toast.makeText(getApplicationContext(),"Product Updated",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),ShowUserDataActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
    private void setData() {
        NameETUP.setText(user.getUserName());
        mobileETUP.setText(user.getMobileNO());
        emailETUP.setText(user.getEmail());
        DOCETUP.setText(user.getDOB());
        pptETUP.setText(user.getPPT());
        branchETUP.setText(user.getBranchName());
        finalamountETUP.setText(user.getFinalAmount());
        premiumETUP.setText(user.getPremiumAmount());
        policyETUP.setText(user.getPolicyNumber());
    }
}