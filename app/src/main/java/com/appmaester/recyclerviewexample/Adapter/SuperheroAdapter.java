package com.appmaester.recyclerviewexample.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appmaester.recyclerviewexample.Model.ListItems;
import com.appmaester.recyclerviewexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SuperheroAdapter extends RecyclerView.Adapter<SuperheroAdapter.ViewHolder> {

    private List<ListItems> listItems;
    private Context context;

    public SuperheroAdapter(List<ListItems> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListItems singleItem = listItems.get(position);

        holder.tvName.setText(singleItem.getName());
        holder.tvRealName.setText(singleItem.getRealname());
        holder.tvTeam.setText(singleItem.getTeam());
        holder.tvFirstAppear.setText(singleItem.getFirstappear());
        holder.tvCreatedBy.setText(singleItem.getCreatedby());

        Picasso.get().load(singleItem.getImgUrl()).into(holder.ivSuperImage);
        
        holder.linearLayoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is "+singleItem.getName()+"!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvRealName;
        TextView tvTeam;
        TextView tvFirstAppear;
        TextView tvCreatedBy;
        ImageView ivSuperImage;
        LinearLayout linearLayoutCard;

        ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvRealName = itemView.findViewById(R.id.tvRealname);
            tvTeam = itemView.findViewById(R.id.tvTeam);
            tvFirstAppear = itemView.findViewById(R.id.tvFirstAppearance);
            tvCreatedBy = itemView.findViewById(R.id.tvCreatedBy);
            ivSuperImage = itemView.findViewById(R.id.imageView);
            linearLayoutCard = itemView.findViewById(R.id.listviewLinLayout);
        }
    }

}
