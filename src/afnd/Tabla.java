/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afnd;

import java.awt.List;
import java.lang.reflect.Array;
import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author netom
 */
public class Tabla {

    Set<String> alfabet;
    Set<Nodo> nodos;
    ArrayList<Set<Integer>> nodoList = new ArrayList<>();

    ArrayList<Set<Integer>>[] valores;
    private Set<String> alfabeto;
    int visitado = 0;
    int tottales = 0;
    ArrayList<String> nodo = new ArrayList<>();
    ArrayList<String>[] arr;

    public Tabla(ArrayList<Nodo> n, Set<String> a) {
        int indexActtal = 0;

        alfabet = a;
        valores = new ArrayList[alfabet.size()];
        for (int i = 0; i < alfabet.size(); i++) {
            valores[i] = new ArrayList<>();
        }
        System.out.println(alfabet.size());
        Set<Integer> c = new HashSet();
        c.add(n.get(0).estado);

        nodoList.add(c);

        int alfabetoIndes = 0;
        for (String alfa : alfabet) {

            Set<Integer> elemento = new HashSet();

            for (Trancicion trancicion : n.get(0).getApunta()) {
                if (trancicion.getValor().equals(alfa)) {
                    elemento.add(trancicion.getNodo());

                }
            }

            nodoList.add(elemento);
            valores[alfabetoIndes].add(elemento);
            alfabetoIndes++;
        }
        alfabetoIndes = 0;

        for (int i = 1; i < nodoList.size(); i++) {
            Set<Integer> cabeza = nodoList.get(i);

            alfabetoIndes = 0;
            for (String alfa : alfabet) {
                Set<Integer> elemento = new HashSet();
                for (Integer contenido : cabeza) {
                    System.out.println("padre: " + n.get(contenido).estado);
                    for (Trancicion trancicion : n.get(contenido).getApunta()) {

                        System.out.println(trancicion.getValor() + "" + trancicion.getNodo());
                        if (trancicion.getValor().equals(alfa)) {
                            elemento.add(trancicion.getNodo());
                            // System.out.println(" "+trancicion.getNodo()+" "+trancicion.getValor());
                            //System.out.println(trancicion.getNodo());
                        }
                    }
                }
                if (elemento.size() > 0) {
                    //System.out.println(alfa+" "+arrtoText(elemento));

                    if (nodoList.contains(elemento)) {
                        // System.out.println("True lo contengos");
                    } else {
                        nodoList.add(elemento);

                    }

                }
                valores[alfabetoIndes].add(elemento);
                alfabetoIndes++;
            }
        }
        convertoToTExt(nodoList, valores);
        System.out.println("nmo corioi");
    }

    public ArrayList<Nodo> get(Main main) {
        return getList(nodo, arr, main);
    }

    void convertoToTExt(ArrayList<Set<Integer>> nodoList, ArrayList<Set<Integer>>[] valores) {
        nodo = new ArrayList<>();
        arr = new ArrayList[valores.length];;
        for (Set<Integer> a : nodoList) {
            System.out.println("kdsnlkdokjdnjkdndjkn");
            String aux = arrtoText(a);
            if (aux.isEmpty()) {
                nodo.add("#");
            } else {
                nodo.add(aux);
                System.out.print(aux + "----");
            }

            nodo.remove("");
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>(); // Crear una instancia de ArrayList para cada elemento del arreglo
            for (Set<Integer> a : valores[i]) {
                String aux = arrtoText(a);
                if (aux.isEmpty()) {
                    arr[i].add("#");
                } else {
                    arr[i].add(aux);

                }
                System.out.print(arrtoText(a) + "--");
            }
            arr[i].remove("");
        }
        System.out.println("");

    }

    String arrtoText(Set<Integer> list) {
        String res = "";
        for (Integer integer : list) {

        }
        Integer[] arr = list.toArray(new Integer[list.size()]);
        Arrays.sort(arr);
        for (Integer integer : arr) {
            res = res + integer;
        }
        return res;
    }

    ArrayList<Nodo> getList(ArrayList<String> nodosLista, ArrayList<String>[] arr, Main main) {
        System.out.println("Agumon Digievoluvionar a");
        ArrayList<Nodo> lista = new ArrayList();
        ArrayList<String> alfa = new ArrayList<>(alfabet);

        for (int index = 0; index < nodosLista.size(); index++) {
            if (nodosLista.get(index).equals("#")) {
                nodosLista.remove(index);
                for (int i = 0; i < arr.length; i++) {
                    arr[i].remove(index);
                }

            }}
        for (int index = 0; index < nodosLista.size(); index++) {
            System.out.println("Nodo:"+index);
            Nodo nodo = new Nodo(index * 10, index * 10, index, main);
            for (int i = 0; i < arr.length; i++) {
                int bucado = buscar(nodosLista, arr[i].get(index));
                if (bucado >= 0) {
                    Trancicion t = new Trancicion(alfa.get(i), bucado);
                   
                    System.out.println( alfa.get(i)+" Apunta a "+bucado);
                    nodo.addTrancicion(t);
                }

            }

            lista.add(nodo);
        }

        for (int i = 0; i < lista.size(); i++) {

            System.out.print(lista.get(i).estado + " ");
            for (Trancicion trancicion : lista.get(i).getApunta()) {
                System.out.println(trancicion.getValor() + " " + trancicion.getNodo());
            }
        }
        return lista;
    }

    public static int buscar(ArrayList<String> nodosLista, String element) {
        for (int i = 0; i < nodosLista.size(); i++) {
            if (nodosLista.get(i).equals(element)) {
                return i; // Devolver el índice del elemento encontrado
            }
        }
        return -1; // El elemento no se encuentra en la lista
    }
}
