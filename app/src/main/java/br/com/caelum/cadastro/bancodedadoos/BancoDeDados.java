package br.com.caelum.cadastro.bancodedadoos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.caelum.cadastro.modelo.Aluno;

@Database(entities = {Aluno.class}, version = 2)
public abstract class BancoDeDados extends RoomDatabase {

    public abstract AlunoDAO getAlunoDAO();
}
