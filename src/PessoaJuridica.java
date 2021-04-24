public class PessoaJuridica extends Cliente{
    private String cnpj;

    public PessoaJuridica(String nome, String cnpj){
        super(nome);
        this.cnpj = cnpj;
    }

    public String getId(){
        return cnpj;
    }

    @Override
    public String toString() {
        return "Pessoa Juridica" + "\nnome : " + getNome() + "\nCNPJ : " + cnpj;
    }

}
