package br.com.caelum.cadastro.application;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

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
                .addMigrations(migracao1Para2())
                .build();

        alunoDAO = bancoDeDados.getAlunoDAO();
    }

    @NonNull
    private Migration migracao1Para2() {
        return new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {

                String sql = "alter table Aluno add column caminhoFoto text;";

                database.execSQL(sql);


            }
        };
    }

    public AlunoDAO getAlunoDAO() {
        return alunoDAO;
    }
}
