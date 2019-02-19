package com.example.farahnstoreapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farahnstoreapp.List_of_products;
import com.example.farahnstoreapp.Model.Category;
import com.example.farahnstoreapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCatAdapter extends RecyclerView.Adapter<SubCatAdapter.subCatViewHolder> {

    private Context context;
    private List<Category> categoryList;

    public SubCatAdapter(Context context, List<Category> categoryList){

        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public subCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_sub_cat,parent,false);
        return new subCatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subCatViewHolder holder, final int position) {

        holder.title.setText(categoryList.get(position).getName());
        Picasso.with(context).load(categoryList.get(position).getIcon()).into(holder.icon);
        holder.crd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, List_of_products.class);
                i.putExtra("ID",categoryList.get(position).getId()+"");
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public  class subCatViewHolder extends RecyclerView.ViewHolder{

        CardView crd;
        TextView title;
        ImageView icon;

        public subCatViewHolder(View itemView) {
            super(itemView);

            crd = itemView.findViewById(R.id.crd_list_sub_cat);
            title = itemView.findViewById(R.id.title_list_sub_cat);
            icon = itemView.findViewById(R.id.img_list_sub_cat);
        }
    }

}
