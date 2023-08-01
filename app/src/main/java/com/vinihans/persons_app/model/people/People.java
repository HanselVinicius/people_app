package com.vinihans.persons_app.model.people;

import java.util.Date;




public class People {

    private Long id;
    private String nome;
    private String cpf;
    private Date dataDeAniversario;
    private Address endereco;
    private String telefone;


    public People( String nome, String cpf, Date dataDeAniversario, Address endereco, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeAniversario = dataDeAniversario;
        this.endereco = endereco;
        this.telefone = telefone;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataDeAniversario(Date dataDeAniversario) {
        this.dataDeAniversario = dataDeAniversario;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
