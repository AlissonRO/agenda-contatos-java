package ui;

import exception.NegocioException;
import service.ContatoService;

import java.util.Scanner;


public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ContatoService service = new ContatoService();

    public void iniciar(){
        while(true){
            System.out.println("""
                    1 - Cadastrar
                    2 - Listar
                    3 - Atualizar
                    4 - Excluir
                    0 - sair
                    """);

            String opcao = scanner.nextLine();

            try{
                switch (opcao){
                    case "1" -> cadastrar();
                    case "2" -> listar();
                    case "3" -> atualizar();
                    case "4" -> excluir();
                    case "0" -> System.exit(0);
                    default -> System.out.println("Opção inválida");
                }
            }catch (NegocioException | IllegalArgumentException e){
                System.out.println("Erro: "+ e.getMessage());
            }
        }
    }

    private void cadastrar(){
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        service.cadastrar(nome, telefone);
        System.out.println("Contato cadastrado com sucesso");
    }

    private void listar(){
        service.listar().forEach(System.out::println);
    }

    private void excluir(){
        System.out.print("Nome do contato: ");
        service.excluir(scanner.nextLine());
        System.out.println("Contato removido");
    }

    private void atualizar(){
        System.out.print("Nome atual: ");
        String nome = scanner.nextLine();

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo telefone: ");
        String novoTelefone = scanner.nextLine();

        service.atualizar(nome, novoNome, novoTelefone);
        System.out.println("Contato atualizado");
    }


}


