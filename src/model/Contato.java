package model;

import java.util.UUID;

public class Contato {
    private final String id;
    private final String nome;
    private final String telefone;

    public Contato(String nome, String telefone){
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        telefone = telefone.replaceAll("\\D", "");
        if(!telefone.matches("\\d{8,11}")){
            throw new IllegalArgumentException("Telefone inválido");
        }

        this.id = UUID.randomUUID().toString();
        this.nome = nome.trim();
        this.telefone = telefone;
    }

/*    public Contato(UUID id, String nome, String telefone){
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        telefone = telefone.replaceAll("\\D", "");
        if(!telefone.matches("\\d{8,11}")){
            throw new IllegalArgumentException("Telefone inválido");
        }

        this.id = id.toString();
        this.nome = nome.trim();
        this.telefone = telefone;
    }*/

    public String getId(){return id;}
    public String getNome(){return nome;}
    public String getTelefone(){return telefone;}

    @Override
    public String toString(){
        return nome + " - " + telefone;
    }
}

