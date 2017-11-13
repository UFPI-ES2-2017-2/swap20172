package ufpi.br.swap.dados;

import android.content.ContentValues;

import ufpi.br.swap.DataModel.DataModel;
import ufpi.br.swap.DataSource.DataSource;
import ufpi.br.swap.entidades.Usuario;

/**
 * Classe que implementa os métodos da interface IColecaoUsuario.
 * @author edson
 */

public class ColecaoUsuario implements IColecaoUsuario {
    DataSource ds;
    ContentValues values;
    /**
     * Busca um usuário na Colecao.
     * @return o usuário procurado.
     */
    @Override
    public boolean buscarUsuario(Usuario objeto) {
        values = new ContentValues();
        values.put("email",objeto.getEmail());
        values.put("nome",objeto.getNome());
        values.put("senha",objeto.getSenha());
        values.put("logado",objeto.getLogado());
        ds.inserirUsuario(values,"USUARIO");
        return true;
    }

    /**
     * Cria um usuário e insere-o na Colecao.
     */
    @Override
    public boolean criarUsuario(Usuario objeto) {
        values = new ContentValues();
        values.put("email",objeto.getEmail());
        values.put("nome",objeto.getNome());
        values.put("senha",objeto.getSenha());
        values.put("logado",objeto.getLogado());
        ds.recuperarUsuario("USUARIO");
        return true;
    }

    /**
     * Checa se um usuário já existe na Colecao.
     */
    @Override
    public void checarUsuario() {

    }

    /**
     * Altera atributos de um usuário existente.
     */
    @Override
    public void atualizarUsuario() {

    }

    /**
     * Deleta um usuário da Colecao.
     */
    @Override
    public void deletarUsuario() {

    }
}
