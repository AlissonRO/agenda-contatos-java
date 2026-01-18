package repository;

import model.Contato;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository {
    private static final String ARQUIVO = "contatos.txt";

    public void salvar(Contato contato) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(contato.getId() + ";" + contato.getNome() + ";" + contato.getTelefone());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar contato");
        }
    }

    public List<Contato> listar() {
        List<Contato> contatos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                contatos.add(new Contato(dados[1], dados[2]));
            }
        } catch (IOException ignored) {}

        return contatos;
    }

    public void sobrescrever(List<Contato> contatos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Contato c : contatos) {
                bw.write(c.getId() + ";" + c.getNome() + ";" + c.getTelefone());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar arquivo");
        }
    }
}
