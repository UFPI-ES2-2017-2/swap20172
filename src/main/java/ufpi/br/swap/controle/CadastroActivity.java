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
import ufpi.br.swap.servico.RespostaServidor;
import ufpi.br.swap.servico.RetrofitService;
import ufpi.br.swap.servico.ServiceGenerator;

public class CadastroActivity extends AppCompatActivity {

    private Button buttonCadastrar;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private EditText editTextSenhaRepetida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_cadastro);

        buttonCadastrar = (Button) findViewById(R.id.button_criar_conta);
        editTextNome = (EditText) findViewById(R.id.editText_nome_cadastro);
        editTextEmail = (EditText) findViewById(R.id.editText_email_cadastro);
        editTextSenha = (EditText) findViewById(R.id.editText_senha_cadastro);
        editTextSenhaRepetida = (EditText) findViewById(R.id.editText_senha_cadastro_repetida);

        listenerButtons();
    }

    private void listenerButtons() {
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                String senha = editTextSenha.getText().toString();
                String senhaRepetida = editTextSenhaRepetida.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senhaRepetida.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.erro_campo_vazio, Toast.LENGTH_LONG).show();
                } else {
                    if (!senha.equals(senhaRepetida)) {
                        Toast.makeText(getApplicationContext(), R.string.erro_senhas_diferentes, Toast.LENGTH_LONG).show();
                    } else {
                        cadastrarUsuario(nome, email, senha);
                    }
                }
            }
        });
    }

    private void cadastrarUsuario(String nome, String email, String senha) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<RespostaServidor> call = service.cadastrarUsuario(nome, email, senha);
        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                if (response.isSuccessful()) {
                    RespostaServidor respostaServidor = response.body();
                    String msg = respostaServidor.getMsg();
                    Boolean success = respostaServidor.getSuccess();

                    if (success) {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        voltarParaTelaLogin();
                    } else {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {

            }
        });
    }

    private void voltarParaTelaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
