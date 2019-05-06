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
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
        }
    }

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
