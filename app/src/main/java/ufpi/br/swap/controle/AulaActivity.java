package ufpi.br.swap.controle;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import ufpi.br.swap.R;

public class AulaActivity extends AppCompatActivity {

    private String nomeConhecimento;
    private String descricaoConhecimento;
    private String nomeUsuario;
    private Float ratingConhecimento;
    private TextView descricao;
    private TextView numeroAvaliacao;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula);

        Intent intent = getIntent();
        nomeConhecimento = intent.getStringExtra("nome");
        descricaoConhecimento = intent.getStringExtra("descricao");
        nomeUsuario = intent.getStringExtra("nome_usuario");
        ratingConhecimento = intent.getFloatExtra("rating", 0);

        this.setTitle(nomeConhecimento);

        descricao = (TextView) findViewById(R.id.descricao_aula);
        descricao.setText("Oferecido por: " + nomeUsuario + "\n\n" + descricaoConhecimento);

        numeroAvaliacao = (TextView) findViewById(R.id.avaliacao_aula);
        numeroAvaliacao.setText(Float.toString(ratingConhecimento));

        ratingBar = (RatingBar) findViewById(R.id.ratingConhecimento);
        ratingBar.setRating(ratingConhecimento);
        ratingBar.setIsIndicator(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Você curtiu a aula. Aguarde " + nomeUsuario + " aceitar sua proposta.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}
