package br.ufrn.imd.cachel1.util;

import br.ufrn.imd.cachel1.enumerator.PoliticasSubstituicaoEnum;
import br.ufrn.imd.cachel1.model.Cache;
import br.ufrn.imd.cachel1.model.Linha;
import br.ufrn.imd.cachel1.model.Memorias;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoliticasSubstituicao {
    public int buscarLinhaPorPoliticaDeSubstituicao(Cache memoriaCache, int conjunto){
        if(Configuracao.POLITICAS_SUBSTITUICAO == PoliticasSubstituicaoEnum.ALEATORIO){
//            Executa a politica aleatória
            return aletoriedade(memoriaCache, conjunto);
        }else if(Configuracao.POLITICAS_SUBSTITUICAO == PoliticasSubstituicaoEnum.LFU){
//            Executa a politica LFU
            return LFU(memoriaCache, conjunto);
        }else if(Configuracao.POLITICAS_SUBSTITUICAO == PoliticasSubstituicaoEnum.FIFO){
//            Executa a politica FIFO
            if(conjunto == -1){
                return FIFO(memoriaCache, -1);
            }else{
                return FIFOPorConjunto(memoriaCache, conjunto);
            }

        }

        return 0;
    }

    private int aletoriedade(Cache memoriaCache, int conjunto){
        int linhaRetorno = 0;
        Random gerador = new Random();

        if(conjunto == -1){
            linhaRetorno = gerador.nextInt(Configuracao.LINHAS_CACHE);
        }else{
            List<Linha> linhas = memoriaCache.buscarLinhasPorConjunto(conjunto);
            int posicaoLista = gerador.nextInt(linhas.size());
            linhaRetorno = linhas.get(posicaoLista).getValor();
        }

        return linhaRetorno;
    }

    private int LFU(Cache memoriaCache, int conjunto){
        boolean contemLinha = false;
        Linha linhaBlocoMenosUsado = new Linha();

        List<Linha> memoriaCacheLinhas = new ArrayList<Linha>();
        if(conjunto == -1 ){
            memoriaCacheLinhas = memoriaCache.getLinhas();
        }else{
            memoriaCacheLinhas = memoriaCache.buscarLinhasPorConjunto(conjunto);
        }

        for (Linha linha : memoriaCacheLinhas) {
            if(!contemLinha){
                linhaBlocoMenosUsado = linha;
                contemLinha = true;
            }else{
                if(linha.getBloco().getUso() < linhaBlocoMenosUsado.getBloco().getUso()){
                    linhaBlocoMenosUsado = linha;
                }
            }
        }
        return linhaBlocoMenosUsado.getValor();
    }

    private int FIFO(Cache memoriaCache, int menorIndice){
//        Busca o primeiro da lista
        int blocoSaida = 0;
        if(menorIndice == -1){
            blocoSaida = memoriaCache.getOrdenarBlocosPorUso().get(0);
        }else{
            blocoSaida = memoriaCache.getOrdenarBlocosPorUso().get(menorIndice);
        }

        for (Linha linha : memoriaCache.getLinhas()) {
            if(linha.getBloco().getValor() == blocoSaida){
//                Retirar o bloco da lista de valores ordenados
                if(menorIndice == -1)
                    memoriaCache.getOrdenarBlocosPorUso().remove(0);
                else
                    memoriaCache.getOrdenarBlocosPorUso().remove(menorIndice);

                return linha.getValor();
            }
        }

        return 0;
    }

    private int FIFOPorConjunto(Cache memoriaCache, int conjunto){
        List<Linha> linhasMemoriaCache = memoriaCache.buscarLinhasPorConjunto(conjunto);

        int menorIndice = -1;

        for (Linha linha : linhasMemoriaCache) {
//            Busca os indices das linhas no atributo de ordenação dentro da cache
            int posicaoLinha = -1;

            for(int i = 0; i < memoriaCache.getOrdenarBlocosPorUso().size(); i++){
                if(linha.getBloco().getValor() == memoriaCache.getOrdenarBlocosPorUso().get(i)){
                    posicaoLinha = i;
                    break;
                }
            }

            if(menorIndice == -1){
                menorIndice = posicaoLinha;
            }else{
                if(posicaoLinha < menorIndice){
                    menorIndice = posicaoLinha;
                }
            }

        }

//        BUsca a linha de menor indice do conjunto
        return FIFO(memoriaCache, menorIndice);

    }

}
