package br.ufrn.imd.cachel1.view;

import br.ufrn.imd.cachel1.model.*;

import java.util.concurrent.BlockingDeque;

public class Impressao {
    public void imprimirMemoriaCache(Cache memoriaCache){
        System.out.println("_________________________________");
        System.out.println("Cache L1 ");
        System.out.println("Observação: -1 indica ausencia de valor - valor vazio ");
        System.out.println("Linha    |   Bloco   |   Endereço   |     Conteúdo |     Uso");
        for (Linha linha : memoriaCache.getLinhas()) {
            for(Endereco endereco : linha.getBloco().getEnderecos()){
                System.out.print(linha.getValor());
                System.out.print("       |      ");
                System.out.print(linha.getBloco().getValor());
                System.out.print("     |       ");
                System.out.print(endereco.getValor());
                System.out.print("       |       ");
                System.out.print(endereco.getConteudo());
                //TODO retirar - só para mostar o uso do bloco
                System.out.print("       |       ");
                System.out.println(linha.getBloco().getUso());
            }
        }
    }

    public void imprimirMemoriaPrincipal(MemoriaPrincipal memoriaPrincipal){
        System.out.println("_________________________________");
        System.out.println("Memoria Principal");
        System.out.println("Bloco   |   Endereço   |     Conteúdo");
        for (Bloco bloco : memoriaPrincipal.getBlocos()) {
            for(Endereco endereco : bloco.getEnderecos()){
                System.out.print(bloco.getValor());
                System.out.print("       |       ");
                System.out.print(endereco.getValor());
                System.out.print("       |       ");
                System.out.println(endereco.getConteudo());
            }
        }
    }

    public void imprimirReadHit(Hit hit){
        System.out.println(hit.getInstrucao() +" "+ hit.getParametroInstrucao1() +" -> HIT linha "+ hit.getLinha());
    }

    public void imprimirReadMiss(Miss miss){
        System.out.print(miss.getInstrucao() + " ");
        System.out.print(miss.getParametroInstrucao1() + " -> MISS -> alocado na linha ");
        System.out.print(miss.getLinhaDeSubstituicao().getValor() + " -> Bloco ");
        System.out.println(miss.getBlocoSubstituido().getValor() + " Substituido");

    }
}
