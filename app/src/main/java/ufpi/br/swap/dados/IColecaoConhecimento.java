package ufpi.br.swap.dados;

import ufpi.br.swap.entidades.Conhecimento;

/**
 * Interface para coleção de conhecimentos
 * Created by viniciuspablo on 12/11/17.
 */

public interface IColecaoConhecimento {

    /**
     * Busca conhecimento na coleção
     * @param Texto para busca de conhecimento
     * @return Conhecimento desejado ou Null
     */
    public Conhecimento buscar(String text);

    /**
     * Adiciona um conhecimento na coleção
     * @param conhecimento a ser adicionado na coleção
     */
    public void addConhecimento(Conhecimento conhecimento);

    /**
     * Verifica se o conhecimento existe. Utilizado para inserir novos conhecimentos
     * sem repetir algum que ja exista ou para verificar algum conhecimento antes de removê-lo.
     * @param conhecimento a ser verificado
     */
    public void existeConhecimento(Conhecimento conhecimento);

    /**
     * Remove um conhecimento da coleção
     * @param conhecimento a ser removido
     */
    public void removerConhecimento(Conhecimento conhecimento);

    /**
     * Atualiza um conhecimento ja existente
     * @param antigo é o conhecimento a ser atualizado
     * @param novo é o conhecimento com os novos dados
     */
    public void attConhecimento(Conhecimento antigo, Conhecimento novo);
}
