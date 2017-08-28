package ufpi.br.swap.controle;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufpi.br.swap.R;

import ufpi.br.swap.entidades.Usuario;
import ufpi.br.swap.servico.RetrofitService;
import ufpi.br.swap.servico.ServiceGenerator;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonEntrar;
    private Button buttonEsqueceuSenha;
    private Button buttonFacebook;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextSenha = (EditText) findViewById(R.id.editText_senha);
        buttonEntrar = (Button) findViewById(R.id.button_entrar);
        buttonEsqueceuSenha = (Button) findViewById(R.id.button_esqueceu_senha);
        buttonFacebook = (Button) findViewById(R.id.button_facebook);
        buttonCadastrar = (Button) findViewById(R.id.button_cadastrar);

        listenerButtons();
    }

    public void listenerButtons() {
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String senha = editTextSenha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.erro_campo_vazio, Toast.LENGTH_LONG).show();
                } else {
                    efetuarLogin(email, senha);
                }
            }
        });
    }

    public void goToEsqueceuSenha(View v) {
        Intent intent = new Intent(this, EsqueceuSenhaActivity.class);
        startActivity(intent);
    }

    public void goToCadastro(View v) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void efetuarLogin(String email, String senha) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<Usuario> call = service.login(email, senha);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();
                    if (usuario.isLogado()) {
                        Toast.makeText(getApplicationContext(), "Nome: " + usuario.getNome() + ". Email: " + usuario.getEmail(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao conectar com servidor", Toast.LENGTH_LONG).show();
            }
        });
    }
}
