package ufpi.br.swap.DataModel;

/**
 * Created by negadoaiti on 12/11/17.
 */

public class DataModel {

    public static String criarTabelaAula(){
        String query = "CREATE TABLE AULA";
        query+= " (";
        query+= "ALUNO TEXT,";
        query+= "PROFESSOR TEXT,";
        query+= "DATA TEXT,";
        query+= "LOCAL TEXT,";
        query+= "NOTA TEXT";
        query+= ")";

        return query;
    }
    public static String criarTabelaUsuario(){
        String query = "CREATE TABLE USUARIO";
        query+= " (";
        query+= "NOME TEXT,";
        query+= "EMAIL TEXT,";
        query+= "SENHA TEXT,";
        query+= "LOGADO INT";
        query+= ")";

        return query;
    }
    public static String criarTabelaConhecimento(){
        String query = "CREATE TABLE CONHECIMENTO";
        query+= " (";
        query+= "USER TEXT,";
        query+= "NAME TEXT,";
        query+= "DESCRIPTION TEXT";
        query+= ")";

        return query;
    }
}
