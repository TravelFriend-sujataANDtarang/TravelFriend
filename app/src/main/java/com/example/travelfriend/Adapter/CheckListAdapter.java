package com.example.travelfriend.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelfriend.Database.RoomDB;
import com.example.travelfriend.Models.Items;
import com.example.travelfriend.R;

import java.util.List;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListViewHolder>{

   Context context;
   List<Items> itemsList;
   RoomDB database;
   String show;

    public CheckListAdapter() {
    }

    public CheckListAdapter(Context context, List<Items> itemsList, RoomDB database, String show) {
        this.context = context;
        this.itemsList = itemsList;
        this.database = database;
        this.show = show;
        if(itemsList.size()==0){
            Toast.makeText(context.getApplicationContext(), "Nothing to show", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public CheckListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckListViewHolder(LayoutInflater.from(context).inflate(R.layout.check_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckListViewHolder holder, int position) {
           holder.checkBox.setText(itemsList.get(position).getItemname());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}

class CheckListViewHolder extends RecyclerView.ViewHolder{
    LinearLayout layout;
    CheckBox checkBox;
    Button btnDelete;


    public CheckListViewHolder(View itemView){
        super(itemView);
        layout=itemView.findViewById(R.id.linearLayout);
        checkBox=itemView.findViewById(R.id.checkbox);
        btnDelete=itemView.findViewById(R.id.btnDelete);

    }
}