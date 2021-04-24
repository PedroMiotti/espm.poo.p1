public class PessoaFisica extends Cliente {
    private String cpf;

    public PessoaFisica(String nome, String cpf){
        super(nome);
        this.cpf = cpf;
    }

    public String getId(){
        return cpf;
    }

    @Override
    public String toString() {
        return "Pessoa Fisica" + "\nnome : " + getNome() + "\nCPF : " + cpf;
    }

}
