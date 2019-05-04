package com.example.arvore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class No {
    private int id;
    private String nome;
    private List<No> no = new ArrayList<>();

    public No() {
    }

    public No(int id, String nome, List<No> no) {
        this.id  = new Random().nextInt();
        this.nome = nome;
        this.no = no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<No> getNo() {
        return no;
    }

    public void setNo(List<No> no) {
        this.no = no;
    }
}
