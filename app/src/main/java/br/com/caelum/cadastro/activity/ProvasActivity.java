package br.com.caelum.cadastro.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.fragments.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragments.ListaProvasFragment;
import br.com.caelum.cadastro.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_provas);

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        if (isLand()) {
            transaction.replace(R.id.provas_frame_esquerda, new ListaProvasFragment());
            transaction.replace(R.id.provas_frame_direita, new DetalhesProvaFragment());

        } else {

            transaction.replace(R.id.provas_frame, new ListaProvasFragment());
        }

        transaction.commit();


    }

    private boolean isLand() {
        return getResources().getBoolean(R.bool.land);
    }

    public void selecionaProva(Prova provaSelecionada) {
        FragmentManager manager = getSupportFragmentManager();

        if (isLand()) {

            DetalhesProvaFragment detalhes = (DetalhesProvaFragment) manager.findFragmentById(R.id.provas_frame_direita);

            detalhes.populaCampos(provaSelecionada);

        } else {

            FragmentTransaction transaction = manager.beginTransaction();

            transaction.replace(R.id.provas_frame, DetalhesProvaFragment.com(provaSelecionada));

            transaction.addToBackStack(null);

            transaction.commit();

        }
    }
}
