package ufpi.br.swap.controle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufpi.br.swap.entidades.Conhecimento;
import ufpi.br.swap.R;
import ufpi.br.swap.entidades.Usuario;
import ufpi.br.swap.servico.RetrofitService;
import ufpi.br.swap.servico.ServiceGenerator;

/**
 * Classe responsável pelo gerenciamento da tela inicial.
 */
public class InicialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Conhecimento> listaConhecimentosRecomendados = new ArrayList<>();
    List<Usuario> listaPesquisaUsuarios = new ArrayList<>();
    ArrayAdapter<Conhecimento> adapterConhecimentos;
    ArrayAdapter<Usuario> adapterUsuarios;

    private TextView userEmailText;
    private TextView userNameText;
    private ListView listaRecomendados;
    private SearchView searchView;
    private TextView labelConhecimentos;
    private RadioGroup radioGroup;
    private RadioButton radioButtonConhecimentos;
    private RadioButton radioButtonUsuarios;

    /**
     * Método responsável pelo gerenciamento dos componentes da tela inicial e do
     * tratamento dos eventos ocorridos, por exemplo, um click em um botão.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Página Inicial");

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.check(R.id.radioButton_conhecimentos);
        radioButtonConhecimentos = (RadioButton) findViewById(R.id.radioButton_conhecimentos);
        radioButtonUsuarios = (RadioButton) findViewById(R.id.radioButton_usuarios);

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

        labelConhecimentos = (TextView) findViewById(R.id.textView_recomendados);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Pesquisar");
        eventoPesquisa();
    }

    /**
     * Método responsável por preencher os campos com dados do usuário logado na aplicação.
     * @param nav
     */
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

    /**
     * Método responsável por tratar o evento de click no botão back do dispositivo.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Método responsável por tratar o evento de click de um MenuItem, alterando o conteúdo da tela
     * de acordo com o item clicado.
     * @param item
     * @return true
     */
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

    /**
     * Método responsável por buscar os conhecimentos recomendados do usuário logado e atualizar a
     * lista de conhecimentos recomendados.
     */
    private void buscarConhecimentosRecomendados() {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<List<Conhecimento>> call = service.getConhecimentosRecomendados();
        call.enqueue(new Callback<List<Conhecimento>>() {
            /**
             * Método responsável por atualizar a lista de conhecimentos recomendados caso a busca
             * tenha sido realizada com sucesso.
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<List<Conhecimento>> call, Response<List<Conhecimento>> response) {
                if (response.isSuccessful()) {
                    listaConhecimentosRecomendados = response.body();
                    setarLista();
                }
            }

            /**
             * Método responsável por exibir uma mensagem de erro caso a busca não tenha sido
             * realizada com sucesso.
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<List<Conhecimento>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.erro_conectar_servidor, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Método responsável por fazer o gerenciamento dos componentes e o tratamento dos eventos
     * relacionados ao campo de pesquisa.
     */
    private void eventoPesquisa() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * Método responsável por realizar a pesquisa de conhecimentos e usuários.
             * @param query
             * @return false
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                labelConhecimentos.setText("Resultados");
                if(radioButtonConhecimentos.isChecked()) {
                    pesquisarConhecimentos(query);
                } else if(radioButtonUsuarios.isChecked()) {
                    pesquisarUsuarios(query);
                }
                return false;
            }

            /**
             * Método responsável por pesquisar novamente caso ocorra mudança no conteudo da
             * pesquisa.
             * @param newText
             * @return false
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                labelConhecimentos.setText("Resultados");
                if(radioButtonConhecimentos.isChecked()) {
                    pesquisarConhecimentos(newText);
                } else if(radioButtonUsuarios.isChecked()) {
                    pesquisarUsuarios(newText);
                }
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            /**
             * Método responsável por mudar a "labelConhecimentos" e buscar os conhecimentos
             * recomendados após a pesquisa ser encerrada.
             * @return false
             */
            @Override
            public boolean onClose() {
                buscarConhecimentosRecomendados();
                labelConhecimentos.setText("Recomendados");
                return false;
            }
        });
    }

    /**
     * Método responsável por realizar a pesquisa de conhecimentos e atualizar a lista de
     * conhecimentos recomendados.
     * @param query
     */
    private void pesquisarConhecimentos(String query) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<List<Conhecimento>> call = service.pesquisarConhecimentos(query);
        call.enqueue(new Callback<List<Conhecimento>>() {
            /**
             * Método responsável por atualizar a lista de conhecimentos recomendados caso a
             * pesquisa tenha sido realizada com sucesso.
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<List<Conhecimento>> call, Response<List<Conhecimento>> response) {
                if (response.isSuccessful()) {
                    listaConhecimentosRecomendados = response.body();
                    setarLista();
                }
            }

            /**
             * Método responsável por exibir uma mensagem de erro caso a pesquisa não tenha sido
             * realizada com sucesso.
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<List<Conhecimento>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.erro_conectar_servidor, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Método responsável por realizar a pesquisa de usuário e atualizar a "listaPesquisaUsuarios" .
     * @param query
     */
    private void pesquisarUsuarios(String query) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<List<Usuario>> call = service.pesquisarUsuarios(query);
        call.enqueue(new Callback<List<Usuario>>() {
            /**
             * Método responsável por atualizar a "listaPesquisaUsuarios" caso a
             * pesquisa tenha sido realizada com sucesso.
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    listaPesquisaUsuarios = response.body();
                    setarLista();
                }
            }

            /**
             *  Método responsável por exibir uma mensagem de erro caso a pesquisa não tenha sido
             * realizada com sucesso.
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.erro_conectar_servidor, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Método responsável por atualizar a lista que será exibida na tela de acordo com o que foi
     * retornado pela pesquisa.
     */
    private void setarLista() {
        if(radioButtonConhecimentos.isChecked()) {
            adapterConhecimentos = new ArrayAdapter<Conhecimento>(this, android.R.layout.simple_list_item_1, listaConhecimentosRecomendados);
            listaRecomendados.setAdapter(adapterConhecimentos);
        } else if(radioButtonUsuarios.isChecked()) {
            adapterUsuarios = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, listaPesquisaUsuarios);
            listaRecomendados.setAdapter(adapterUsuarios);
        } else {

        }

        listaRecomendados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Método responsável por fazer o tratamento do evento de click em um dos itens da
             * lista, redirecionando para a tela de vizualização de aula correspondente.
             * @param parent
             * @param view
             * @param position
             * @param id
             */
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


