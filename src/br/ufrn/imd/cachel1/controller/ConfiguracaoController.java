package br.ufrn.imd.cachel1.controller;

import br.ufrn.imd.cachel1.enumerator.ConfiguracaoEnum;
import br.ufrn.imd.cachel1.util.Configuracao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracaoController {
//    Caminha produção
    private static String ARQUIVO = "../../../config/config.txt";
//    Caminho local
//    private static String ARQUIVO = "config/config.txt";

    public List<Integer> lerArquivo(){
        try {
            FileReader arquivo = new FileReader(ARQUIVO);
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            String linha;
            List<Integer> parametros = new ArrayList<Integer>();

            do {
                linha = lerArquivo.readLine();
                if(linha != null){
                    parametros.add(Integer.parseInt(linha));
                }

            }while(linha != null);

            return parametros;

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        return new ArrayList<Integer>();
    }

    public void aplicarConfiguracoes(){
        List<Integer> parametros = lerArquivo();

        Configuracao.TAMANHO_BLOCOS = parametros.get(ConfiguracaoEnum.TAMANHO_BLOCOS);
        Configuracao.LINHAS_CACHE = parametros.get(ConfiguracaoEnum.LINHAS_CACHE);
        Configuracao.BLOCOS_MEMORIA_PRINCIPAL = parametros.get(ConfiguracaoEnum.BLOCOS_MEMORIA_PRINCIPAL);
        Configuracao.MAPEAMENTO = parametros.get(ConfiguracaoEnum.MAPEAMENTO);
        Configuracao.NUMERO_CONJUNTOS = parametros.get(ConfiguracaoEnum.NUMERO_CONJUNTOS);
        Configuracao.POLITICAS_SUBSTITUICAO = parametros.get(ConfiguracaoEnum.POLITICAS_SUBSTITUICAO);
    }

}
