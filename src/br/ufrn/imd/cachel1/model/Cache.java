package br.ufrn.imd.cachel1.model;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private List<Linha> linhas;

    public Cache(){
        linhas = new ArrayList<Linha>();
    }

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }
}
