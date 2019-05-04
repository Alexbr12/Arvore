package com.example.arvore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recycleView;
    public List<No> no = new ArrayList<>();
    private NoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        no.add(new No(1, "1", new ArrayList<No>()));
        {
            No no1 = new No(11, "1.1", new ArrayList<No>());
            No no2 = new No(12, "1.2", new ArrayList<No>());
            no.get(0).getNo().add(no1);
            no.get(0).getNo().add(no2);

            {
                No no11 = new No(111, "1.1.1", new ArrayList<No>());
                No no22= new No(112, "1.1.2", new ArrayList<No>());
                no.get(0).getNo().get(0).getNo().add(no11);
                no.get(0).getNo().get(0).getNo().add(no22);
            }
        }

        no.add(new No(2, "2", new ArrayList<No>()));

        {
            No no1 = new No(21, "2.1", new ArrayList<No>());
            No no2 = new No(22, "2.2", new ArrayList<No>());
            No no3 = new No(21, "2.3", new ArrayList<No>());
            No no4 = new No(22, "2.4", new ArrayList<No>());
            no.get(1).getNo().add(no1);
            no.get(1).getNo().add(no2);
            no.get(1).getNo().add(no3);
            no.get(1).getNo().add(no4);
        }

        no.add(new No(3, "3", new ArrayList<No>()));

        {
            No no1 = new No(31, "3.1", new ArrayList<No>());
            No no2 = new No(32, "3.2", new ArrayList<No>());
            no.get(2).getNo().add(no1);
            no.get(2).getNo().add(no2);
        }

        no.add(new No(4, "4", new ArrayList<No>()));
        {
            No no1 = new No(41, "4.1", new ArrayList<No>());
            No no2 = new No(42, "4.2", new ArrayList<No>());
            no.get(3).getNo().add(no1);
            no.get(3).getNo().add(no2);
        }

        no.add(new No(5, "5", new ArrayList<No>()));
        {
            No no1 = new No(51, "5.1", new ArrayList<No>());
            No no2 = new No(52, "5.2", new ArrayList<No>());
            No no3 = new No(53, "5.3", new ArrayList<No>());
            No no4 = new No(54, "5.4", new ArrayList<No>());
            No no5 = new No(55, "5.5", new ArrayList<No>());
            no.get(4).getNo().add(no1);
            no.get(4).getNo().add(no2);
            no.get(4).getNo().add(no3);
            no.get(4).getNo().add(no4);
            no.get(4).getNo().add(no5);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoAdapter(no, this);
        recyclerView.setAdapter(adapter);

  //      percorrerArvore(no);
//        delete(no, 11);

       // adapter.notifyDataSetChanged();


    //    percorrerArvore(no);
    }


     public void percorrerArvore(List<No> raiz) {
        for(int i = 0; i < raiz.size(); i++) {
            Log.i("Nó", raiz.get(i).getNome());
            percorrerArvore(raiz.get(i).getNo());
        }
    }

    public void delete(List<No> raiz, int id) {
        for(int i = 0; i < raiz.size(); i++) {
            No no = raiz.get(i);
            if(no.getId() == id) {
                raiz.remove(no);
                Log.i("DELETE", no.getNome());
                adapter.notifyDataSetChanged();
                percorrerArvore(raiz);
                return;
            }
            delete(no.getNo(), id);
        }
        //return true;
    }

    private void inserir (List<No> raiz, String nome) {


    }

}
