package br.com.caelum.cadastro.bancodedadoos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

@Dao
public interface AlunoDAO {

    @Insert
    void insere(Aluno aluno);

    @Query("select * from Aluno order by nome")
    List<Aluno> buscaAlunos();
}
