package br.com.caelum.cadastro.modelo;

import java.util.ArrayList;
import java.util.List;

public class Prova {

    private String materia;
    private String data;
    private List<String> conteudos = new ArrayList<>();

    public Prova(String materia, String data) {
        this.materia = materia;
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<String> conteudos) {
        this.conteudos = conteudos;
    }

    @Override
    public String toString() {
        return materia;
    }
}
