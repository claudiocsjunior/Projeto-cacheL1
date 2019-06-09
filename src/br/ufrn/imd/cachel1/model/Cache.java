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

    public int buscarValorLinhaVaziaPorConjunto(int conjunto){
        for (Linha linha : this.linhas) {
            if(linha.getBloco().getValor() == -1 && linha.getConjuntoPertencente() == conjunto){
                return linha.getValor();
            }
        }

        return -1;
    }

    public List<Linha> buscarLinhasPorConjunto(int conjunto){
        List<Linha> linhas = new ArrayList<Linha>();

        for (Linha linha : this.getLinhas()) {
            if(linha.getConjuntoPertencente() == conjunto){
                linhas.add(linha);
            }
        }

        return linhas;
    }
}
