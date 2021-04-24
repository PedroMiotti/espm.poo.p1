package Fila;

import java.util.ArrayList;
import java.util.List;

public class Fila<T> {
    No<T> inicio;
    No<T> fim;
    int tamanho;

    public Fila(){
        inicio = fim = null;
        tamanho = 0;
    }

    public boolean isEmpty() {
        return (inicio == null);
    }

    public int getTamanho() {
        return tamanho;
    }

    public void inserirInicio(T data) {
        No<T> novoNo = new No(data);

        if (inicio == null)
            fim = inicio = novoNo;
        else {
            novoNo.prox = inicio;
            inicio.ant = novoNo;
            inicio = novoNo;
        }

        tamanho++;
    }

    public void inserirFim(T data){
        No novoNo = new No(data);

        if (fim == null)
            inicio = fim = novoNo;
        else {
            novoNo.ant = fim;
            fim.prox = novoNo;
            fim = novoNo;
        }

        tamanho++;

    }

    public void deletarInicio(){
        if (isEmpty())
            System.out.print("Fila vazia !!");
        else {
            No temp = inicio;
            inicio = inicio.prox;

            if (inicio == null)
                fim = null;
            else
                inicio.ant = null;

            tamanho--;
        }
    }

    public void deletarFim() {
        if (isEmpty())
            System.out.print("Fila vazia !!");

        else {
            No temp = fim;
            fim = fim.ant;

            if (fim == null)
                inicio = null;
            else
                fim.prox = null;

            tamanho--;
        }
    }

    public T getInicio(){
        if (isEmpty())
            System.out.println("Fila vazia !");

        return inicio.data;
    }

    public T getFim(){
        if (isEmpty())
            System.out.println("Fila vazia !");

        return fim.data;
    }

    public List toArray(){
        No n = inicio;
        List novaLista = new ArrayList();

        while(n != null){
            novaLista.add(n.data);
            n = n.prox;
        }
        return novaLista;
    }

    public void deletarFromIndex(int n) {
        if (inicio == null || n <= 0)
            return;

        No aux = inicio;

        for (int i = 1; aux != null && i < n; i++) {
            aux = aux.prox;
        }

        if (aux == null)
            return;

        deletarNo(aux);
    }

    No deletarNo(No noParaDeletar) {
        if (inicio == null || noParaDeletar == null)
            return null;

        if (inicio == noParaDeletar)
            inicio = noParaDeletar.prox;

        if (noParaDeletar.prox != null)
            noParaDeletar.prox.ant = noParaDeletar.ant;

        if (noParaDeletar.ant != null)
            noParaDeletar.ant.prox = noParaDeletar.prox;

        noParaDeletar = null;

        return inicio;
    }

    public void deletarTudo() {
        fim = null;
        while (inicio != null) {
            No temp = inicio;
            inicio = inicio.prox;
        }
        tamanho = 0;
    }
}

