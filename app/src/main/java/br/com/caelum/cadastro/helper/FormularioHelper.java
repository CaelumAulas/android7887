package br.com.caelum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {

    private FloatingActionButton botaoFoto;
    private TextInputLayout tilNome;
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private EditText campoEndereco;
    private RatingBar campoNota;
    private ImageView foto;

    private Aluno aluno;


    public FormularioHelper(FormularioActivity activity) {

        this.aluno = new Aluno();

        this.botaoFoto = activity.findViewById(R.id.formulario_botao_foto);
        this.foto = activity.findViewById(R.id.formulario_foto);
        this.tilNome = activity.findViewById(R.id.formulario_nome_til);
        this.campoNome = activity.findViewById(R.id.formulario_nome);
        this.campoTelefone = activity.findViewById(R.id.formulario_telefone);
        this.campoEmail = activity.findViewById(R.id.formulario_email);
        this.campoEndereco = activity.findViewById(R.id.formulario_endereco);
        this.campoNota = activity.findViewById(R.id.formulario_nota);

    }

    public Aluno pegaAlunoDoFormulario() {

        aluno.setNome(campoNome.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota((double) campoNota.getRating());

        return aluno;

    }


    public void carregaFoto(String caminhoFoto) {

        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);

        Bitmap bitmapTratado = Bitmap.createScaledBitmap(bitmap,
                bitmap.getWidth(), bitmap.getHeight(), true);

        foto.setImageBitmap(bitmapTratado);

    }


    public boolean alunoEstaValido() {

        String nome = campoNome.getText().toString();

        return !nome.trim().isEmpty();
    }


    public void mostraErro() {
        tilNome.setError("Campo obrigatório");
    }

    public void populaOsCamposCom(Aluno aluno) {

        this.aluno = aluno;

        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoNota.setRating(aluno.getNota().floatValue());
    }

    public FloatingActionButton getBotaoFoto() {
        return botaoFoto;
    }
}
