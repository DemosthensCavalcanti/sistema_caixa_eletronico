package pack.caixaeletronico;

import java.util.*;

public class SistemaCaixaEletronico implements CaixaEletronicoInterface {

    List<Banco> bancos;

    public SistemaCaixaEletronico() {

    }

    public double transferencia(Banco banco, String numeroContaOriginal, String numeroAgOriginal, String numeroContaDestino,
                                String numeroAgenciaDestino, double valor)
    {
        for (Conta c : banco.getContas()) {
            if (numeroContaOriginal.equals(c.getNumeroConta()) && numeroAgOriginal.equals(c.getNumeroAgencia())) {
                for (Conta k : banco.getContas()) {
                    if (numeroContaDestino.equals(k.getNumeroConta()) && numeroAgenciaDestino.equals(k.getNumeroAgencia())) {
                        c.setSaldo(c.getSaldo() - valor);
                        k.setSaldo(k.getSaldo() + valor);
                        return k.getSaldo();
                    }
                }
            }
        }
        return -1;
    }

    public List<Banco> getBancos() {
        return this.bancos;
    }

    public double verificarSaldo(Banco banco, String numeroConta, String numeroAgencia) throws ContaInexistenteException {
        double saldo = 0;
        for (Conta c : banco.getContas()) {
            if (c.getNumeroConta().equals(numeroConta) && c.getNumeroAgencia().equals(numeroAgencia)) {
                saldo = c.getSaldo();
                return saldo;
            }
        }
        throw new ContaInexistenteException("O número da conta " + numeroConta + " ou o número da agência " + numeroAgencia + " está incorreto.\n Verifique e tente novamente.");
    }

    public double saqueTotal(Banco banco, String numeroConta, String numeroAgencia) throws NumberFormatException, NullPointerException{

        for (Conta c : banco.getContas()) {
            if (c.getNumeroConta().equals(numeroConta) && c.getNumeroAgencia().equals(numeroAgencia) && c.getSaldo() > 0) {
                double saldoDisponivel = c.getSaldo();
                c.setSaldo(0);
                return saldoDisponivel;
            }
        }
        throw new NumberFormatException();

    }

    public double deposito(Banco banco, String numeroConta, String numeroAgencia, double saldo) throws ContaInexistenteException {
        for (Conta c : banco.getContas()) {
            if (c.getNumeroConta().equals(numeroConta) && c.getNumeroAgencia().equals(numeroAgencia)) {
                c.setSaldo(c.getSaldo() + saldo);
                return c.getSaldo();
            }
        }
        throw new ContaInexistenteException("O número da conta " + numeroConta + " ou o número de agência " + numeroAgencia + " está incorreto. \n Verifique e tente novamente.");
    }



    public double saqueNormal(Banco banco, String numeroConta, String numeroAgencia, double saldo) {
        for (Conta c : banco.getContas()) {
            if (c.getNumeroConta().equals(numeroConta) && c.getNumeroAgencia().equals(numeroAgencia) && c.getSaldo() > 0 && c.getSaldo() >= saldo) {
                if (banco.getSaquePorDia() >= saldo) {
                    c.setSaldo(c.getSaldo() - saldo);
                    return c.getSaldo();
                }
            }
        }
        return 0.0;
    }

    public String toStringBancos() {

        String nomesBancos = "";
        for (Banco b : this.bancos) {
            nomesBancos += b.getNome() + ",";

        }
        return nomesBancos;
    }

    public void setBancos(List<Banco> bancos) {
        this.bancos = bancos;

    }
}


