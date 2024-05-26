package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import entities.Carro;
import entities.Moto;
import entities.Veiculo;

public class VeiculoService {
    private List<Veiculo> veiculosDB;
    private Scanner scan;

    public List<Veiculo> getVeiculosDB() {
        return veiculosDB;
    }

    public VeiculoService() {
        this.veiculosDB = new ArrayList<>();
        this.scan = new Scanner(System.in);
    }

    public Veiculo save(Veiculo veiculo) throws Exception {
        if (veiculo == null)
            throw new Exception("Objeto nulo");
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty())
            throw new Exception("Campo Modelo não pode ser em branco");

        if (veiculo.getMarca() == null || veiculo.getMarca().isEmpty())
            throw new Exception("Campo Marca não pode ser em branco");

        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty())
            throw new Exception("Campo Placa não pode ser em branco");
        veiculosDB.add(veiculo);
        return veiculo;
    }

    public Optional<Veiculo> placaVeiculo(String placa) {
        return veiculosDB.stream().filter(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa)).findFirst();
    }

    public void removeVeiculo(String placa) throws Exception {
        Optional<Veiculo> veiculo = placaVeiculo(placa);
        if (veiculo.isPresent()) {
            veiculosDB.remove(veiculo.get());
        } else {
            throw new Exception("Veículo não encontrado.");
        }
    }

    public void imprimirVeiculos() {
        List<Veiculo> veiculos = getVeiculosDB();
        System.out.print("\033[H\033[2J");
        System.out.println("LISTA DE VEÍCULOS CADASTRADOS");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            int i = 1;
            for (Veiculo veiculo : veiculos) {
                String tipoVeiculo = (veiculo instanceof Carro) ? "Carro" : "Moto";
                String descricao = (veiculo instanceof Carro) ? "N. Portas: " + ((Carro) veiculo).getNumeroPortas() : "Part. Elétrica: " + (((Moto) veiculo).getPartidaEletrica() ? "Sim" : "Não");
                System.out.println("Veículo " + i + " - Tipo: " + tipoVeiculo + " - " + veiculo + " - " + descricao);
                i++;
            }
        }
        System.out.println("Pressione Enter para continuar para voltar ao Menu Inicial");
        scan.nextLine();
    }

    public void placaVeiculo() {
        System.out.print("\033[H\033[2J");
        System.out.println("PESQUISA DE VEÍCULOS POR PLACA");
        System.out.print("Informe a placa que deseja pesquisar: ");
        String placa = scan.nextLine();
        Optional<Veiculo> veiculo = placaVeiculo(placa);
        if (veiculo.isPresent()) {
            System.out.println(veiculo.get());
        } else {
            System.out.println("Veículo não encontrado com a placa informada.");
        }
        System.out.println("Pressione Enter para continuar para voltar ao Menu Inicial");
        scan.nextLine();
    }

    public void removePlaca() {
        System.out.print("\033[H\033[2J");
        System.out.println("REMOÇÃO DE VEÍCULO POR PLACA");
        List<Veiculo> veiculos = getVeiculosDB();
        System.out.print("\033[H\033[2J");
        System.out.println("LISTA DE VEÍCULOS CADASTRADOS");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            int i = 1;
            for (Veiculo veiculo : veiculos) {
                String tipoVeiculo = (veiculo instanceof Carro) ? "Carro" : "Moto";
                String descricao = (veiculo instanceof Carro) ? "N. Portas: " + ((Carro) veiculo).getNumeroPortas() : "Part. Elétrica: " + (((Moto) veiculo).getPartidaEletrica() ? "Sim" : "Não");
                System.out.println("Veículo " + i + " - Tipo: " + tipoVeiculo + " - " + veiculo + " - " + descricao);
                i++;
            }
        }
        System.out.print("Informe a placa do veículo que deseja REMOVER: ");
        String placa = scan.nextLine();
        try {
            removeVeiculo(placa);
            System.out.println("Veículo removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Veículo não encontrado com a placa informada");
        }
        System.out.println("Pressione Enter para continuar para voltar ao Menu Inicial");
        scan.nextLine();
    }
}