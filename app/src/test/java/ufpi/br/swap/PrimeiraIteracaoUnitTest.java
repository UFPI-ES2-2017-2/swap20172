package ufpi.br.swap;

import android.util.Log;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;
import ufpi.br.swap.controle.CadastroActivity;
import ufpi.br.swap.controle.LoginActivity;
import ufpi.br.swap.servico.MensagemAPI;
import ufpi.br.swap.servico.RetrofitService;
import ufpi.br.swap.servico.ServiceGenerator;

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
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<MensagemAPI> call = service.cadastrarUsuario("João", "email@mail.com", "senha");

        Response<MensagemAPI> response = call.execute();


        assertTrue(response.isSuccessful());
    }

    /**
     * C.U. testado: efetuar login (controle de sessão)
     * @throws Exception
     */

    public void loginTeste() throws Exception{
        CadastroActivity ca = new CadastroActivity();
        LoginActivity la = new LoginActivity();
        boolean status;

        status = la.efetuarLogin("mail@mail.com", "senha");

        assertEquals(true,status);
    }
}
