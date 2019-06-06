package br.ufrn.imd.cachel1.model;

public class Endereco extends Valor{
    private int conteudo;

    public Endereco(){
        this.conteudo = 0;
    }

    public int getConteudo() {
        return conteudo;
    }

    public void setConteudo(int conteudo) {
        this.conteudo = conteudo;
    }
}
