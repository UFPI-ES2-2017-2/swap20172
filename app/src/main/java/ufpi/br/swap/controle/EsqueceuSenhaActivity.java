package ufpi.br.swap.controle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ufpi.br.swap.R;

/**
 * Classe responsável pelo gerenciamento da tela esqueceu senha.
 */
public class EsqueceuSenhaActivity extends AppCompatActivity {

    /**
     * Método responsável pelo gerenciamento dos componentes da tela esqueceu senha e do
     * tratamento dos eventos ocorridos, por exemplo, um click em um botão.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_esqueceu_senha);
    }
}
