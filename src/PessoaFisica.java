public class PessoaFisica extends Cliente {
    private String cpf;

    public PessoaFisica(String nome, String cpf){
        super(nome);
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Pessoa Fisica - " +
                "nome = " + getNome() +
                "cpf = " + cpf;
    }
}
