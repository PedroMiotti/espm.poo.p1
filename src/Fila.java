public class Fila {
    private int[] fila;
    private int capacidade, inicio, fim;

    // Capacidade para controlar o tamanho da fila de espera
    public Fila(int capacidade) {
        this.capacidade = capacidade;
        this.inicio = -1;
        this.fim = -1;
        this.fila = new int[capacidade];
    }

    public boolean isEmpty() {
        return (this.inicio == -1 && this.fim == -1);
    }

    public boolean isFull() {
        return ((this.fim + 1) % capacidade) == this.inicio;
    }

    public void add(int n) {

        if (isFull()) throw new RuntimeException("A fila está cheia !");
        if (isEmpty()) {
            this.inicio = 0;
            this.fim = 0;
            this.fila[0] = n;
        } else {
            fim = (fim + 1) % capacidade;
            this.fila[fim] = n;
        }

    }

    public int remove() {

        if (isEmpty())
            throw new RuntimeException("A fila está vazia !");

        int value = fila[inicio];

        if (this.inicio == this.fim) {
            this.inicio = -1;
            this.fim = -1;
        } else {
            this.inicio = ((this.inicio + 1) % capacidade);
        }
        return value;

    }

    public int head() {
        if (this.isEmpty())
            throw new RuntimeException("A fila está vazia !");
        return this.fila[inicio];
    }

}
