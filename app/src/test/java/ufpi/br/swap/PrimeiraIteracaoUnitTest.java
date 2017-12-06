package ufpi.br.swap;

import org.junit.Test;

import ufpi.br.swap.controle.CadastroActivity;
import ufpi.br.swap.controle.LoginActivity;

import static org.junit.Assert.*;

/**
 * Testes Unitários automáticos das funcionalidades da primeira iteração
 * Created by viniciuspablo on 05/12/17.
 */

public class PrimeiraIteracaoUnitTest {

    /**
     * C.U. testado: cadastrar novo usuário
     * @throws Exception
     */
    @Test
    public void cadastrarTeste() throws Exception{
        CadastroActivity ca = new CadastroActivity();

        boolean status;
        status = ca.cadastrarUsuario("João","email@mail.com","senha");

        assertEquals(true,status);
    }

    /**
     * C.U. testado: efetuar login (controle de sessão)
     * @throws Exception
     */
    @Test
    public void loginTeste() throws Exception{
        LoginActivity la = new LoginActivity();
        boolean status;

        status = la.efetuarLogin("mail@mail.com", "senha");

        assertEquals(true,status);
    }
}
