package br.ufrn.imd.cachel1.model;

public class Linha extends Valor{
    private Bloco bloco;
    private int conjuntoPertencente;

    public int getConjuntoPertencente() {
        return conjuntoPertencente;
    }

    public void setConjuntoPertencente(int conjuntoPertencente) {
        this.conjuntoPertencente = conjuntoPertencente;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

}
