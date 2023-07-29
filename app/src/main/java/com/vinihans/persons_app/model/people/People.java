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
