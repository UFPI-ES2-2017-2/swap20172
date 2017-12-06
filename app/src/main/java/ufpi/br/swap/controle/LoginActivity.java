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

/**
 * Classe responsável pelo gerenciamento da tela de login.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonEntrar;
    private Button buttonEsqueceuSenha;
    private Button buttonFacebook;
    private Button buttonCadastrar;
    private boolean status = true;

    /**
     * Método responsável pelo gerenciamento dos componentes da tela de login e do
     * tratamento dos eventos ocorridos, por exemplo, um click em um botão.
     * @param savedInstanceState
     */
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

    /**
     * Setter para o atributo valor
     * @param value
     */
    public void setStatus(boolean value){
        this.status = value;
    }

    /**
     * Método responsável pelo gerenciamento e tratamento do evento de click de um botão.
     */
    private void listenerButtons() {
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            /**
             * Método responsável pelo tratamento do evento de click do botão Entrar, chamando o
             * método "efetuarLogin()".
             * @param v
             */
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

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            /**
             * Método responsável pelo tratamento do evento de click do botão Cadastrar, chamando o
             * método "goToCadastro()".
             * @param v
             */
            @Override
            public void onClick(View v) {
                goToCadastro(v);
            }
        });

        buttonEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            /**
             * Método responsável pelo tratamento do evento de click do botão EsqueceuSenha, chamando o
             * método "goToEsqueceuSenha()".
             * @param v
             */
            @Override
            public void onClick(View v) {
                goToEsqueceuSenha(v);
            }
        });
    }

    /**
     * Método responsável por redirecionar para a tela esqueceu senha após ser chamado pelo método
     * onClick() do botão EsqueceuSenha.
     * @param v
     */
    private void goToEsqueceuSenha(View v) {
        Intent intent = new Intent(this, EsqueceuSenhaActivity.class);
        startActivity(intent);
    }

    /**
     * Método responsável por redirecionar para a tela de cadastro de usuário após ser chamado pelo
     * método onClick() do botão Cadastrar.
     * @param v
     */
    private void goToCadastro(View v) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    /**
     * Método (chamado pelo método onClick() do botão Entrar) responsável por realizar o login do usuário e redirecionar para tela inicial caso o
     * login tenha ocorrido com sucesso.
     * @param email
     * @param senha
     * @return this.status
     */
    public boolean efetuarLogin(String email, String senha) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<Usuario> call = service.login(email, senha);
        call.enqueue(new Callback<Usuario>() {
            /**
             * Método responsável por redirecionar para tela inicial caso o login do usuário tenha
             * sido realizado com sucesso. Caso contrário, exibe uma mensagem de erro na tela.
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();
                    if (usuario.getLogged()) {
                        setStatus(true);
                        irParaTelaInicial(usuario);
                    } else {
                        setStatus(false);
                        Toast.makeText(getApplicationContext(), "Usuário inexistente", Toast.LENGTH_LONG).show();
                    }
                }
            }

            /**
             * Método responsável por exibir uma mensagem de erro na tela caso ocorra falha na
             * realização do login.
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                setStatus(false);
                Toast.makeText(getApplicationContext(), R.string.erro_conectar_servidor, Toast.LENGTH_LONG).show();
            }
        });

        return this.status;
    }

    /**
     * Método responsável por redirecionar para tela inicial após o login ter sido realizado
     * com sucesso.
     * @param usuario
     */
    private void irParaTelaInicial(Usuario usuario) {
        Intent intent = new Intent(this, InicialActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user_name", usuario.getName());
        intent.putExtra("user_email", usuario.getEmail());
        startActivity(intent);
    }
}
