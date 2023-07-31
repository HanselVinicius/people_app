package com.vinihans.persons_app.model.people;

import java.util.Date;

public class People {

    private Long id;
    private String nome;
    private String cpf;
    private Date dataDeAniversario;
    private Address endereco;
    private String telefone;


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataDeAniversario() {
        return dataDeAniversario;
    }

    public Address getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataDeAniversario=" + dataDeAniversario +
                ", endereco=" + endereco +
                ", telefone='" + telefone + '\'' +
                '}';
    }



}
