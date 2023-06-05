/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afnd;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author netom
 */
public class AFD {
    private Set<Set<Integer>> estados;
    private Set<Integer> estadoInicial;
    private Set<Set<Integer>> estadosFinales;
    private Map<Set<Integer>, Map<Character, Set<Integer>>> transiciones;
    private Set<Character> alfabeto;

    public AFD(Set<Set<Integer>> estados, Set<Integer> estadoInicial, Set<Set<Integer>> estadosFinales, Map<Set<Integer>, Map<Character, Set<Integer>>> transiciones, Set<Character> alfabeto) {
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
        this.transiciones = transiciones;
        this.alfabeto = alfabeto;
    }

    public boolean aceptaCadena(String cadena) {
        Set<Integer> estadoActual = estadoInicial;
        for (char simbolo : cadena.toCharArray()) {
            estadoActual = transiciones.get(estadoActual).get(simbolo);
            if (estadoActual == null) {
                return false;
            }
        }
        return estadosFinales.contains(estadoActual);
    }

    public Set<Set<Integer>> getEstados() {
        return estados;
    }

    public Set<Integer> getEstadoInicial() {
        return estadoInicial;
    }

    public Set<Set<Integer>> getEstadosFinales() {
        return estadosFinales;
    }

    public Map<Set<Integer>, Map<Character, Set<Integer>>> getTransiciones() {
        return transiciones;
    }

    public Set<Character> getAlfabeto() {
        return alfabeto;
    }
}
