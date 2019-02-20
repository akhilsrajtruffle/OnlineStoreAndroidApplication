package com.example.farahnstoreapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farahnstoreapp.Model.Product;
import com.example.farahnstoreapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListInPlAdapter extends RecyclerView.Adapter<ProductListInPlAdapter.ProctListViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductListInPlAdapter(Context context, List<Product> productList){
        this.context = context;

        this.productList = productList;
    }

    @NonNull
    @Override
    public ProctListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_product_in_pl,parent,false);

        return new ProctListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProctListViewHolder holder, final int position) {

        holder.title.setText(productList.get(position).getName());
        Picasso.with(context).load(productList.get(position).getIcon()).into(holder.ax);
        holder.price.setText(productList.get(position).getPrice()+" تومان ");
        holder.crd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent i = new Intent(context, DetailOfProduct.class);
                i.putExtra("ID",productList.get(position).getId());
                context.startActivity(i);
                */
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public  class ProctListViewHolder extends RecyclerView.ViewHolder{


        ImageView ax;
        TextView title,price;
        CardView crd;

        public ProctListViewHolder(View itemView) {
            super(itemView);

            ax = itemView.findViewById(R.id.img_list_product);
            title = itemView.findViewById(R.id.txt_name_list_product);
            price = itemView.findViewById(R.id.txt_price_list_product);
            crd = itemView.findViewById(R.id.crd_list_product);

        }
    }
}
