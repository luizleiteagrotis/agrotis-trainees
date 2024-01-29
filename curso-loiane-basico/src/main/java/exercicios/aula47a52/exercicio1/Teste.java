package exercicios.aula47a52.exercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

import exercicios.aula47a52.exercicio1.execoes.ContatoNaoExisteException;
import exercicios.aula47a52.exercicio1.execoes.ExcecaoAgendaCheia;

public class Teste {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean flagAgenda = true;
        Agenda agenda = new Agenda();
        int opcao;

        do {
            try {
                System.out.println("1- consultar contato na agenda: ");
                System.out.println("2- adicionar um contato na agenda:  ");
                opcao = scan.nextInt();
                scan.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o contato desejada: ");
                        String nomePesquisa = scan.next();
                        agenda.mostrarContatoAgenda(nomePesquisa);
                        break;
                    case 2:
                        System.out.println("Digite o nome: ");
                        String nome = scan.nextLine();
                        System.out.println("Digite  o telefone: ");
                        String telefone = scan.nextLine();
                        Contato contato = new Contato();
                        contato.setNome(nome);
                        contato.setTelefone(telefone);
                        agenda.adicionarContato(contato, contato.getIdentificador());

                        break;
                    default:
                        System.out.println("Opção inválida");
                }

                System.out.println("-------------------------------");
                System.out.println("Pretende escolher outra opção? ");
                String decisao = scan.next();
                flagAgenda = validarFlag(decisao);
            } catch (ExcecaoAgendaCheia e) {
                System.out.println("Agenda Cheia");
                continue;
            } catch (ContatoNaoExisteException exp) {
                System.out.println("Contato não existe");
                continue;
            } catch (InputMismatchException excep) {
                System.out.println("Valor de entrada inválido");
                scan.next();
                continue;
            }
        } while (flagAgenda);

    }

    public static boolean validarFlag(String decisao) {
        boolean flag = true;
        if (decisao.equalsIgnoreCase("sim") || decisao.equalsIgnoreCase("s")) {
            flag = true;
        } else if (decisao.equalsIgnoreCase("nao") || decisao.equalsIgnoreCase("n") || decisao.equalsIgnoreCase("não")) {
            flag = false;
        } else {
            System.out.println("Opção inválida");
        }

        return flag;
    }
}
