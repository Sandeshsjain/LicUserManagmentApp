package com.example.licdatamanagementapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.DataViewHolder>  {
    Context context;
    List<User> users;
    public UserDetailsAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,parent,false);
        return new DataViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.nameContainer.setText(users.get(position).getUserName());
        holder.dobContainer.setText(users.get(position).getDOB());
        holder.pptContainer.setText(users.get(position).getPPT());
        holder.emailContainer.setText(users.get(position).getEmail());
        holder.pamountContainer.setText(users.get(position).getPremiumAmount());
        holder.famountContainer.setText(users.get(position).getFinalAmount());
        holder.mobileContainer.setText(users.get(position).getMobileNO());
        holder.policyContainer.setText(users.get(position).getPolicyNumber());
        holder.branchNameContainer.setText(users.get(position).getBranchName());
        holder.ButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateUserActivity.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(users.get(position));
                intent.putExtra("myjson",myJson);
                context.startActivity(intent);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Alert....");
                builder.setMessage("Are you sure you wanna delete");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = new User();
                        user.setId(users.get(position).getId());
                        MainActivity.myAppDatabase.myDeo().deleteUser(user);
                        Toast.makeText(context,"User deleted",Toast.LENGTH_SHORT).show();
                        users.remove(position);
                        notifyItemRemoved(position);
                        ShowUserDataActivity.TVUserCount.setText(Integer.toString(users.size()));
                        MainActivity.totalClientCountTV.setText(Integer.toString(users.size()));
                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView nameContainer,dobContainer,pptContainer,emailContainer,pamountContainer,famountContainer,mobileContainer,policyContainer,branchNameContainer;
        Button deleteButton,ButtonEdit;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            nameContainer = itemView.findViewById(R.id.nameContainer);
            dobContainer = itemView.findViewById(R.id.dobContainer);
            pptContainer = itemView.findViewById(R.id.pptContainer);
            emailContainer = itemView.findViewById(R.id.emailContainer);
            pamountContainer = itemView.findViewById(R.id.pamountContainer);
            famountContainer = itemView.findViewById(R.id.famountContainer);
            mobileContainer = itemView.findViewById(R.id.mobileContainer);
            policyContainer = itemView.findViewById(R.id.policyContainer);
            branchNameContainer = itemView.findViewById(R.id.branchNameContainer);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            ButtonEdit = itemView.findViewById(R.id.ButtonEdit);
        }
    }
}
