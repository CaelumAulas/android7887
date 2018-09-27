package br.com.caelum.cadastro.servicos;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebClient {


    public String buscaMedia(String json) {

        try {
            OkHttpClient client = new OkHttpClient();

            MediaType tipo = MediaType.parse("application/json; charset=utf-8");

            RequestBody corpo = RequestBody.create(tipo, json);

            Request requisicao = new Request.Builder()
                    .url("https://www.caelum.com.br/mobile")
                    .post(corpo)
                    .build();

            Response resposta = client.newCall(requisicao).execute();

            return resposta.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Deu ruim";
        }
    }

}

