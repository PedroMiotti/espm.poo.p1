public abstract class Cliente {
    private String nome;

    public Cliente(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    abstract public String getId();

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                '}';
    }


}
