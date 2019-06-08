package br.ufrn.imd.cachel1.util;

import br.ufrn.imd.cachel1.enumerator.PoliticasSubstituicaoEnum;
import br.ufrn.imd.cachel1.model.Cache;
import br.ufrn.imd.cachel1.model.Linha;
import br.ufrn.imd.cachel1.model.Memorias;

import java.util.Random;

public class PoliticasSubstituicao {
    public int buscarLinhaPorPoliticaDeSubstituicao(Cache memoriaCache){
        if(Configuracao.POLITICAS_SUBSTITUICAO == PoliticasSubstituicaoEnum.ALEATORIO){
//            Executa a politica aleatória
            return aletoriedade();
        }else if(Configuracao.POLITICAS_SUBSTITUICAO == PoliticasSubstituicaoEnum.LFU){
//            Executa a politica LFU
            return LFU(memoriaCache);
        }else if(Configuracao.POLITICAS_SUBSTITUICAO == PoliticasSubstituicaoEnum.FIFO){
//            Executa a politica FIFO
            return FIFO(memoriaCache);
        }

        return 0;
    }

    private int aletoriedade(){
        Random gerador = new Random();
        return gerador.nextInt(Configuracao.LINHAS_CACHE);
    }

    private int LFU(Cache memoriaCache){
        boolean contemLinha = false;
        Linha linhaBlocoMenosUsado = new Linha();

        for (Linha linha : memoriaCache.getLinhas()) {
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

    private int FIFO(Cache memoriaCache){
//        Busca o primeiro da lista
        int blocoSaida = memoriaCache.getOrdenarBlocosPorUso().get(0);
        for (Linha linha : memoriaCache.getLinhas()) {
            if(linha.getBloco().getValor() == blocoSaida){
//                Retirar o bloco da lista de valores ordenados
                memoriaCache.getOrdenarBlocosPorUso().remove(0);

                return linha.getValor();
            }
        }

        return 0;
    }

}
