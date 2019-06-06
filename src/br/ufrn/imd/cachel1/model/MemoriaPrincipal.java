package br.ufrn.imd.cachel1.model;

import java.util.ArrayList;
import java.util.List;

public class MemoriaPrincipal {
    private List<Bloco> blocos;

    public MemoriaPrincipal(){
        blocos = new ArrayList<Bloco>();
    }

    public List<Bloco> getBlocos() {
        return blocos;
    }

    public void setBlocos(List<Bloco> blocos) {
        this.blocos = blocos;
    }
}
