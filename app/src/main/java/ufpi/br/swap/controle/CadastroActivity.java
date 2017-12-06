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
import ufpi.br.swap.servico.MensagemAPI;
import ufpi.br.swap.servico.RetrofitService;
import ufpi.br.swap.servico.ServiceGenerator;

/**
 * Classe responsável pelo gerenciamento da tela de cadastro de usuário
 */
public class CadastroActivity extends AppCompatActivity {

    private Button buttonCadastrar;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private EditText editTextSenhaRepetida;
    private boolean status = true;

    /**
     * Método responsável pelo gerenciamento dos componentes da tela de cadastro de usuário e do
     * tratamento dos eventos ocorridos, por exemplo, um click em um botão.
     * @param savedInstanceState
     */
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

    /**
     * Seta o valor do atributo status
     * @param value
     */
    private void setStatus(boolean value){
        this.status = value;
    }

    /**
     * Método responsável pelo gerenciamento e tratamento do evento de click de um botão.
     */
    private void listenerButtons() {
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            /**
             * Método responsável pelo tratamento do evento de click do botão Cadastrar, chamando
             * o método "cadastrarUsuario()".
             * @param v
             */
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

    /**
     * Método responsável pelo cadastro de um novo usuário após ser chamado pelo método onClick() do botão Cadastrar.
     * @param nome
     * @param email
     * @param senha
     * @return this.status
     */
    public boolean cadastrarUsuario(String nome, String email, String senha) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<MensagemAPI> call = service.cadastrarUsuario(nome, email, senha);
        call.enqueue(new Callback<MensagemAPI>() {
            /**
             * Método responsável por tentar realizar o cadastro de um novo usuário. Caso o cadastro
             * seja realizado com sucesso, uma mensagem é exibida na tela e logo após, um
             * redirecionamento para a tela de login é feito. Caso contrário, uma mensagem de erro é
             * exibida.
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<MensagemAPI> call, Response<MensagemAPI> response) {
                if (response.isSuccessful()) {
                    MensagemAPI mensagemAPI = response.body();
                    String msg = mensagemAPI.getMsg();
                    Boolean success = mensagemAPI.getSuccess();

                    if (success) {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        voltarParaTelaLogin();
                        setStatus(true);
                    } else {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        setStatus(false);
                    }
                }
            }

            /**
             * Método responsável por mostrar uma mensagem de erro na tela no caso de falha ao
             * tentar realizar o cadastro.
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<MensagemAPI> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.erro_conectar_servidor, Toast.LENGTH_LONG).show();
                setStatus(false);
            }
        });

        return this.status;
    }

    /**
     * Método responsável por redirecionar para tela de login após o cadastro ter sido realizado
     * com sucesso.
     */
    private void voltarParaTelaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
