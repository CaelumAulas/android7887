package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.caelum.cadastro.bancodedadoos.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        helper = new FormularioHelper(this);

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

        alunoDAO.insere(aluno);
    }
}
