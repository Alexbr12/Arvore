package com.example.arvore;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arvore.ui.conteudo.ConteudoFragment;

public class Conteudo extends AppCompatActivity {
    DBHelper myDB;
    String texto;
    String conteudo;
    TextView txtConteudo;
    Button btnAdd;


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TextView getTxtConteudo() {
        return txtConteudo;
    }

    public void setTxtConteudo(EditText txtConteudo) {
        this.txtConteudo = txtConteudo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conteudo__activity);

        myDB =new DBHelper(this);
        txtConteudo = (TextView)findViewById(R.id.message);

        texto = getIntent().getExtras().getString("nome");
        conteudo = getIntent().getExtras().getString("conteudo");

        if (savedInstanceState == null) {
            ConteudoFragment fragment = ConteudoFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("nome", texto);
            bundle.putString("conteudo", conteudo);
            fragment.setArguments(bundle);
            //fragment.setArguments();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
        }

//        AddData();
//        viewAll();
//        deleteData();
    }

//    private void AddData() {
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isInserted = myDB.insertData(txtConteudo.getText().toString());
//                if (isInserted == true)
//                    Toast.makeText(Conteudo.this, "Dados Inseridos.", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(Conteudo.this, "Falha na Inserção!", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void viewAll () {
//        btnViewAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Cursor res = myDB.getAllData();
//                if (res.getCount() == 0) {
//                    showMessage("Error", "Nenhum dado encontrado");
//                    return;
//                }
//
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()) {
//                    buffer.append("Id: " + res.getString(0)+ "\n");
//                    buffer.append("Texto: " + res.getString(1)+ "\n\n");
//                    buffer.append("Email: " + res.getString(2)+ "\n\n");
//                }
//                showMessage("Data", buffer.toString());
//            }
//        });
//    }
//
//    private void deleteData () {
//        btnDelete.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Integer deleteRows = myDB.deleteData(txtId.getText().toString());
//                        if(deleteRows > 0)
//                            Toast.makeText(Conteudo.this, "Conteudo deletado", Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(Conteudo.this, "Conteudo não pode ser deletado", Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//
//    }

    private void showMessage (String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void show (View v){
        Snackbar.make(v, "Replace with your own detail action detail", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
