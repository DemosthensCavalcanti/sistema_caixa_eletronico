package pack.caixaeletronico;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class CaixaEletronico {


    public static void main(String[] args) throws IOException{

         SistemaCaixaEletronico sistema = new SistemaCaixaEletronico();
         GravadorDeDados dados = new GravadorDeDados();

        RecuperarDados(sistema,dados);

        while(true) {


            String[] menu = {"Transferência", "Verificar saldo", "Saque total", "Depósito", "Saque normal", "Sair"};
            int menuUp = JOptionPane.showOptionDialog(null,"Escolha as opções:",
                    "Caixa Eletrônico",JOptionPane.OK_CANCEL_OPTION, 3,new ImageIcon("meuIconLogo.png"), menu,
                    menu[5]);


            switch (menuUp) {
                case 0:
                    try {
                        Dados transferir = solicitarDados(sistema);
                        String numeroContaDestino = JOptionPane.showInputDialog("Digite o número da conta de Destino");
                        String numeroAgenciaDestino = JOptionPane.showInputDialog("Digite o número da Agência de Destino");

                        String[] valores1 = {"20", "50", "100", "Outros valores"};
                        String selecionar1 = (String) JOptionPane.showInputDialog(null, "Escolha o valor para tranferência\nSaldo disponível: "
                                        + sistema.verificarSaldo(transferir.getBanco(), transferir.getNumeroConta(), transferir.getNumeroAgencia()),
                                "Caixa Eletrônico", JOptionPane.QUESTION_MESSAGE, new ImageIcon("meuIcon5.png"), valores1, valores1[0]);
                        double valor1 = 0;
                        if (selecionar1.equals(valores1[3])) {
                            valor1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser transferido "));
                            sistema.transferencia(transferir.getBanco(), transferir.getNumeroConta(), transferir.getNumeroAgencia(), numeroContaDestino, numeroAgenciaDestino, valor1);
                            JOptionPane.showMessageDialog(null, "Transferência de R$" + valor1 + " concluida");
                        } else {
                            sistema.transferencia(transferir.getBanco(), transferir.getNumeroConta(), transferir.getNumeroAgencia(), numeroContaDestino, numeroAgenciaDestino, Double.parseDouble(selecionar1));
                            JOptionPane.showMessageDialog(null, "Transferência de R$" + selecionar1 + " concluida");
                        }
                    } catch (ContaInexistenteException | NullPointerException | NumberFormatException e){
                        JOptionPane.showMessageDialog(null, e.getMessage());

                    }
                    break;

                case 1:
                    try {
                        Dados verificarSaldo = solicitarDados(sistema);
                        sistema.verificarSaldo(verificarSaldo.getBanco(), verificarSaldo.getNumeroConta(), verificarSaldo.getNumeroAgencia());
                        JOptionPane.showMessageDialog(null, "Seu saldo é R$" + sistema.verificarSaldo(verificarSaldo.getBanco(), verificarSaldo.getNumeroConta(), verificarSaldo.getNumeroAgencia()));
                    } catch(ContaInexistenteException | NullPointerException e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;

                case 2:

                    try {
                        Dados saqueTotal = solicitarDados(sistema);
                          sistema.saqueTotal(saqueTotal.getBanco(), saqueTotal.getNumeroConta(), saqueTotal.getNumeroAgencia());
                          JOptionPane.showMessageDialog(null, "Saque total realizado");
                    } catch(NumberFormatException | NullPointerException  e){
                        JOptionPane.showMessageDialog(null, "Dados inválidos!\nVerifique os dados e tente novamente." );
                    }
                    break;

                case 3:
                    try {

                        Dados depositar = solicitarDados(sistema);

                        String[] valores2 = {"20","50","100","Outros valores"};
                        String selecionar2 = (String) JOptionPane.showInputDialog(null, "Escolha o valor para depositar\nSaldo disponível: "
                                        + sistema.verificarSaldo(depositar.getBanco(),depositar.getNumeroConta(),depositar.getNumeroAgencia()) ,
                                "Caixa Eletrônico",JOptionPane.QUESTION_MESSAGE, new ImageIcon("meuIcon5.png"),valores2,valores2[0]);

                        double valorSacar = 0;
                        if(selecionar2.equals(valores2[3])){
                            valorSacar = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser depositado "));
                            sistema.deposito(depositar.getBanco(), depositar.getNumeroConta(), depositar.getNumeroAgencia(), valorSacar);
                            JOptionPane.showMessageDialog(null, "Depósito realizado");

                        }else{
                            sistema.deposito(depositar.getBanco(), depositar.getNumeroConta(), depositar.getNumeroAgencia(), Integer.parseInt(selecionar2));
                            JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso");
                        }


                    } catch(ContaInexistenteException | NumberFormatException | NullPointerException a){
                        JOptionPane.showMessageDialog(null, "Dados inválidos!\nVerifique os dados e tente novamente.");
                    }
                    break;

                case 4:
                    try {
                        Dados saqueNormal = solicitarDados(sistema);
                        String[] valores = {"20","50","100","Outros valores"};
                        String selecionar = (String) JOptionPane.showInputDialog(null, "Escolha o valor para sacar\nSaldo disponível: "
                                        + sistema.verificarSaldo(saqueNormal.getBanco(),saqueNormal.getNumeroConta(),saqueNormal.getNumeroAgencia()) ,
                                "Caixa Eletrônico",JOptionPane.QUESTION_MESSAGE, new ImageIcon("meuIcon5.png"),valores,valores[0]);

                        double valorSacar = 0;
                        if(selecionar.equals(valores[3])){
                             valorSacar = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser transferido "));
                            sistema.saqueNormal(saqueNormal.getBanco(), saqueNormal.getNumeroConta(), saqueNormal.getNumeroAgencia(), valorSacar);
                            JOptionPane.showMessageDialog(null, "Saque realizado");

                        }else{
                            sistema.saqueNormal(saqueNormal.getBanco(), saqueNormal.getNumeroConta(), saqueNormal.getNumeroAgencia(), Integer.parseInt(selecionar));
                            JOptionPane.showMessageDialog(null, "Saque realizado");
                        }
                    } catch(ContaInexistenteException | NumberFormatException | NullPointerException e){
                        JOptionPane.showMessageDialog(null, "Dados inválidos!\nVerifique os dados e tente novamente.");
                    }
                    break;

                case 5:
                    GravarDados(sistema, dados);
                    System.exit(0);
                    break;
            }
        }
    }
        public static Dados solicitarDados (SistemaCaixaEletronico sistema) {
            List<Banco> listaDeDados = sistema.getBancos();
            String[] nomesBancos = sistema.toStringBancos().split(",");
            int bancoVS = JOptionPane.showOptionDialog(null,"Escolha seu Banco:\n",
                    "Caixa Eletrônico",JOptionPane.OK_CANCEL_OPTION, 3, null, nomesBancos,
                    nomesBancos[0]);

            for (Banco b : listaDeDados) {
                if (b.getNome().equalsIgnoreCase(nomesBancos[bancoVS])) {
                    String numeroConta = JOptionPane.showInputDialog("Digite o número da conta ");
                    String numeroAgencia = JOptionPane.showInputDialog("Digite o número da Agência");
                    return new Dados(b, numeroConta, numeroAgencia);
                }
            }
            return null;
        }
        public static void GravarDados(SistemaCaixaEletronico sistema, GravadorDeDados dados) throws IOException {
            List<Banco> bancos = sistema.getBancos();
            List<String> lista = new ArrayList<>();
            List<String> contas = new ArrayList<>();
            for(Banco a : bancos){
                String salvamentoDeDados = a.getNome() + "," + a.getCnpj() + "," + String.valueOf(a.getSaquePorDia()) + ",";
                String salvamentoDeContas = "";
                for(Conta c : a.getContas()){
                    salvamentoDeContas = a.getNome()+ ","+ c.getCpf() + "," + c.getNome() + "," + c.getTipoConta() + "," + c.getNumeroConta() + "," +
                            c.getNumeroAgencia() + "," + String.valueOf(c.getSaldo() + ",");
                            contas.add(salvamentoDeContas);
                }
                lista.add(salvamentoDeDados);
            }
            dados.gravaTextoEmArquivo(contas, "ContasGravadas.txt");
            dados.gravaTextoEmArquivo(lista,"DadosGravados.txt");
        }

        public static void RecuperarDados(SistemaCaixaEletronico sistema, GravadorDeDados recuperador) throws IOException{
            List<Banco> bancos = new ArrayList<>();
            List<String> recuperar = recuperador.recuperaTextoEmArquivo("DadosGravados.txt");
            List<String> recuperarContas = recuperador.recuperaTextoEmArquivo("ContasGravadas.txt");

            for(String a : recuperar){
                String[] dadosString = a.split(",");
                List<Conta> todasContas = new ArrayList<>();

                for(String c : recuperarContas){
                    String[] contasString = c.split(",");
                    if(contasString[0].equals(dadosString[0])){
                        Conta c1 = new Conta(contasString[1],contasString[2],contasString[3],contasString[4],contasString[5], Double.parseDouble(contasString[6]));
                        todasContas.add(c1);
                    }
                }
                Banco b1 = new Banco(dadosString[0],dadosString[1],todasContas,Double.parseDouble(dadosString[2]));
                bancos.add(b1);
            }
            sistema.setBancos(bancos);
        }
    }