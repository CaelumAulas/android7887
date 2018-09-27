package br.com.caelum.cadastro.servicos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class BuscaMediaTask extends AsyncTask<String, Void, String> {

    private Context contexto;

    public BuscaMediaTask(Context contexto) {
        this.contexto = contexto;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contexto, "To indo pegar os dados", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String doInBackground(String... objects) {

        String json = objects[0];

        WebClient webClient = new WebClient();
        String media = webClient.buscaMedia(json);

        return media;
    }

    @Override
    public void onPostExecute(String media) {

        Toast.makeText(contexto, media, Toast.LENGTH_SHORT).show();

    }
}
