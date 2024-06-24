package com.example.wzy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class KnotAdapter extends RecyclerView.Adapter<KnotAdapter.ViewHolder> {
    ArrayList<Knot> knots;

    public KnotAdapter(ArrayList<Knot> knots){
        this.knots = knots;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull KnotAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nazwa.setText(knots.get(position).getNazwa());
        holder.kategoria.setText(knots.get(position).getKategoria());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(knots.get(position).getZdjecie(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.zdjecie);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), KnotActivity.class);
                intent.putExtra("object", knots.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return knots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nazwa;
        ImageView zdjecie;
        TextView kategoria;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nazwa = itemView.findViewById(R.id.knotName);
            zdjecie = itemView.findViewById(R.id.knotPic);
            kategoria = itemView.findViewById(R.id.knotKat);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
