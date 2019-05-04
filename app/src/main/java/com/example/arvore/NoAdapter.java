package com.example.arvore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NoAdapter extends RecyclerView.Adapter<NoAdapter.ViewHolder> {

    private final List<No> nos;
    private final Context context;

    NoAdapter(List<No> nos, Context context) {
        this.nos = nos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nome.setText(nos.get(position).getNome());
        holder.filhos.setLayoutManager(new LinearLayoutManager(context));
        holder.filhos.setAdapter(new NoAdapter(nos.get(position).getNo(), context));

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).delete(((MainActivity)context).no, nos.get(position).getId());
            }
        });

        holder.espacamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.filhos.getVisibility() == View.VISIBLE) {
                    holder.filhos.setVisibility(View.GONE);
                }
                else {
                    holder.filhos.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.filhos.getVisibility() == View.VISIBLE) {
                    holder.filhos.setVisibility(View.GONE);
                }
                else {
                    holder.filhos.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.nos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nome;
        final RecyclerView filhos;
        final ImageView remove;
        final View espacamento;

        ViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nome);
            filhos = (RecyclerView) view.findViewById(R.id.filhos);
            remove = (ImageView) view.findViewById(R.id.remove);
            espacamento = (View) view.findViewById(R.id.espacamento);
        }
    }
}
