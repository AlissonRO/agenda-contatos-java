package service;

import exception.NegocioException;
import model.Contato;
import repository.ContatoRepository;

import java.util.List;

public class ContatoService {

    private final ContatoRepository repository = new ContatoRepository();

    public void cadastrar(String nome, String telefone){
        List<Contato> contatos = repository.listar();

        boolean telefoneExiste = contatos.stream().anyMatch(c ->c.getTelefone().equals(telefone.replaceAll("\\D", "")));

        if(telefoneExiste){
            throw new NegocioException("Telefone já cadastrado");
        }

        repository.salvar(new Contato(nome, telefone));
    }

    public List<Contato> listar(){
        return repository.listar();
    }

    public void excluir(String nome){
        List<Contato> contatos = repository.listar();
        boolean removido = contatos.removeIf(c -> c.getNome().equalsIgnoreCase(nome));

        if(!removido){
            throw new NegocioException("Contato não encontrado");
        }

        repository.sobrescrever(contatos);
    }

    public void atualizar(String nome, String novoNome, String novoTelefone){
        List<Contato> contatos = repository.listar();

        for(int i = 0; i < contatos.size(); i++){
            if(contatos.get(i).getNome().equalsIgnoreCase(nome)){
                contatos.set(i, new Contato(novoNome, novoTelefone));
                repository.sobrescrever(contatos);
                return;
            }
        }
        throw new NegocioException("Contato não encontrado");
    }
}
