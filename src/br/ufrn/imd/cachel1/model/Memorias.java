package br.ufrn.imd.cachel1.model;

import br.ufrn.imd.cachel1.controller.CacheController;
import br.ufrn.imd.cachel1.controller.MemoriaPrincipalController;
import br.ufrn.imd.cachel1.util.Configuracao;

public class Memorias {
    private MemoriaPrincipal memoriaPrincipal;
    private Cache memoriaCache;

    public Memorias(){
//        Criando memória principal
        memoriaPrincipal = new MemoriaPrincipal();
//        Criando memória cache
        memoriaCache = new Cache();

        this.aplicarConfiguracoesNasMemorias();
    }

    private void aplicarConfiguracoesNasMemorias(){
//        Criando controlador de memória principal
        MemoriaPrincipalController controladorMemoria = new MemoriaPrincipalController();

//        Preencher memória principal
        controladorMemoria.preencherMemoriaPrincipal(memoriaPrincipal);

//        Criar controlador de cache
        CacheController controladorCache = new CacheController();

//        Preencher memória de Cache com valores vazios
        controladorCache.preencherMemoriaCache(memoriaCache);

//        Inserir conjunto nas linhas as quais elas pertencem
        controladorCache.inserirConjuntosNasLinhas(memoriaCache);

    }

    public MemoriaPrincipal getMemoriaPrincipal() {
        return memoriaPrincipal;
    }

    public Cache getMemoriaCache() {
        return memoriaCache;
    }
}
