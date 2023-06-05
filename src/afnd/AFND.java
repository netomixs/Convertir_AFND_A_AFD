/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afnd;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author netom
 */
public class AFND {
    private Set<Integer> estados;
    private Set<Integer> estadoInicial;
    private Set<Integer> estadosFinales;
    private Map<Integer, Map<Character, Set<Integer>>> transiciones;
    private Set<Character> alfabeto;

    public AFND(Set<Integer> estados, Set<Integer> estadoInicial, Set<Integer> estadosFinales, Map<Integer, Map<Character, Set<Integer>>> transiciones, Set<Character> alfabeto) {
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
        this.transiciones = transiciones;
        this.alfabeto = alfabeto;
    }

    public boolean aceptaCadena(String cadena) {
        Set<Integer> estadosActuales = cerraduraEpsilon(estadoInicial);
        for (char simbolo : cadena.toCharArray()) {
            Set<Integer> nuevosEstadosActuales = new HashSet<>();
            for (int estadoActual : estadosActuales) {
                Set<Integer> estadosDestino = transiciones.get(estadoActual).get(simbolo);
                if (estadosDestino != null) {
                    nuevosEstadosActuales.addAll(estadosDestino);
                }
            }
            estadosActuales = cerraduraEpsilon(nuevosEstadosActuales);
        }
        return !Collections.disjoint(estadosActuales, estadosFinales);
    }

    private Set<Integer> cerraduraEpsilon(int estado) {
        Set<Integer> estadosVisitados = new HashSet<>();
        Stack<Integer> pila = new Stack<>();
        pila.push(estado);
        while (!pila.isEmpty()) {
            int estadoActual = pila.pop();
            if (estadosVisitados.contains(estadoActual)) {
                continue;
            }
            estadosVisitados.add(estadoActual);
            Set<Integer> estadosDestino = transiciones.get(estadoActual).get(null);
            if (estadosDestino != null) {
                pila.addAll(estadosDestino);
            }
        }
        return estadosVisitados;
    }

    private Set<Integer> cerraduraEpsilon(Set<Integer> estados) {
        Set<Integer> cerradura = new HashSet<>();
        for (int estado : estados) {
            cerradura.addAll(cerraduraEpsilon(estado));
        }
        return cerradura;
    }

    public Set<Integer> getEstados() {
        return estados;
    }

    public Set<Integer> getEstadoInicial() {
        return estadoInicial;
    }

    public Set<Integer> getEstadosFinales() {
        return estadosFinales;
    }

    public Map<Integer, Map<Character, Set<Integer>>> getTransiciones() {
        return transiciones;
    }

    public Set<Character> getAlfabeto() {
        return alfabeto;
    }
}
