package br.ufrn.imd.cachel1.controller;

import br.ufrn.imd.cachel1.enumerator.BuscaCacheEnum;
import br.ufrn.imd.cachel1.enumerator.InstrucaoEnum;
import br.ufrn.imd.cachel1.model.Cache;
import br.ufrn.imd.cachel1.model.Memorias;
import br.ufrn.imd.cachel1.view.Impressao;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class InstrucaoController {
    private CacheController controladorCache;
    private MemoriaPrincipalController controladorMemoriaPrincipal;

    public InstrucaoController(){
        controladorCache = new CacheController();
        controladorMemoriaPrincipal = new MemoriaPrincipalController();
    }

    public void executarInstrucao(String[] instrucaoArray, int tipoInstrucao, Memorias memorias){
        if(tipoInstrucao == InstrucaoEnum.SHOW){
            //Executar comando de mostrar memorias
            this.executarInstrucaoShow(memorias);
        }else if(tipoInstrucao == InstrucaoEnum.READ){
            //Buscar bloco na memoria cache hit ou miss
            this.executarInstrucaoRead(memorias, instrucaoArray);

        }else if(tipoInstrucao == InstrucaoEnum.WRITE){
            //Buscar bloco na memoria cache hit ou miss substituindo pelo novo valor
        }
    }

    public int verificarInstrucao(String[] instrucaoArray){
//        Verificar string para determinar o tipo de instrução
        String instrucaoLow = instrucaoArray[0].toLowerCase();
        if(instrucaoLow.equals(InstrucaoEnum.READ_STRING.toLowerCase())){
            if(instrucaoArray.length <= 1){
                return InstrucaoEnum.INVALID_PARAMETERS;
            }
            return InstrucaoEnum.READ;
        }else if(instrucaoLow.equals(InstrucaoEnum.WRITE_STRING.toLowerCase())){
            return InstrucaoEnum.WRITE;
        }else if(instrucaoLow.equals(InstrucaoEnum.SHOW_STRING.toLowerCase())){
            return InstrucaoEnum.SHOW;
        }else{
            return InstrucaoEnum.NOT_FOUND;
        }
    }

    private void executarInstrucaoShow(Memorias memorias){
        controladorCache.executarImpressaoCache(memorias.getMemoriaCache());
        controladorMemoriaPrincipal.executarImpressaoMemoriaPrincipal(memorias.getMemoriaPrincipal());
    }

    private void executarInstrucaoRead(Memorias memorias, String[] instrucaoArray){
        List<Integer> resultadoBusca = controladorCache.buscarEnderecoNaMemoriaCache(memorias.getMemoriaCache(), Integer.parseInt(instrucaoArray[1]));

        Impressao impressao = new Impressao();

        if(resultadoBusca.get(0) == BuscaCacheEnum.HIT){
//            Executa HIT
            impressao.imprimirReadHit(instrucaoArray, resultadoBusca.get(1));
        }else{
//            Executa MISS de acordo com o mapeamento
        }

    }
}
