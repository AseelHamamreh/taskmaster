package com.example.taskmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmaster.R;
import com.example.taskmaster.room.Task;
import com.example.taskmaster.views.TaskDetailsPage;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

    List<Task> data;
    Context context;


    public myAdapter(Context ct, List<Task> l){
        context = ct;
        data=l;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  myAdapter.MyViewHolder holder, int position) {
        Task item = data.get(position);
        holder.textView15.setText(item.getTitle());
        holder.textView17.setText(item.getBody());
        holder.textView12.setText(item.getState());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TaskDetailsPage.class);
                intent.putExtra("title", data.get(position).getTitle());
                intent.putExtra("description", data.get(position).getBody());
                intent.putExtra("status", data.get(position).getState());
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView17 , textView15, textView12;

        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView15 = itemView.findViewById(R.id.textTitle);
            textView17 = itemView.findViewById(R.id.textDes);
            textView12 = itemView.findViewById(R.id.textStatus);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }



}
