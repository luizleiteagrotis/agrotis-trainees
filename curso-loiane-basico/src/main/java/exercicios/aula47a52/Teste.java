package atividades47_52;

import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int opcao = 1;

        while (opcao != 3) {
            opcao = obterMenu(scan);

            if (opcao == 1) {
                consultarContato(scan, agenda);
            } else if (opcao == 2) {
                adicionarContato(scan, agenda);
            } else if (opcao == 3) {
                System.exit(0);
            }

        }

    }

    public static void adicionarContato(Scanner scan, Agenda agenda) {

        try {
            System.out.println("Criando um novo contato");
            String nome = lerInformacao(scan, "Entre com o nome do contato");
            String telefone = lerInformacao(scan, "Entre com o telefone do contato");
            String email = lerInformacao(scan, "Entre com o email do contato");

            Contato contato = new Contato();
            contato.setNome(nome);
            contato.setTelefone(telefone);
            contato.setEmail(email);

            System.out.println("Contato a ser criado: ");
            System.out.println(contato);

            agenda.adicionarContato(contato);

        } catch (AgendaCheiaException e) {
            System.out.println(e.getMessage());

            System.out.println("Contatos: ");
            System.out.println(agenda);
        }
    }

    public static void consultarContato(Scanner scan, Agenda agenda) {

        String nomeContato = lerInformacao(scan, "Entre com o nome do contato a ser pesquisado: ");

        try {
            if (agenda.consultarContatoNome(nomeContato) >= 0) {
                System.out.println("Contato existe");
            }
        } catch (ContatoNaoExisteException e) {
            System.out.println(e.getMessage());
        }

    }

    public static String lerInformacao(Scanner scan, String info) {
        System.out.println(info);
        String entrada = scan.nextLine();
        return entrada;
    }

    public static int obterMenu(Scanner scan) {

        boolean entradaValida = false;
        int opcao = 3;

        while (!entradaValida) {
            System.out.println("Digite a opção desejada: ");
            System.out.println("1: Consultar contato");
            System.out.println("2: Adicionar contato");
            System.out.println("3: Sair");

            try {
                String entrada = scan.nextLine();
                opcao = Integer.parseInt(entrada);

                if (opcao == 1 || opcao == 2 || opcao == 3) {
                    entradaValida = true;
                } else {
                    throw new Exception("Entrada inválida\n");
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente\n");
            }
        }

        return opcao;

    }

}
