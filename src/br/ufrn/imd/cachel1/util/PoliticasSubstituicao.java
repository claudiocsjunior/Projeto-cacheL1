package br.ufrn.imd.cachel1.util;

import br.ufrn.imd.cachel1.enumerator.PoliticasSubstituicaoEnum;
import br.ufrn.imd.cachel1.model.Cache;
import br.ufrn.imd.cachel1.model.Linha;

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
        }

        return 0;
    }

    private int aletoriedade(){
        Random gerador = new Random();
        return gerador.nextInt(Configuracao.LINHAS_CACHE);
    }

    private int LFU(Cache memoriaCache){
        int linhaBlocoMenosUsado = -1;

        for (Linha linha : memoriaCache.getLinhas()) {
            if(linhaBlocoMenosUsado == -1){
                linhaBlocoMenosUsado = linha.getBloco().getUso();
            }else{
                if(linha.getBloco().getUso() < linhaBlocoMenosUsado){
                    linhaBlocoMenosUsado = linha.getBloco().getUso();
                }
            }
        }

        return linhaBlocoMenosUsado;
    }

}
