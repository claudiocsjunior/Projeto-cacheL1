package br.ufrn.imd.cachel1.model;

import java.util.ArrayList;
import java.util.List;

public class Bloco extends Valor{
    private List<Endereco> enderecos;
    private int uso;

    public Bloco (){
        enderecos = new ArrayList<Endereco>();
        uso = 0;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public boolean existeEndereco(int enderecoValor){
        for (Endereco endereco : this.getEnderecos()) {
            if(endereco.getValor() == enderecoValor){
                return true;
            }
        }

        return false;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }

    public Endereco getEnderecoPorParametro(int valorEndereco){
        for (Endereco endereco : this.enderecos) {
            if(endereco.getValor() == valorEndereco){
                return endereco;
            }
        }

        return new Endereco();
    }
}
