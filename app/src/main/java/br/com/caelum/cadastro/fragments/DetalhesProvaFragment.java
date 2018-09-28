package br.com.caelum.cadastro.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

public class DetalhesProvaFragment extends Fragment {


    private TextView data;
    private TextView materia;
    private ListView topicos;

    public static Fragment com(Prova provaSelecionada) {
        DetalhesProvaFragment detalhes = new DetalhesProvaFragment();

        Bundle arguments = new Bundle();

        arguments.putSerializable("prova", provaSelecionada);

        detalhes.setArguments(arguments);

        return detalhes;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalhes_prova,
                container, false);

        data = view.findViewById(R.id.detalhe_prova_data);
        materia = view.findViewById(R.id.detalhe_prova_materia);
        topicos = view.findViewById(R.id.detalhe_prova_topicos);

        Bundle arguments = getArguments();

        if (arguments != null) {
            Prova prova = (Prova) arguments.getSerializable("prova");

            populaCampos(prova);
        }

        return view;
    }

    public void populaCampos(Prova prova) {

        materia.setText(prova.getMateria());
        data.setText(prova.getData());

        topicos.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                prova.getConteudos()));
    }
}
