package br.ufrn.imd.cachel1.view;

import br.ufrn.imd.cachel1.controller.InstrucaoController;
import br.ufrn.imd.cachel1.enumerator.InstrucaoEnum;
import br.ufrn.imd.cachel1.model.Memorias;

import java.util.Scanner;
import java.util.regex.Pattern;

public class EntradaParametros {
    private void mostrarParametros(){
        System.out.println("___________________________________");
        System.out.println("Entre com uma das instruções abaixo: ");
        System.out.println("Read $endereço_a_ser_lido: ");
        System.out.println("Write $endereço_a_ser_lido $novo_valor: ");
        System.out.println("Show: ");
        System.out.println("exit:");
        System.out.println("___________________________________");

    }

    public void EntrarComInstrucoes(Memorias memorias){
        String instrucao = "";
        do {
            mostrarParametros();
            Scanner scanner = new Scanner(System.in);
            instrucao = scanner.nextLine();
//        Quebrar string em partes
            String[] instrucaoArray = instrucao.split(Pattern.quote(" "));
            if(!instrucao.equals("exit")){
                InstrucaoController instrucaoController = new InstrucaoController();
                int tipoInstrucao = instrucaoController.verificarInstrucao(instrucaoArray);
                if(tipoInstrucao == InstrucaoEnum.NOT_FOUND) {
                    System.out.println("Comando não encontrado");
                }else if(tipoInstrucao == InstrucaoEnum.INVALID_PARAMETERS){
                    System.out.println("Parametro(s) inválido(s)");
                }else{
                    instrucaoController.executarInstrucao(instrucaoArray, tipoInstrucao, memorias);
                }
            }
        }while(!instrucao.equals("exit"));
    }
}
