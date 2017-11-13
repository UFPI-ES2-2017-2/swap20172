package ufpi.br.swap.servico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ufpi.br.swap.entidades.Conhecimento;
import ufpi.br.swap.entidades.Usuario;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("user/login")
    Call<Usuario> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user/signup")
    Call<MensagemAPI> cadastrarUsuario(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("knowledge/recommended")
    Call<List<Conhecimento>> getConhecimentosRecomendados();

    @FormUrlEncoded
    @POST("knowledge/search")
    Call<List<Conhecimento>> pesquisarConhecimentos(
            @Field("name") String name
    );
}
