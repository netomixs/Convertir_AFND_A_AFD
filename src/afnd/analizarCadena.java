/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afnd;

import java.util.ArrayList;

/**
 *
 * @author netom
 */
public class analizarCadena {
    ArrayList<Nodo> nodos;
    char[]arr;

    public analizarCadena(ArrayList<Nodo> nodos,char[] arr) {
        this.nodos = nodos;
        
        this.arr=arr;
    }
    boolean analizar(int indexActual,int indexNodo){
        if(indexActual==arr.length-1){
            for (Trancicion t : nodos.get(indexNodo).getApunta()) {
                    if(t.getValor().equals(arr[indexActual])){
                        if(nodos.get(t.getNodo()).estadoFinal){
                            return true;
                        }
                    }
            }
        }else{
              for (Trancicion t : nodos.get(indexNodo).getApunta()) {
                    if(t.getValor().equals(arr[indexActual])){
                       return analizar(indexActual+1,t.getNodo());
                    }
            }
        }
        return false;
    }
}
