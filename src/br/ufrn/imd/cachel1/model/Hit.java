package br.ufrn.imd.cachel1.model;

public class Hit {
    protected int linha;
    protected Endereco endereco;
    protected String instrucao;
    protected int parametroInstrucao1;
    protected int parametroInstrucao2;

    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public int getParametroInstrucao1() {
        return parametroInstrucao1;
    }

    public void setParametroInstrucao1(int parametroInstrucao1) {
        this.parametroInstrucao1 = parametroInstrucao1;
    }

    public int getParametroInstrucao2() {
        return parametroInstrucao2;
    }

    public void setParametroInstrucao2(int parametroInstrucao2) {
        this.parametroInstrucao2 = parametroInstrucao2;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
