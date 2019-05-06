package com.example.arvore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class No {
    private int id;
    private String nome;
    private List<No> no = new ArrayList<>();
    private String conteudo;
    private int idPai;


    public No() {
    }

    public No(int id, String nome, String conteudo, int idPai) {
        this.idPai = idPai;
        this.nome = nome;
        this.id = id;
        this.conteudo = conteudo;
    }

    public No(int idPai, String nome, List<No> no) {
        this.idPai = idPai;
        this.nome = nome;
        this.no = no;
        this.conteudo = null;
    }

    public No(int id, String nome, List<No> no, String conteudo) {
        this.id  = new Random().nextInt();
        this.nome = nome;
        this.no = no;
        this.conteudo = conteudo;
    }

    public int getIdPai() {
        return idPai;
    }

    public void setIdPai(int idPai) {
        this.idPai = idPai;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
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
