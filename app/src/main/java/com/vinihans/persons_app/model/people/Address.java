package com.vinihans.persons_app.model.people;

public class Address {
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private String uf;

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    @Override
    public String toString() {
        return "Address{" +
                "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep='" + cep + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
