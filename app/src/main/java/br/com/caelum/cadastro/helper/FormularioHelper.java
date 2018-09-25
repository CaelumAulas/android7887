package br.com.caelum.cadastro.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private EditText campoEndereco;
    private RatingBar campoNota;


    public FormularioHelper(FormularioActivity activity) {

        this.campoNome = activity.findViewById(R.id.formulario_nome);
        this.campoTelefone = activity.findViewById(R.id.formulario_telefone);
        this.campoEmail = activity.findViewById(R.id.formulario_email);
        this.campoEndereco = activity.findViewById(R.id.formulario_endereco);
        this.campoNota = activity.findViewById(R.id.formulario_nota);

    }

    public Aluno pegaAlunoDoFormulario() {
        Aluno aluno = new Aluno();

        aluno.setNome(campoNome.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota((double) campoNota.getRating());

        return aluno;

    }
}
