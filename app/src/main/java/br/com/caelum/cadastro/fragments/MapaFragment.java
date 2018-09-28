package br.com.caelum.cadastro.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.caelum.cadastro.application.CadastroApplication;
import br.com.caelum.cadastro.modelo.Aluno;

public class MapaFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                CadastroApplication application = (CadastroApplication) getActivity().getApplication();

                List<Aluno> alunos = application.getAlunoDAO().buscaAlunos();

                Geocoder geocoder = new Geocoder(getContext());
                try {
                    for (Aluno aluno : alunos) {

                        MarkerOptions marcador = criaMarcador(geocoder, aluno);

                        googleMap.addMarker(marcador);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @NonNull
    private MarkerOptions criaMarcador(Geocoder geocoder, Aluno aluno) throws IOException {
        LatLng coordenada = pegaCoordenada(geocoder, aluno);

        MarkerOptions marcador = new MarkerOptions();

        marcador.position(coordenada);
        marcador.title(aluno.getNome());
        marcador.snippet(aluno.getEndereco());
        return marcador;
    }

    @NonNull
    private LatLng pegaCoordenada(Geocoder geocoder, Aluno aluno) throws IOException {
        String endereco = aluno.getEndereco();

        List<Address> addresses = geocoder.getFromLocationName(endereco, 1);

        Address address = addresses.get(0);

        double latitude = address.getLatitude();
        double longitude = address.getLongitude();
        return new LatLng(latitude, longitude);
    }
}
