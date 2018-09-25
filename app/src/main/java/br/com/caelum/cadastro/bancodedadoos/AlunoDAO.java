package br.com.caelum.cadastro.bancodedadoos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import br.com.caelum.cadastro.modelo.Aluno;

@Dao
public interface AlunoDAO {

    @Insert
    void insere(Aluno aluno);
}
