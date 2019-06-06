package br.ufrn.imd.cachel1.view;

import br.ufrn.imd.cachel1.model.*;

import java.util.concurrent.BlockingDeque;

public class Impressao {
    public void imprimirMemoriaCache(Cache memoriaCache){
        System.out.println("_________________________________");
        System.out.println("Cache L1");
        System.out.println("Linha    |   Bloco   |   Endereço   |     Conteúdo");
        for (Linha linha : memoriaCache.getLinhas()) {
            for(Endereco endereco : linha.getBloco().getEnderecos()){
                System.out.print(linha.getValor());
                System.out.print("       |      ");
                System.out.print(linha.getBloco().getValor());
                System.out.print("     |       ");
                System.out.print(endereco.getValor());
                System.out.print("       |       ");
                System.out.println(endereco.getConteudo());
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

    public void imprimirReadHit(String[] instrucaoArray, int linha){
        System.out.println(instrucaoArray[0] +" "+ instrucaoArray[1] +" -> HIT linha "+ linha);
    }
}
