/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author netom
 */
public class ConversionAFNDaAFD  {
   private int numEstados;
    private Set<Integer> estadosFinales;
    private Map<Integer, Map<Character, Integer>> transiciones;
 public ConversionAFNDaAFD(AFND afnd) {
        numEstados = 0;
        estadosFinales = new HashSet<>();
        transiciones = new HashMap<>();
        Map<Set<Integer>, Integer> subconjuntos = new HashMap<>();
        Queue<Set<Integer>> cola = new LinkedList<>();
        Set<Integer> estadoInicial = cierreEpsilon(afnd, Collections.singleton(0));
        subconjuntos.put(estadoInicial, numEstados++);
        cola.offer(estadoInicial);
        while (!cola.isEmpty()) {
            Set<Integer> estado = cola.poll();
            if (esEstadoFinal(afnd, estado)) {
                estadosFinales.add(subconjuntos.get(estado));
            }
            Map<Character, Set<Integer>> transiciones = transiciones(afnd, estado);
            for (Character simbolo : transiciones.keySet()) {
                Set<Integer> siguienteEstado = cierreEpsilon(afnd, transiciones.get(simbolo));
                if (!subconjuntos.containsKey(siguienteEstado)) {
                    subconjuntos.put(siguienteEstado, numEstados++);
                    cola.offer(siguienteEstado);
                }
                transiciones.computeIfAbsent(simbolo, k -> new HashSet<>());
                transiciones.get(simbolo).add(subconjuntos.get(siguienteEstado));
            }
            this.transiciones.put(subconjuntos.get(estado), transiciones.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().iterator().next())));
        }
    }

    private Set<Integer> cierreEpsilon(AFND afnd, Set<Integer> estados) {
        Set<Integer> cierre = new HashSet<>(estados);
        Queue<Integer> cola = new LinkedList<>(estados);
        while (!cola.isEmpty()) {
            int estado = cola.poll();
            Map<Character, Set<Integer>> transiciones = this.transiciones(afnd, estados);
            if (transiciones.containsKey(null)) {
                for (int siguienteEstado : transiciones.get(null)) {
                    if (!cierre.contains(siguienteEstado)) {
                    cierre.add(siguienteEstado);
                    cola.offer(siguienteEstado);
                }
            }
        }
    }
    return cierre;
}

private boolean esEstadoFinal(AFND afnd, Set<Integer> estados) {
    for (int estado : estados) {
        if (afnd.getEstadosFinales().contains(estado)) {
            return true;
        }
    }
    return false;
}

private Map<Character, Set<Integer>> transiciones(AFND afnd, Set<Integer> estados) {
    Map<Character, Set<Integer>> transiciones = new HashMap<>();
    for (int estado : estados) {
        Map<Character, Set<Integer>> transicionesEstado = afnd.getTransiciones().getOrDefault(estado, Collections.emptyMap());
        for (Map.Entry<Character, Set<Integer>> transicion : transicionesEstado.entrySet()) {
            Character simbolo = transicion.getKey();
            Set<Integer> siguientesEstados = transicion.getValue();
            transiciones.computeIfAbsent(simbolo, k -> new HashSet<>());
            transiciones.get(simbolo).addAll(siguientesEstados);
        }
    }
    return transiciones;
}
  
}
