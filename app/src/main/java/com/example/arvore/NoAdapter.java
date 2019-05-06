package com.example.arvore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class NoAdapter extends RecyclerView.Adapter<NoAdapter.ViewHolder> {

    private final List<No> nos;
    private final Context context;
    private String m_Text;


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

        holder.inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(position);
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

                if (nos.get(position).getNo().isEmpty()){
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Conteudo.class);
                    intent.putExtra("nome", nos.get(position).getNome());
                    intent.putExtra("conteudo", nos.get(position).getConteudo());
                    context.startActivity(intent);
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

                if (nos.get(position).getNo().isEmpty()){
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Conteudo.class);
                    intent.putExtra("nome", nos.get(position).getNome());
                    intent.putExtra("conteudo", nos.get(position).getConteudo());

                    context.startActivity(intent);
                }
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nos.get(position).getNo().isEmpty())
                    onCreateDialogAdd(position);
            }
        });


        if(nos.get(position).getNo().isEmpty())
            holder.add.setVisibility(View.VISIBLE);
        else
            holder.add.setVisibility(View.GONE);
    }

    private void onCreateDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Insira o Nome");

// Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT ); //| InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        Log.i("Position", ""+position);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Log.i("Position", ""+position);
                Toast.makeText(context, m_Text, Toast.LENGTH_LONG).show();
                ((MainActivity)context).inserir(((MainActivity)context).no, m_Text , nos.get(position).getId());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private void onCreateDialogAdd (final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Insira o Conteudo");

// Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType( InputType.TYPE_CLASS_TEXT ); //| InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
               // Toast.makeText(context, m_Text, Toast.LENGTH_LONG).show();
                nos.get(position).setConteudo(m_Text);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }


    @Override
    public int getItemCount() {
        return this.nos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nome;
        final RecyclerView filhos;
        final ImageView remove;
        final ImageView inserir;
        final View espacamento;
        final ImageView add;

        ViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nome);
            filhos = (RecyclerView) view.findViewById(R.id.filhos);
            remove = (ImageView) view.findViewById(R.id.remove);
            inserir = (ImageView) view.findViewById(R.id.inserir);
            espacamento = (View) view.findViewById(R.id.espacamento);
            add = (ImageView) view.findViewById(R.id.addConteudo);
        }
    }
}
