package br.ufrn.imd.cachel1.controller;

import br.ufrn.imd.cachel1.enumerator.BuscaCacheEnum;
import br.ufrn.imd.cachel1.model.*;
import br.ufrn.imd.cachel1.util.Configuracao;
import br.ufrn.imd.cachel1.util.PoliticasSubstituicao;
import br.ufrn.imd.cachel1.view.Impressao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheController {
    private Impressao impressao;
    private MemoriaPrincipalController memoriaPrincipalController;

    public CacheController(){
        impressao = new Impressao();
        memoriaPrincipalController = new MemoriaPrincipalController();
    }

    public void preencherMemoriaCache(Cache memoriaCache){
        int enderecoValor = 0;

        for(int i = 0; i < Configuracao.LINHAS_CACHE; i++){
            Linha linha = new Linha();
            linha.setValor(i);
//            Criar novo bloco
            Bloco bloco = new Bloco();
            bloco.setValor(-1);
            for(int j = 0; j < Configuracao.TAMANHO_BLOCOS; j++){
//                Criar novo endereço
                Endereco endereco = new Endereco();

                endereco.setValor(-1);
                endereco.setConteudo(-1);
//                Incrementa os valores de endereço da memória
                enderecoValor++;

//                Adicionar endereço ao bloco
                bloco.getEnderecos().add(endereco);
            }
            linha.setBloco(bloco);
            memoriaCache.getLinhas().add(linha);
        }
    }

    public void inserirConjuntosNasLinhas(Cache memoriaCache){
        int i = 0;
        int j = 0;
        int posicaoLinha = 0;
        while (i < (Configuracao.NUMERO_CONJUNTOS+1)){
            while(j < (Configuracao.LINHAS_CACHE / Configuracao.NUMERO_CONJUNTOS)){
                if(memoriaCache.getLinhas().get(posicaoLinha) != null){
                    memoriaCache.getLinhas().get(posicaoLinha).setConjuntoPertencente(i);
                }
                posicaoLinha++;
                j++;
            }
            i++;
            j = 0;
        }
    }

    public void executarImpressaoCache(Cache memoriaCache){
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


    public Miss executarTrocaMapeamentoDireto(Memorias memorias, int valorEndereco, Bloco blocoMemoriaPrincipal){
//        Buscar valor da linha a partir da formula
        int valorBuscaLinha = blocoMemoriaPrincipal.getValor() % Configuracao.LINHAS_CACHE;

//        Setando a linha e buscando o Miss
        return this.executarSubstituicaoBlocoMiss(valorBuscaLinha, new Miss(), memorias, blocoMemoriaPrincipal);
    }

    private Miss executarSubstituicaoBlocoMiss(int valorBuscaLinha, Miss miss, Memorias memorias, Bloco blocoSubstituto){
        for (Linha linha : memorias.getMemoriaCache().getLinhas()) {
            if(linha.getValor() == valorBuscaLinha){
//                Passando o bloco substituido para o Miss
                miss.setBlocoSubstituido(linha.getBloco());

//                Inserindo o novo bloco na linha
                linha.setBloco(blocoSubstituto);

//                Guardando o novo bloco no Miss
                miss.setBlocoSubstituto(blocoSubstituto);

//                Preencher a linha
                miss.setLinhaDeSubstituicao(linha);
            }
        }

        return miss;
    }

    public Miss executarTrocaMapeamentoTotalmenteAssociativo(Memorias memorias, int valorEndereco, Bloco blocoMemoriaPrincipal){
//        Verificar se há alguma linha vazia - caso haja - preenche-la
//        Caso contrário, adicionar a partir do método de substituicao

        int valorBuscaLinha = memorias.getMemoriaCache().buscarValorLinhaVazia();

//        Se for == -1 é porque não existem espaços vazios
        if(valorBuscaLinha == -1){
            PoliticasSubstituicao politicasSubstituicao = new PoliticasSubstituicao();
            valorBuscaLinha = politicasSubstituicao.buscarLinhaPorPoliticaDeSubstituicao(memorias.getMemoriaCache());
        }

        return this.executarSubstituicaoBlocoMiss(valorBuscaLinha, new Miss(), memorias, blocoMemoriaPrincipal);

    }

    public Miss executarTrocaMapeamentoParcialmenteAssociativo(Memorias memorias, int valorEndereco, Bloco blocoMemoriaPrincipal){
//        Verificar se há alguma linha vazia - caso haja - preenche-la
//        Caso contrário, adicionar a partir do método de substituicao

        int valorBuscaLinha = memorias.getMemoriaCache().buscarValorLinhaVazia();

//        Se for == -1 é porque não existem espaços vazios
        if(valorBuscaLinha == -1){
            PoliticasSubstituicao politicasSubstituicao = new PoliticasSubstituicao();
            valorBuscaLinha = politicasSubstituicao.buscarLinhaPorPoliticaDeSubstituicao(memorias.getMemoriaCache());
        }

        return this.executarSubstituicaoBlocoMiss(valorBuscaLinha, new Miss(), memorias, blocoMemoriaPrincipal);

    }


}
