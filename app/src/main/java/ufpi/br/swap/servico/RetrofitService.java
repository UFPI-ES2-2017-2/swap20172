package ufpi.br.swap.servico;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import ufpi.br.swap.entidades.Usuario;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("login")
    Call<Usuario> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("signup")
    Call<RespostaServidor> cadastrarUsuario(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );
}
