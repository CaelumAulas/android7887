package br.com.caelum.cadastro.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.caelum.cadastro.application.CadastroApplication;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.adapter.AlunoAdapter;
import br.com.caelum.cadastro.bancodedadoos.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

import static android.view.MenuItem.OnMenuItemClickListener;
import static android.view.View.OnClickListener;
import static android.widget.AdapterView.AdapterContextMenuInfo;
import static android.widget.AdapterView.OnItemClickListener;

public class ListaAlunosActivity extends AppCompatActivity {


    public static final int REQUEST_TELEFONE = 1;
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

                Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);

                Intent edicao = new Intent(self, FormularioActivity.class);

                edicao.putExtra("chave", aluno);

                startActivity(edicao);
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

        AlunoAdapter adapter = new AlunoAdapter(alunos, this);

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


        MenuItem ligar = menu.add("Ligar");
        MenuItem sms = menu.add("SMS");
        MenuItem mapa = menu.add("Ver no Mapa");
        MenuItem deletar = menu.add("Deletar");


        ligar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent fazLigacao = new Intent(Intent.ACTION_CALL);
                fazLigacao.setData(Uri.parse("tel:" + aluno.getTelefone()));


                if (ActivityCompat
                        .checkSelfPermission(ListaAlunosActivity.this,
                                Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {

                    String[] permissoes = {Manifest.permission.CALL_PHONE};

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(permissoes, REQUEST_TELEFONE);
                    }

                    return false;
                }
                startActivity(fazLigacao);
                return false;
            }
        });


        sms.setIntent(vaiParaSMS(aluno));

        mapa.setIntent(vaiParaMapa(aluno));

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

    @NonNull
    private Intent vaiParaMapa(Aluno aluno) {
        Intent vaiParaMapa = new Intent(Intent.ACTION_VIEW);
        String endereco = aluno.getEndereco();
        Uri protocolo = Uri.parse("geo:0,0?q=" + endereco);
        vaiParaMapa.setData(protocolo);
        return vaiParaMapa;
    }

    @NonNull
    private Intent vaiParaSMS(Aluno aluno) {
        String telefone = aluno.getTelefone();
        Intent vaiParaSMS = new Intent(Intent.ACTION_VIEW);
        vaiParaSMS.setData(Uri.parse("sms:" + telefone));
        vaiParaSMS.putExtra("sms_body", "Bla bl BL");
        return vaiParaSMS;
    }


}


