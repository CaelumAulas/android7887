package br.com.caelum.cadastro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.bancodedadoos.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

import static android.view.View.OnClickListener;
import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemLongClickListener;

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


        lista.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int posicao, long id) {

                Aluno aluno = (Aluno) adapter.getItemAtPosition(posicao);

                Toast.makeText(self, aluno.getNome(), Toast.LENGTH_SHORT).show();

                return true;
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

    }


    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();

    }


    public void carregaLista() {

        CadastroApplication application =
                (CadastroApplication) getApplication();

        AlunoDAO dao = application.getAlunoDAO();

        List<Aluno> alunos = dao.buscaAlunos();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);


        lista.setAdapter(adapter);

    }
}


