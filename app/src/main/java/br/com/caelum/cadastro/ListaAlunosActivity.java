package br.com.caelum.cadastro;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemLongClickListener;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        final String[] alunos = {"Aldriano", "Andrea",
                "Heitor", "João", "Gui"};

        final ListView lista = findViewById(R.id.lista_alunos);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);

        lista.setAdapter(adapter);

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

                String nome = (String) adapter.getItemAtPosition(posicao);


                Toast.makeText(self, nome, Toast.LENGTH_SHORT).show();

                return true;
            }
        });


    }
}
