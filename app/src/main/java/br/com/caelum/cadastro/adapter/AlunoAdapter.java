package br.com.caelum.cadastro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoAdapter extends BaseAdapter {

    private List<Aluno> alunos;
    private Activity activity;

    public AlunoAdapter(List<Aluno> alunos, Activity activity) {
        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return alunos.get(posicao);
    }


    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }


    @Override
    public View getView(int posicao, View view,
                        ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();

        View linha = inflater.inflate(R.layout.item_aluno, viewGroup,
                false);

        Aluno aluno = alunos.get(posicao);

        TextView nome = linha.findViewById(R.id.item_aluno_nome);
        TextView email = linha.findViewById(R.id.item_aluno_email);
        ImageView foto = linha.findViewById(R.id.item_aluno_foto);

        nome.setText(aluno.getNome());
        email.setText(aluno.getEmail());


        if (aluno.getCaminhoFoto() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(aluno.getCaminhoFoto());

            foto.setImageBitmap(bitmap);
        }

        return linha;
    }
}
