package br.com.caelum.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import br.com.caelum.cadastro.bancodedadoos.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.modelo.Aluno;

import static android.view.View.OnClickListener;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();

        if (intent.hasExtra("chave")) {
            Aluno aluno = (Aluno) intent.getSerializableExtra("chave");
            helper.populaOsCamposCom(aluno);
        }




        helper.getBotaoFoto().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent vaiParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                caminhoFoto = getExternalFilesDir("foto") + "/" + System.currentTimeMillis() + ".jpg";
                File arquivo = new File(caminhoFoto);
                Uri localFoto = Uri.fromFile(arquivo);

                vaiParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                startActivity(vaiParaCamera);


            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (caminhoFoto != null) {
            helper.carregaFoto(caminhoFoto);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_formulario, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_formulario_salvar:

                if (helper.alunoEstaValido()) {

                    Aluno aluno = helper.pegaAlunoDoFormulario();

                    salva(aluno);

                    Toast.makeText(this, aluno.getNome(), Toast.LENGTH_SHORT).show();

                    finish();

                } else {
                    helper.mostraErro();
                }
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return false;
        }
    }

    private void salva(Aluno aluno) {
        CadastroApplication application =
                (CadastroApplication) getApplication();

        AlunoDAO alunoDAO = application.getAlunoDAO();

        if (aluno.getId() == null) {
            alunoDAO.insere(aluno);
        } else {
            alunoDAO.atualiza(aluno);
        }
    }
}
