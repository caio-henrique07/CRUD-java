package com.template;

public class HeroisDTO {

    private int id;
    private String nome;
    private String funcao; // Tank, Dano, Suporte
    private String nacionalidade;
    private int vida;

    // Construtor vazio
    public HeroisDTO() {
    }

    // Construtor completo
    public HeroisDTO(int id, String nome, String funcao, String nacionalidade, int vida) {
        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
        this.nacionalidade = nacionalidade;
        this.vida = vida;
    }

    // Getters e Setters
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}

