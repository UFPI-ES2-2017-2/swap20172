package ufpi.br.swap.visao;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ufpi.br.swap.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);
    }

    public void goToEsqueceuSenha(View v) {
        Intent intent = new Intent(this, EsqueceuSenhaActivity.class);
        startActivity(intent);
    }

    public void goToCadastro(View v) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
}
