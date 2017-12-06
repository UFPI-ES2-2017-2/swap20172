package ufpi.br.swap;

import org.junit.Test;

import ufpi.br.swap.controle.CadastroActivity;

import static org.junit.Assert.*;

/**
 * Testes Unitários automáticos das funcionalidades da primeira iteração
 * Created by viniciuspablo on 05/12/17.
 */

public class PrimeiraIteracaoUnitTest {

    /**
     * CU testado: cadastrar novo usuário
     * @throws Exception
     */
    @Test
    public void cadastrarTeste() throws Exception{
        CadastroActivity ca = new CadastroActivity();

        boolean status;
        status = ca.cadastrarUsuario("João","email@mail.com","senha");

        assertEquals(true,status);
    }
}
