package br.ufrn.imd.cachel1.controller;

import br.ufrn.imd.cachel1.enumerator.BuscaCacheEnum;
import br.ufrn.imd.cachel1.enumerator.InstrucaoEnum;
import br.ufrn.imd.cachel1.enumerator.MapeamentoEnum;
import br.ufrn.imd.cachel1.model.*;
import br.ufrn.imd.cachel1.util.Configuracao;
import br.ufrn.imd.cachel1.view.Impressao;

import java.util.List;

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
            this.executarInstrucaoWrite(memorias, instrucaoArray);
        }
    }

    public int verificarInstrucao(String[] instrucaoArray){
//        Verificar string para determinar o tipo de instrução
        String instrucaoLow = instrucaoArray[0].toLowerCase();
        if(instrucaoLow.equals(InstrucaoEnum.READ_STRING.toLowerCase())){
            if(instrucaoArray.length != 2){
                return InstrucaoEnum.INVALID_PARAMETERS;
            }
            return InstrucaoEnum.READ;
        }else if(instrucaoLow.equals(InstrucaoEnum.WRITE_STRING.toLowerCase())){
            if(instrucaoArray.length != 3){
                return InstrucaoEnum.INVALID_PARAMETERS;
            }
            return InstrucaoEnum.WRITE;
        }else if(instrucaoLow.equals(InstrucaoEnum.SHOW_STRING.toLowerCase())){
            if(instrucaoArray.length != 1){
                return InstrucaoEnum.INVALID_PARAMETERS;
            }
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

        int blocoNovoValorUso = 0;
        if(resultadoBusca.get(0) == BuscaCacheEnum.HIT){
            Hit hit = new Hit();
            hit.setInstrucao(instrucaoArray[0]);
            hit.setParametroInstrucao1(Integer.parseInt(instrucaoArray[1]));
            hit.setLinha(resultadoBusca.get(1));

            blocoNovoValorUso = memorias.getMemoriaCache().getLinhaPorParametro(hit.getLinha()).getBloco().getUso() + 1;
            memorias.getMemoriaCache().getLinhaPorParametro(hit.getLinha()).getBloco().setUso(blocoNovoValorUso);

//            Executa HIT
            impressao.imprimirReadHit(hit);
        }else{
//            Executa MISS de acordo com o mapeamento
            Miss miss = new Miss();

//        Buscar bloco que contem o endereco
            Bloco blocoMemoriaPrincipal = controladorMemoriaPrincipal.buscarBlocoNaMemoria(memorias.getMemoriaPrincipal(), Integer.parseInt(instrucaoArray[1]));
            blocoNovoValorUso = blocoMemoriaPrincipal.getUso()+1;
            blocoMemoriaPrincipal.setUso(blocoNovoValorUso);

//            Verificar mapeamento da configuração
            if(Configuracao.MAPEAMENTO == MapeamentoEnum.MAPEAMENTO_DIRETO){
                miss = controladorCache.executarTrocaMapeamentoDireto(memorias, Integer.parseInt(instrucaoArray[1]), blocoMemoriaPrincipal);
            }else if(Configuracao.MAPEAMENTO == MapeamentoEnum.TOTALMENTE_ASSOCIATIVO){
                miss = controladorCache.executarTrocaMapeamentoTotalmenteAssociativo(memorias, Integer.parseInt(instrucaoArray[1]), blocoMemoriaPrincipal);
            }else if(Configuracao.MAPEAMENTO == MapeamentoEnum.PARCIALMENTE_ASSOCIATIVO){
                miss = controladorCache.executarTrocaMapeamentoParcialmenteAssociativo(memorias, Integer.parseInt(instrucaoArray[1]), blocoMemoriaPrincipal);
            }

//            Adiciona no final da lista de ordenação o valor do bloco que irá substituir o outro
            memorias.getMemoriaCache().getOrdenarBlocosPorUso().add(blocoMemoriaPrincipal.getValor());

            miss.setInstrucao(instrucaoArray[0]);
            miss.setParametroInstrucao1(Integer.parseInt(instrucaoArray[1]));
            impressao.imprimirReadMiss(miss);
        }

    }

    private void executarInstrucaoWrite(Memorias memorias, String[] instrucaoArray){
        List<Integer> resultadoBusca = controladorCache.buscarEnderecoNaMemoriaCache(memorias.getMemoriaCache(), Integer.parseInt(instrucaoArray[1]));

        Impressao impressao = new Impressao();

        int blocoNovoValorUso = 0;
        if(resultadoBusca.get(0) == BuscaCacheEnum.HIT){
            Hit hit = new Hit();
            hit.setInstrucao(instrucaoArray[0]);
            hit.setParametroInstrucao1(Integer.parseInt(instrucaoArray[1]));
            hit.setLinha(resultadoBusca.get(1));

            Bloco bloco = memorias.getMemoriaCache().getLinhaPorParametro(hit.getLinha()).getBloco();

            blocoNovoValorUso = bloco.getUso() + 1;
            memorias.getMemoriaCache().getLinhaPorParametro(hit.getLinha()).getBloco().setUso(blocoNovoValorUso);

//Executa a troca do valor do bloco na memoria cache e memoria principal porque está sedo passado como referencia
            bloco.getEnderecoPorParametro(Integer.parseInt(instrucaoArray[1])).setConteudo(Integer.parseInt(instrucaoArray[2]));
            hit.setParametroInstrucao2(Integer.parseInt(instrucaoArray[2]));

            //            Executa HIT
            impressao.imprimirWriteHit(hit);
        }else{
//            Executa MISS de acordo com o mapeamento
            Miss miss = new Miss();

//        Buscar bloco que contem o endereco
            Bloco blocoMemoriaPrincipal = controladorMemoriaPrincipal.buscarBlocoNaMemoria(memorias.getMemoriaPrincipal(), Integer.parseInt(instrucaoArray[1]));
            blocoNovoValorUso = blocoMemoriaPrincipal.getUso()+1;
            blocoMemoriaPrincipal.setUso(blocoNovoValorUso);

//            Verificar mapeamento da configuração
            if(Configuracao.MAPEAMENTO == MapeamentoEnum.MAPEAMENTO_DIRETO){
                miss = controladorCache.executarTrocaMapeamentoDireto(memorias, Integer.parseInt(instrucaoArray[1]), blocoMemoriaPrincipal);
            }else if(Configuracao.MAPEAMENTO == MapeamentoEnum.TOTALMENTE_ASSOCIATIVO){
                miss = controladorCache.executarTrocaMapeamentoTotalmenteAssociativo(memorias, Integer.parseInt(instrucaoArray[1]), blocoMemoriaPrincipal);
            }else if(Configuracao.MAPEAMENTO == MapeamentoEnum.PARCIALMENTE_ASSOCIATIVO){
                miss = controladorCache.executarTrocaMapeamentoParcialmenteAssociativo(memorias, Integer.parseInt(instrucaoArray[1]), blocoMemoriaPrincipal);
            }

//            Adiciona no final da lista de ordenação o valor do bloco que irá substituir o outro
            memorias.getMemoriaCache().getOrdenarBlocosPorUso().add(blocoMemoriaPrincipal.getValor());

            miss.setInstrucao(instrucaoArray[0]);
            miss.setParametroInstrucao1(Integer.parseInt(instrucaoArray[1]));

//Executa a troca do valor do bloco na memoria cache e memoria principal porque está sedo passado como referencia
            blocoMemoriaPrincipal.getEnderecoPorParametro(Integer.parseInt(instrucaoArray[1])).setConteudo(Integer.parseInt(instrucaoArray[2]));
            miss.setParametroInstrucao2(Integer.parseInt(instrucaoArray[2]));

            impressao.imprimirWriteMiss(miss);
        }
    }

}
