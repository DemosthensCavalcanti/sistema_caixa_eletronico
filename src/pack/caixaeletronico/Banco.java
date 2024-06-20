package pack.caixaeletronico;

import java.util.*;

public class Banco {

    private String nome;
    private String cnpj;
    private List<Conta> contas = new ArrayList<>();
    private double saquePorDia;

    public Banco(String nome, String cnpj, List<Conta> contas, double saquePorDia){
        this.nome = nome;
        this.cnpj = cnpj;
        this.contas = contas;
        this.saquePorDia = saquePorDia;
    }

    public String getNome() {

        return this.nome;
    }

    public String getCnpj() {

        return this.cnpj;
    }

    public List<Conta> getContas() {

        return this.contas;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
    public double getSaquePorDia() {
        return this.saquePorDia;
    }
    public void setSaquePorDia(double saquePorDia) {
        this.saquePorDia = saquePorDia;
    }

    public void addConta(Conta c1) {
        this.contas.add(c1);
    }
}