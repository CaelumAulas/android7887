package br.com.caelum.cadastro;

import android.app.Application;
import android.arch.persistence.room.Room;

import br.com.caelum.cadastro.bancodedadoos.AlunoDAO;
import br.com.caelum.cadastro.bancodedadoos.BancoDeDados;

public class CadastroApplication extends Application {

    private AlunoDAO alunoDAO;

    @Override
    public void onCreate() {
        super.onCreate();
        BancoDeDados bancoDeDados = Room
                .databaseBuilder(this,
                        BancoDeDados.class,
                        "Cadastro")
                .allowMainThreadQueries()
                .build();

        alunoDAO = bancoDeDados.getAlunoDAO();
    }

    public AlunoDAO getAlunoDAO() {
        return alunoDAO;
    }
}
