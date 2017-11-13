package ufpi.br.swap.dados;

import ufpi.br.swap.entidades.Usuario;

/**
 * Interface que identifica os métodos que devem existir nas classes de acesso às entidades.
 * @author edson
 */

public interface IColecaoUsuario {

    /**
     * Busca um usuário na Colecao.
     * @return o usuário procurado.
     */
    public boolean buscarUsuario(Usuario objeto);

    /**
     * Cria um usuário e insere-o na Colecao.
     */
    public boolean criarUsuario(Usuario objeto);

    /**
     * Checa se um usuário já existe na Colecao.
     */
    public void checarUsuario();

    /**
     * Altera atributos de um usuário existente.
     */
    public void atualizarUsuario();

    /**
     * Deleta um usuário da Colecao.
     */
    public void deletarUsuario();
}
