package com.example.farahnstoreapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.farahnstoreapp.Model.Category;
import com.example.farahnstoreapp.R;

import java.util.List;

public class CatTitlesListAdapter extends RecyclerView.Adapter<CatTitlesListAdapter.CatTitleListViewHolder> {


    private Context context;
    private List<Category> categoryList;

    public CatTitlesListAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CatTitleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_cat,parent,false);

        return new CatTitleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatTitleListViewHolder holder, int position) {

        holder.txt.setText(categoryList.get(position).getName());
        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(context, CategoryList.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CatTitleListViewHolder extends RecyclerView.ViewHolder{

        LinearLayout rel;
        TextView txt;

        public CatTitleListViewHolder(View itemView) {
            super(itemView);
            rel = itemView.findViewById(R.id.rel_list_cat);
            txt = itemView.findViewById(R.id.text_list_cat);
        }
    }

}
