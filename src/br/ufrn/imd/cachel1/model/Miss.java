package br.ufrn.imd.cachel1.model;

public class Miss extends Hit{
    private Bloco blocoSubstituto;
    private Bloco blocoSubstituido;
    private Linha linhaDeSubstituicao;

    public Bloco getBlocoSubstituto() {
        return blocoSubstituto;
    }

    public void setBlocoSubstituto(Bloco blocoSubstituto) {
        this.blocoSubstituto = blocoSubstituto;
    }

    public Bloco getBlocoSubstituido() {
        return blocoSubstituido;
    }

    public void setBlocoSubstituido(Bloco blocoSubstituido) {
        this.blocoSubstituido = blocoSubstituido;
    }

    public Linha getLinhaDeSubstituicao() {
        return linhaDeSubstituicao;
    }

    public void setLinhaDeSubstituicao(Linha linhaDeSubstituicao) {
        this.linhaDeSubstituicao = linhaDeSubstituicao;
    }
}
