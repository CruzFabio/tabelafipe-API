package br.com.oracle.g8.tabelafipe.principal;

import br.com.oracle.g8.tabelafipe.model.Dados;
import br.com.oracle.g8.tabelafipe.model.Modelos;
import br.com.oracle.g8.tabelafipe.model.Veiculo;
import br.com.oracle.g8.tabelafipe.servico.ConsumoApi;
import br.com.oracle.g8.tabelafipe.servico.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    public void exibirMenu() {
        boolean continuar = true;

        while (continuar) {

            var menu = """
                    
                    |  -------------------------------------  |
                    |               TABELA FIPE               |
                    |  -------------------------------------  |
                    |         Categorias disponíveis:         |
                    |                                         |
                    | > Carros (inclui Utilitários Pequenos)  |
                    | > Caminhões (inclui Micro-ônibus)       |
                    | > Motos                                 |
                    |  -------------------------------------  |
                    
                    """;

            System.out.println(menu);
            System.out.print("> Qual deseja consultar: ");
            var opcao = leitura.nextLine();
            String endereco = "";

            if (opcao.toLowerCase().contains("carr")) {
                endereco = URL_BASE + "carros/marcas";
            } else if (opcao.toLowerCase().contains("cami")) {
                endereco = URL_BASE + "caminhoes/marcas";
            } else if (opcao.toLowerCase().contains("mot")) {
                endereco = URL_BASE + "motos/marcas";
            } else {
                System.out.println("Opção inválida. Tente novamente.\n");
                continue;
            }

            var json = consumoApi.obterDados(endereco);
            System.out.println(json);
            var marcas = converteDados.obterLista(json, Dados.class);
            marcas.stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                            .forEach(System.out::println);

            System.out.print("Digite o código da marca para consulta: ");
            var codigoMarca = leitura.nextLine();

            endereco += "/" + codigoMarca + "/modelos";
            json = consumoApi.obterDados(endereco);
            var modeloLista = converteDados.obterDados(json, Modelos.class);

            System.out.println("\nModelos da marca:");
            modeloLista.modelos().stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                            .forEach(System.out::println);

            System.out.print("\nDigite o nome do carro a ser localizado: ");
            var nomeVeiculo = leitura.nextLine();

            List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                    .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                            .collect(Collectors.toList());

            System.out.println("\nModelos Filtrados");
            modelosFiltrados.forEach(System.out::println);

            System.out.print("\nDigite o código do modelo escolhido: ");
            var codigoModelo = leitura.nextLine();

            endereco += "/" + codigoModelo + "/anos";
            json = consumoApi.obterDados(endereco);
            List<Dados> anos = converteDados.obterLista(json, Dados.class);
            List<Veiculo> veiculos = new ArrayList<>();

            for (int i = 0; i < anos.size(); i++) {
                var enderecoAnos = endereco + "/" + anos.get(i).codigo();
                json = consumoApi.obterDados(enderecoAnos);
                Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
                veiculos.add(veiculo);
            }

            System.out.println("\nVeículos filtrados com avaliações por anos:");
            veiculos.forEach(System.out::println);

            // continua ?
            System.out.print("\nDeseja realizar uma nova consulta? (s/n): ");
            var resposta = leitura.nextLine().toLowerCase();
            if (!resposta.startsWith("s")) {
                continuar = false;
                System.out.println("Consulta finalizada. Obrigado por usar a tabela FIPE!");
            }
        }
        leitura.close();
    }
}
