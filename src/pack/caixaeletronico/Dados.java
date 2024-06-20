package pack.caixaeletronico;

public class Dados {
    private Banco banco;
    private String numeroConta;
    private String numeroAgencia;

    public Dados(Banco banco, String numeroConta, String numeroAgencia) {
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
    }
    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
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
}