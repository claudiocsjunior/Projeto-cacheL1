package br.ufrn.imd.cachel1.util;

public class Configuracao {
    private int tamanhoBlocos;
    private int linhasCache;
    private int blocosMemoriaPrincipal;
    private int mapeamento;
    private int numeroConjuntos;
    private int politicaSubstituicao;

    public Configuracao(int tamanhoBlocos, int linhasCache,
                        int blocosMemoriaPrincipal, int mapeamento,
                        int numeroConjuntos, int politicaSubstituicao){
        this.tamanhoBlocos = tamanhoBlocos;
        this.linhasCache = linhasCache;
        this.blocosMemoriaPrincipal = blocosMemoriaPrincipal;
        this.mapeamento = mapeamento;
        this.numeroConjuntos = numeroConjuntos;
        this.politicaSubstituicao = politicaSubstituicao;

    }

    public int getTamanhoBlocos() {
        return tamanhoBlocos;
    }

    public void setTamanhoBlocos(int tamanhoBlocos) {
        this.tamanhoBlocos = tamanhoBlocos;
    }

    public int getLinhasCache() {
        return linhasCache;
    }

    public void setLinhasCache(int linhasCache) {
        this.linhasCache = linhasCache;
    }

    public int getBlocosMemoriaPrincipal() {
        return blocosMemoriaPrincipal;
    }

    public void setBlocosMemoriaPrincipal(int blocosMemoriaPrincipal) {
        this.blocosMemoriaPrincipal = blocosMemoriaPrincipal;
    }

    public int getMapeamento() {
        return mapeamento;
    }

    public void setMapeamento(int mapeamento) {
        this.mapeamento = mapeamento;
    }

    public int getNumeroConjuntos() {
        return numeroConjuntos;
    }

    public void setNumeroConjuntos(int numeroConjuntos) {
        this.numeroConjuntos = numeroConjuntos;
    }

    public int getPoliticaSubstituicao() {
        return politicaSubstituicao;
    }

    public void setPoliticaSubstituicao(int politicaSubstituicao) {
        this.politicaSubstituicao = politicaSubstituicao;
    }
}
