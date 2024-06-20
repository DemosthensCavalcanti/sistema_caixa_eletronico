package pack.caixaeletronico;

import java.util.Objects;

public class Conta {

    private String cpf;
    private String nome;
    private String tipoConta;
    private String numeroConta;
    private String numeroAgencia;
    private double saldo;


    public Conta(String cpf, String nome, String tipoConta, String numeroConta, String numeroAgencia, double saldo) {
        this.cpf = cpf;
        this.nome = nome;
        this.tipoConta = tipoConta;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.saldo = saldo;
    }
    public Conta() {
        this("","","","","",0.0);
    }

    public String getCpf() {

        return cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conta conta = (Conta) o;

        if (!Objects.equals(cpf, conta.cpf)) return false;
        return Objects.equals(numeroConta, conta.numeroConta);
    }

    @Override
    public int hashCode() {
        int result = cpf != null ? cpf.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (numeroConta != null ? numeroConta.hashCode() : 0);
        return result;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getTipoConta() {

        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {

        this.tipoConta = tipoConta;
    }

    public String getNumeroConta() {

        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {

        this.numeroConta = numeroConta;
    }

    public String getNumeroAgencia() {

        return numeroAgencia;
    }

    public void setNumeroAgencia(String numeroAgencia) {

        this.numeroAgencia = numeroAgencia;
    }

    public double getSaldo() {

        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
