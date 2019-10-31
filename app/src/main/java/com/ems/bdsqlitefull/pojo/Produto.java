package com.ems.bdsqlitefull.pojo;

import java.io.Serializable;

public class Produto implements Serializable {

    private int id;
    private String codBarra;
    private String produto;
    private String descricao;
    private String setor;


    public Produto() {
    }


    public Produto(String codBarra, String produto, String descricao, String setor) {
        this.codBarra = codBarra;
        this.produto = produto;
        this.descricao = descricao;
        this.setor = setor;
    }


    public Produto(int id, String codBarra, String produto, String descricao, String setor) {
        this.id = id;
        this.codBarra = codBarra;
        this.produto = produto;
        this.descricao = descricao;
        this.setor = setor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }


    @Override
    public String toString() {
        return produto;
    }


    public String getDados() {
        return "ID: " + id + "\n" +
                "codBarra: " + codBarra + "\n" +
                "Produto: " + produto + "\n" +
                "Descricao: " + descricao + "\n" +
                "Setor: " + setor;
    }


}
