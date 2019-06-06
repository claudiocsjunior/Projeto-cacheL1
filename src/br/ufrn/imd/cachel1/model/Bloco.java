package br.ufrn.imd.cachel1.model;

import java.util.ArrayList;
import java.util.List;

public class Bloco extends Valor{
    private List<Endereco> enderecos;

    public Bloco (){
        enderecos = new ArrayList<Endereco>();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
