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

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

public class ListaProvasFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        ArrayAdapter<Prova> adapter =
                new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1,
                        pegaProvas());

        ListView lista = view.findViewById(R.id.fragment_provas_lista);

        lista.setAdapter(adapter);

        return view;

    }


    private List<Prova> pegaProvas(){

        Prova android = new Prova("Android", "29/09/2018");
        Prova ios = new Prova("IOS", "30/09/2018");

        return Arrays.asList(android, ios);
    }
}
