package ufpi.br.swap;

import android.support.annotation.NonNull;
import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Response;
import ufpi.br.swap.controle.CadastroActivity;
import ufpi.br.swap.controle.LoginActivity;
import ufpi.br.swap.entidades.Conhecimento;
import ufpi.br.swap.entidades.Usuario;
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
     * C.U. testado: efetuar login (controle de sessão) corretamente
     * @throws Exception
     */
    @Test
    public void loginTesteSucesso() throws Exception{
        RetrofitService service1 = ServiceGenerator.createService(RetrofitService.class);
        Call<MensagemAPI> call1 = service1.cadastrarUsuario("Pablo", "mail@mail.com", "senha");
        Response<MensagemAPI> response = call1.execute();


        //Teste com usuário e senha corretos
        RetrofitService service2 = ServiceGenerator.createService(RetrofitService.class);
        Call<Usuario> call2 = service2.login("mail@mail.com", "senha");
        Response<Usuario> response2 = call2.execute();
        assertTrue(response2.body().getLogado());


    }

    /**
     * C.U. testado: efetuar login (controle de sessão) com senha errada
     * @throws Exception
     */
    @Test
    public void loginTesteSenhaErrada() throws Exception{
        RetrofitService service1 = ServiceGenerator.createService(RetrofitService.class);
        Call<MensagemAPI> call1 = service1.cadastrarUsuario("Pablo", "mail@mail.com", "senha");
        Response<MensagemAPI> response = call1.execute();

        //Teste com senha incorreta
        RetrofitService service3 = ServiceGenerator.createService(RetrofitService.class);
        Call<Usuario> call3 = service3.login("mail@mail.com", "pass");
        Response<Usuario> response3 = call3.execute();
        assertFalse(response3.body().getLogado());

    }

    /**
     * C.U. testado: efetuar login (controle de sessão) com email inexistente
     * @throws Exception
     */
    @Test
    public void loginTesteUsuarioInexistente() throws Exception{
        RetrofitService service1 = ServiceGenerator.createService(RetrofitService.class);
        Call<MensagemAPI> call1 = service1.cadastrarUsuario("Pablo", "mail@mail.com", "senha");
        Response<MensagemAPI> response = call1.execute();

        //Teste com email incorreto
        RetrofitService service4 = ServiceGenerator.createService(RetrofitService.class);
        Call<Usuario> call4 = service4.login("new_email@mail.com", "senha");
        Response<Usuario> response4 = call4.execute();
        assertFalse(response4.body().getLogado());
    }

    @Test
    public void pesquisaConhecimentoTeste() throws Exception{
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<List<Conhecimento>> call = service.pesquisarConhecimentos("h");
        Response<List<Conhecimento>> response = call.execute();

        Conhecimento c1 = new Conhecimento();
        c1.setName("Hacker");
        c1.setUser("Marcos Joshoa");
        c1.setDescription("Conhecimentos avançados em Hasckeragem");
        c1.setRating(5.0);

        Conhecimento c2 = new Conhecimento();
        c2.setName("HTML5");
        c2.setUser("Kássio Venícios");
        c2.setDescription("Conhecimentos avançados em HTML5");
        c2.setRating(5.0);



        assertEquals(2,response.body().size());
        assertEquals(c1.getName(),response.body().get(0).getName());
        assertEquals(c2.getName(), response.body().get(1).getName());

    }
}
