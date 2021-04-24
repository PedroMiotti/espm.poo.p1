import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Fila.Fila;

public class Main {

    public static void main(String[] args) {
        List<Reserva> ListaReserva = new ArrayList<>();
        Fila<Reserva> ListaEspera = new Fila<Reserva>();

        /*
         * Professores, caso queira testar mais facilmente, sem ter que ficar varias adicionando reservas,
         * copie e cole o conteudo do arquivo paraTestar.txt aqui.
         */

        int opcao;

        do {
            try {
                opcao = parseInt(showInputDialog(GerarMenu()));

                switch (opcao) {
                    case 1:
                        if (ListaReserva.size() <= 1) {
                            ListaReserva.add(ReservarMesa());
                        } else {
                            showMessageDialog(null, "Lista de reserva esta cheia ! Movendo para a lista de espera.");
                            ListaEspera.inserirFim(ReservarMesa());
                        }
                        break;
                    case 2:
                        PesquisarReserva(ListaReserva, ListaEspera);
                        break;

                    case 3:
                        ListarReservas(ListaReserva);
                        break;

                    case 4:
                        ListarListaDeEspera(ListaEspera);
                        break;

                    case 5:
                        ListaReserva = CancelarReserva(ListaReserva, ListaEspera);
                        break;
                }

            } catch (NumberFormatException e) {
                showMessageDialog(null, "Caracter invalido ! Por favor digitar um numero.");
                opcao = 0;
            }
        } while (opcao != 6);

    }

    public static String GerarMenu() {
        String menu = "";
        menu += "1. Reservar mesa\n" +
                "2. Pesquisar reserva\n" +
                "3. Imprimir reservas\n" +
                "4. Imprimir lista de espera\n" +
                "5. Cancelar reserva\n" +
                "6. Finalizar\n";

        return menu;
    }

    private static Reserva ReservarMesa() {
        String tipoPagamento;
        String tipoPessoa;
        Cliente cliente = null;
        boolean _tipoPagamento = false;

        tipoPessoa = showInputDialog("Pessoa Fisica[F] ou Pessoa Juridica[J] ?");

        if (tipoPessoa.equalsIgnoreCase("f"))
            cliente = CadastrarPF();

        else if (tipoPessoa.equalsIgnoreCase("j"))
            cliente = CadastrarPJ();

        tipoPagamento = showInputDialog("Pagamento a vista ?\n A vista[0] Parcelado[1] ");

        if (tipoPagamento.equalsIgnoreCase("0"))
            _tipoPagamento = true;

        Reserva novaReserva = new Reserva(cliente, _tipoPagamento);
        return novaReserva;
    }

    private static PessoaJuridica CadastrarPJ() {
        String cnpj;
        String nome;

        nome = showInputDialog("Nome : ");
        cnpj = showInputDialog("CNPJ : ");

        PessoaJuridica pj = new PessoaJuridica(nome, cnpj);
        return pj;
    }

    private static PessoaFisica CadastrarPF() {
        String cpf;
        String nome;

        nome = showInputDialog("Nome : ");
        cpf = showInputDialog("CPF : ");

        PessoaFisica pf = new PessoaFisica(nome, cpf);
        return pf;
    }

    private static void ListarReservas(List<Reserva> ListaReserva) {
        String listaDeReservas = "";

        for (int i = 0; i < ListaReserva.size(); i++) {
            listaDeReservas += ListaReserva.get(i);
        }

        showMessageDialog(null, listaDeReservas);
    }

    private static void ListarListaDeEspera(Fila<Reserva> ListaEspera) {
        String listaDeEspera = "";
        List novaLista;

        novaLista = ListaEspera.toArray();

        for (int i = 0; i < novaLista.size(); i++) {
            listaDeEspera += novaLista.get(i);
        }

        showMessageDialog(null, listaDeEspera);
    }

    /*
        @param Id: String - CPF ou CNPJ
     */
    private static int ProcurarReservaPorId(List<Reserva> Lista, String id) {
        int pos = -1;
        for (int i = 0; i < Lista.size(); i++) {
            if (Lista.get(i).getCliente().getId().equals(id)) {
                pos = i;
                return pos;
            }
        }
        return pos;
    }

    private static void PesquisarReserva(List<Reserva> ListaReserva, Fila<Reserva> ListaEspera) {
        String id = showInputDialog(null, "Qual o CPF ou CNPJ da sua reserva ?");
        int posicaoReserva;
        List novaLista;

        posicaoReserva = ProcurarReservaPorId(ListaReserva, id);

        if (posicaoReserva != -1) {
            showMessageDialog(null, "Sua reserva foi encontrada !");
        } else {
            novaLista = ListaEspera.toArray();
            posicaoReserva = ProcurarReservaPorId(novaLista, id);

            if (posicaoReserva == -1) {
                showMessageDialog(null, "Sua reserva nao foi encontrada !");
            } else {
                showMessageDialog(null, "Voce esta na lista de espera na posicao : " + (posicaoReserva + 1));
            }
        }

    }

    private static List CancelarReserva(List<Reserva> ListaReserva, Fila<Reserva> ListaEspera) {
        String id;
        int posicaoReserva;
        List novaLista;
        Reserva primeiraReservaListaEspera;

        id = showInputDialog(null, "Qual o CPF ou CNPJ da sua reserva ?");
        posicaoReserva = ProcurarReservaPorId(ListaReserva, id);

        if (posicaoReserva != -1) {
            ListaReserva.remove(posicaoReserva);

            // Movendo a primeira reserva de lista de espera para a lista de reserva
            primeiraReservaListaEspera = ListaEspera.getInicio();
            ListaReserva.add(primeiraReservaListaEspera);
            ListaEspera.deletarInicio();

            showMessageDialog(null, "Sua reserva foi cancelada com sucesso !");
            return ListaReserva;
        } else {
            novaLista = ListaEspera.toArray();
            posicaoReserva = ProcurarReservaPorId(novaLista, id);

            if (posicaoReserva == -1) {
                showMessageDialog(null, "Sua reserva nao foi encontrada !");
            } else {
                ListaEspera.deletarFromIndex(posicaoReserva);
                showMessageDialog(null, "Sua reserva foi cancelada com sucesso");
            }
        }

        return ListaReserva;
    }
}


