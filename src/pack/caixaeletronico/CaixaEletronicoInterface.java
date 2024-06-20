package pack.caixaeletronico;

public interface CaixaEletronicoInterface {

     double transferencia(Banco banco, String numeroContaOriginal, String numeroAgOriginal, String numeroContaDestino, String numeroAgenciaDestino, double valor);

    double verificarSaldo(Banco banco, String numeroConta, String numeroAgencia) throws ContaInexistenteException;

     double saqueTotal(Banco banco, String numeroConta, String numeroAgencia);

     double deposito(Banco banco, String numeroConta, String numeroAgencia, double saldo) throws ContaInexistenteException;

     double saqueNormal(Banco banco, String numeroConta, String numeroAgencia, double saldo);

}
