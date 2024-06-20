package pack.caixaeletronico;

public class ContaInexistenteException extends Exception{

    public ContaInexistenteException(String msg){
        super(msg);
    }
}
