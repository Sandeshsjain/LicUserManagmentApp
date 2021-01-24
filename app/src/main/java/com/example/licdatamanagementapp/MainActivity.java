package com.example.licdatamanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    EditText NameET,mobileET,emailET,DOCET,pptET,branchET,finalamountET,premiumET,policyET;
    public static TextView totalClientCountTV,viewAllbtn;
    Button buttonSubmit;
    public static MyAppDatabase myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"userDb").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        NameET = findViewById(R.id.NameET);
        mobileET = findViewById(R.id.mobileET);
        emailET = findViewById(R.id.emailET);
        DOCET = findViewById(R.id.DOCET);
        pptET = findViewById(R.id.pptET);
        branchET = findViewById(R.id.branchET);
        finalamountET = findViewById(R.id.finalamountET);
        premiumET = findViewById(R.id.premiumET);
        policyET = findViewById(R.id.policyET);
        totalClientCountTV = findViewById(R.id.totalClientCountTV);
        viewAllbtn = findViewById(R.id.viewAllbtn);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        getCustomerCount();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDataBase();
                getCustomerCount();
            }
        });

        viewAllbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShowUserDataActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getCustomerCount() {
        List<User> users = myAppDatabase.myDeo().getUsers();
        int getCount = users.size();
        totalClientCountTV.setText(Integer.toString(getCount));
    }

    private void addDataToDataBase() {
        User user = new User();
        if(NameET.getText().toString().isEmpty()){
            NameET.setError("Name field is required");
        }
        else if(mobileET.getText().toString().isEmpty()){
            mobileET.setError("Mobile field is required");
        }
        else if(finalamountET.getText().toString().isEmpty()){
            finalamountET.setError("Final amount field is required");
        }
        else if(premiumET.getText().toString().isEmpty()){
            premiumET.setError("Premium amount field is required");
        }
        else if(policyET.getText().toString().isEmpty()){
            policyET.setError("Policy number field is required");
        }
        else{
            user.setUserName(NameET.getText().toString().trim());
            user.setBranchName(branchET.getText().toString().trim());
            user.setDOB(DOCET.getText().toString().trim());
            user.setEmail(emailET.getText().toString().trim());
            user.setFinalAmount(finalamountET.getText().toString().trim());
            user.setMobileNO(mobileET.getText().toString().trim());
            user.setPolicyNumber(policyET.getText().toString().trim());
            user.setPPT(pptET.getText().toString().trim());
            user.setPremiumAmount(premiumET.getText().toString().trim());
            myAppDatabase.myDeo().addUser(user);
            Toast.makeText(getApplicationContext(),"User Added Successfully",Toast.LENGTH_SHORT).show();
            NameET.setText("");
            mobileET.setText("");
            emailET.setText("");
            DOCET.setText("");
            pptET.setText("");
            branchET.setText("");
            finalamountET.setText("");
            premiumET.setText("");
            policyET.setText("");
        }
    }
}