package ufpi.br.swap.controle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufpi.br.swap.entidades.Conhecimento;
import ufpi.br.swap.R;
import ufpi.br.swap.servico.MensagemAPI;
import ufpi.br.swap.servico.RetrofitService;
import ufpi.br.swap.servico.ServiceGenerator;

public class InicialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Conhecimento> listaConhecimentosRecomendados = new ArrayList<>();
    ArrayAdapter<Conhecimento> adapter;

    private TextView userEmailText;
    private TextView userNameText;
    private ListView listaRecomendados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Página Inicial");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nav = navigationView.getHeaderView(0);
        setarTextViews(nav);

        listaRecomendados = (ListView) findViewById(R.id.lista_recomendados);
        buscarConhecimentosRecomendados();
    }

    private void setarTextViews(View nav) {
        userEmailText = (TextView) nav.findViewById(R.id.user_email);
        userNameText = (TextView) nav.findViewById(R.id.user_name);

        Bundle extras = getIntent().getExtras();
        String nome = extras.getString("user_name");
        String email = extras.getString("user_email");
        Log.i("nome", nome);
        Log.i("email", email);
        userEmailText.setText(email);
        userNameText.setText(nome);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_perfil) {

        } else if (id == R.id.nav_saldo) {

        } else if (id == R.id.nav_config) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void buscarConhecimentosRecomendados() {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<List<Conhecimento>> call = service.getConhecimentosRecomendados();
        call.enqueue(new Callback<List<Conhecimento>>() {
            @Override
            public void onResponse(Call<List<Conhecimento>> call, Response<List<Conhecimento>> response) {
                if (response.isSuccessful()) {
                    listaConhecimentosRecomendados = response.body();
                    setarLista();
                }
            }

            @Override
            public void onFailure(Call<List<Conhecimento>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.erro_conectar_servidor, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setarLista() {
        adapter = new ArrayAdapter<Conhecimento>(this, android.R.layout.simple_list_item_1, listaConhecimentosRecomendados);
        listaRecomendados.setAdapter(adapter);
        listaRecomendados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Conhecimento c = listaConhecimentosRecomendados.get(position);
                Intent intent = new Intent(getApplicationContext(), AulaActivity.class);
                intent.putExtra("nome", c.getName());
                intent.putExtra("descricao", c.getDescription());
                intent.putExtra("nome_usuario", c.getUser());
                intent.putExtra("rating", c.getRating());
                startActivity(intent);
            }
        });
    }
}
