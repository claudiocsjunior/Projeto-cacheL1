package br.ufrn.imd.cachel1.controller;

import br.ufrn.imd.cachel1.model.Bloco;
import br.ufrn.imd.cachel1.model.Cache;
import br.ufrn.imd.cachel1.model.Endereco;
import br.ufrn.imd.cachel1.model.MemoriaPrincipal;
import br.ufrn.imd.cachel1.util.Configuracao;
import br.ufrn.imd.cachel1.view.Impressao;

public class MemoriaPrincipalController {
    public void preencherMemoriaPrincipal(MemoriaPrincipal memoria){
        int enderecoValor = 0;

        for(int i = 0; i < Configuracao.BLOCOS_MEMORIA_PRINCIPAL; i++){
//            Criar novo bloco
            Bloco bloco = new Bloco();
            bloco.setValor(i);
            for(int j = 0; j < Configuracao.TAMANHO_BLOCOS; j++){
//                Criar novo endereço
                Endereco endereco = new Endereco();
                endereco.setValor(enderecoValor);
                endereco.setConteudo(enderecoValor); //Gerar um numero aleatório para o valor

//                Incrementa os valores de endereço da memória
                enderecoValor++;

//                Adicionar endereço ao bloco
                bloco.getEnderecos().add(endereco);
            }
//            Adicionar Bloco a memória
            memoria.getBlocos().add(bloco);
        }
    }

    public void executarImpressaoMemoriaPrincipal(MemoriaPrincipal memoriaPrincipal){
        Impressao impressao = new Impressao();
        impressao.imprimirMemoriaPrincipal(memoriaPrincipal);
    }

    public Bloco buscarBlocoNaMemoria(MemoriaPrincipal memoriaPrincipal, int valorEndereco){
        for (Bloco bloco : memoriaPrincipal.getBlocos()) {
            if(bloco.existeEndereco(valorEndereco)){
                return bloco;
            }
        }

        return new Bloco();

    }
}
