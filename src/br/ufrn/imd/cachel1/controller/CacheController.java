package br.ufrn.imd.cachel1.controller;

import br.ufrn.imd.cachel1.enumerator.BuscaCacheEnum;
import br.ufrn.imd.cachel1.model.Bloco;
import br.ufrn.imd.cachel1.model.Cache;
import br.ufrn.imd.cachel1.model.Endereco;
import br.ufrn.imd.cachel1.model.Linha;
import br.ufrn.imd.cachel1.util.Configuracao;
import br.ufrn.imd.cachel1.view.Impressao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheController {
    public void preencherMemoriaCache(Cache memoriaCache, Configuracao configuracao){
        int enderecoValor = 0;

        for(int i = 0; i < configuracao.getLinhasCache(); i++){
            Linha linha = new Linha();
            linha.setValor(i);
//            Criar novo bloco
            Bloco bloco = new Bloco();
            for(int j = 0; j < configuracao.getTamanhoBlocos(); j++){
//                Criar novo endereço
                Endereco endereco = new Endereco();

//                Incrementa os valores de endereço da memória
                enderecoValor++;

//                Adicionar endereço ao bloco
                bloco.getEnderecos().add(endereco);
            }
            linha.setBloco(bloco);
            memoriaCache.getLinhas().add(linha);
        }
    }

    public void executarImpressaoCache(Cache memoriaCache){
        Impressao impressao = new Impressao();
        impressao.imprimirMemoriaCache(memoriaCache);
    }

    public List<Integer> buscarEnderecoNaMemoriaCache(Cache memoriaCache, int valorEndereco){
        boolean enderecoEncontrado = false;
        int linhaBlocoEndereco = 0;

        List<Integer> buscaRetorno = new ArrayList<Integer>();

        for (Linha linha : memoriaCache.getLinhas()) {
            for (Endereco endereco : linha.getBloco().getEnderecos()) {
                if(endereco.getValor() == valorEndereco){
                    enderecoEncontrado = true;
                    linhaBlocoEndereco = linha.getValor();
                    break;
                }
            }

            if(enderecoEncontrado)
                break;
        }

        if(enderecoEncontrado){
            buscaRetorno.add(BuscaCacheEnum.HIT);
        }else{
            buscaRetorno.add(BuscaCacheEnum.MISS);
        }

        buscaRetorno.add(linhaBlocoEndereco);

        return buscaRetorno;
    }
}
