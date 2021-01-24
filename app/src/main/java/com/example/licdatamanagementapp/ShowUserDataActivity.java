package com.example.licdatamanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ShowUserDataActivity extends AppCompatActivity {
    public static TextView TVAddUser,TVUserCount;
    RecyclerView recyclerView;
    UserDetailsAdapter userDetailsAdapter;
    List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_data);
        TVAddUser = findViewById(R.id.TVAddUser);
        TVUserCount = findViewById(R.id.TVUserCount);
        recyclerView = findViewById(R.id.recyclerView);
        users = MainActivity.myAppDatabase.myDeo().getUsers();
        userDetailsAdapter = new UserDetailsAdapter(this,users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userDetailsAdapter);
        userDetailsAdapter.notifyDataSetChanged();
        getUserCount();
        TVAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getUserCount() {
        int getCount = users.size();
        TVUserCount.setText(Integer.toString(getCount));
    }
}