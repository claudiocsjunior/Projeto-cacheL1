package br.ufrn.imd.cachel1.model;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private List<Linha> linhas;
    private List<Integer> ordenarBlocosPorUso;

    public Cache(){
        linhas = new ArrayList<Linha>();
        ordenarBlocosPorUso = new ArrayList<Integer>();
    }

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }


    public List<Integer> getOrdenarBlocosPorUso() {
        return ordenarBlocosPorUso;
    }

    public void setOrdenarBlocosPorUso(List<Integer> ordenarBlocosPorUso) {
        this.ordenarBlocosPorUso = ordenarBlocosPorUso;
    }

    public int buscarValorLinhaVazia(){
        for (Linha linha : this.linhas) {
            if(linha.getBloco().getValor() == -1){
                return linha.getValor();
            }
        }

        return -1;
    }

    public Linha getLinhaPorParametro(int linhaValor){
        for (Linha linha : this.linhas) {
            if(linha.getValor() == linhaValor)
                return  linha;
        }

        return new Linha();
    }
}
