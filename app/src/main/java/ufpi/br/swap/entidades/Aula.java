package ufpi.br.swap.entidades;

import java.util.ArrayList;

/**
 * Representação de uma Aula
 * Created by viniciuspablo on 12/11/17.
 */

public class Aula {

    private String nome;
    private String descricao;
    private ArrayList<String> comentarios;
    private Usuario professor;
    private Usuario aluno;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }
}
