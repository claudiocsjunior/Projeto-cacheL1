package br.ufrn.imd.cachel1.view;

import br.ufrn.imd.cachel1.controller.MemoriaPrincipalController;
import br.ufrn.imd.cachel1.model.Cache;
import br.ufrn.imd.cachel1.model.MemoriaPrincipal;
import br.ufrn.imd.cachel1.model.Memorias;
import br.ufrn.imd.cachel1.util.Configuracao;

public class main {

    public static void main(String[] args){
        /*
        * Endereço contem o conteudo
        * Bloco contem uma lista de endereços
        * linha contem um bloco
        *
        * cache contem uma lista de linha
        * memoria contem uma lista de blocos
        *
        * */

//        Aplicando configuração de teste - Buscar pelo arquivo csv de configuração
         Configuracao.aplicarConfiguracao(4, 8, 16, 1, 2, 3);

//        Criando mamórias com base nas configurações recebidas no objeto acima
         Memorias memorias = new Memorias();

//        Entrada de parametros
            EntradaParametros entradaParametros = new EntradaParametros();
            entradaParametros.EntrarComInstrucoes(memorias);
    }
}
