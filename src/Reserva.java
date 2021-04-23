public class Reserva implements Pagamento {
    private Cliente cliente;
    private boolean pagamentoAVista;

    private static final double DESCONTO_AVISTA = 0.1;
    private static final double VALOR_FIXO = 32000;

    public Reserva(Cliente cliente, boolean pagamentoAVista){
        this.cliente = cliente;
        this.pagamentoAVista = pagamentoAVista;
    }

    public double calcularPagamento(){
        return 1.1;
    }

    @Override
    public String toString() {
        return cliente.toString() +
                ", pagamentoAVista=" + pagamentoAVista;
    }
}
