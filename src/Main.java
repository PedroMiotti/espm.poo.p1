import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import java.util.ArrayList;
import java.util.List;

import Fila.Fila;

public class Main {

    public static void main(String[] args){
        List<Reserva> ListaReserva = new ArrayList<>();
        Fila<Reserva> ListaEspera = new Fila<Reserva>();

        int posicao = 0;
        int posicaoEspera = 0;
        int opcao;

        do {
            opcao = parseInt(showInputDialog(GerarMenu()));
            if (opcao < 1 || opcao > 6) {
                showMessageDialog(null, "Opcao invalida !");
            } else {
                switch (opcao) {
                    case 1:
                        if(ListaReserva.size() <= 5){
                            ListaReserva.add(ReservarMesa());
                            ListarReservas(ListaReserva);
                        }
                        else{
                            showMessageDialog(null, "Lista de reserva esta cheia ! Movendo para a lista de espera.");
                             ListaEspera.inserirFim(ReservarMesa());
                        }

                        break;
                    case 2:
//                        Pesquisar(reserva, listaDeEspera, posicaoEspera, posicao);
                        break;

                    case 3:
//                        ListarReservas(reserva, posicao);
                        break;

                    case 4:
//                        ListarReservas(listaDeEspera, posicaoEspera);
                        break;

                    case 5:
//                        int[] aux = CancelarReserva(reserva, listaDeEspera, posicaoEspera, posicao);
                        break;
                }
            }
        } while (opcao != 6);

    }

    public static String GerarMenu(){
        String menu = "";
        menu += "1. Reservar mesa\n" +
                "2. Pesquisar reserva\n" +
                "3. Imprimir reservas\n" +
                "4. Imprimir lista de espera\n" +
                "5. Cancelar reserva\n" +
                "6. Finalizar\n";

        return menu;
    }

    private static Reserva  ReservarMesa(){
        String tipoPagamento;
        String tipoPessoa;
        Cliente cliente = null;
        boolean _tipoPagamento = false;

        tipoPessoa = showInputDialog("Pessoa Fisica[F] ou Pessoa Juridica[J] ?");

        if(tipoPessoa.equalsIgnoreCase("f"))
            cliente = CadastrarPF();

        else if(tipoPessoa.equalsIgnoreCase("j"))
            cliente = CadastrarPJ();

        tipoPagamento = showInputDialog("Pagamento a vista ?\n A vista[0] Parcelado[1] ");

        if(tipoPagamento.equalsIgnoreCase("0"))
            _tipoPagamento = true;

        Reserva novaReserva = new Reserva(cliente, _tipoPagamento);
        return novaReserva;
    }

    private static PessoaJuridica CadastrarPJ(){
        String cnpj;
        String nome;

        nome = showInputDialog("Nome : ");
        cnpj = showInputDialog("CNPJ : ");

        PessoaJuridica pj = new PessoaJuridica(nome, cnpj);
        return pj;
    }

    private static PessoaFisica CadastrarPF(){
        String cpf;
        String nome;

        nome = showInputDialog("Nome : ");
        cpf = showInputDialog("CPF : ");

        PessoaFisica pf = new PessoaFisica(nome, cpf);
        return pf;
    }

    private static void ListarReservas(List<Reserva> ListaReserva){
        ListaReserva.forEach(reservas -> {
            System.out.println(reservas);
        });

    }



}


