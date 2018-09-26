package br.com.caelum.cadastro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.bancodedadoos.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

import static android.view.MenuItem.OnMenuItemClickListener;
import static android.view.View.OnClickListener;
import static android.widget.AdapterView.AdapterContextMenuInfo;
import static android.widget.AdapterView.OnItemClickListener;

public class ListaAlunosActivity extends AppCompatActivity {


    private ListView lista;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        this.lista = findViewById(R.id.lista_alunos);

        final Context self = this;

        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int posicao, long id) {

                Toast.makeText(self, "Posicao clicada " + posicao, Toast.LENGTH_SHORT).show();
            }
        });




        FloatingActionButton botaoAdicionar = findViewById(R.id.adicionar);

        botaoAdicionar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent formulario = new Intent(self, FormularioActivity.class);
                startActivity(formulario);
            }
        });

        registerForContextMenu(lista);

    }


    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();

    }


    public void carregaLista() {

        AlunoDAO dao = getAlunoDAO();

        List<Aluno> alunos = dao.buscaAlunos();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);


        lista.setAdapter(adapter);

    }

    private AlunoDAO getAlunoDAO() {
        CadastroApplication application =
                (CadastroApplication) getApplication();

        return application.getAlunoDAO();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenuInfo menuInfo) {


        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

        int posicao = info.position;

        final Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);


        MenuItem deletar = menu.add("Deletar");
        MenuItem ligar = menu.add("Ligar");
        MenuItem sms = menu.add("SMS");

        deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {


                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setMessage("Quer mesmo deletar ? ")
                        .setTitle("Atenção")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                AlunoDAO dao = getAlunoDAO();

                                dao.deleta(aluno);

                                carregaLista();

                            }
                        })
                        .setNegativeButton("Não", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


                return false;
            }
        });

    }


}


