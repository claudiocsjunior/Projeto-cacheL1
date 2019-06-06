package br.ufrn.imd.cachel1.model;

public abstract class Valor {
    protected int valor;

    public Valor(){
        this.valor = 0;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
