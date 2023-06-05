/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package afnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author netom
 */
public class Main extends javax.swing.JFrame {

    public Set<String> alfabeto;
    public boolean isPanel = false;
    ArrayList<Nodo> nodos;
    public int nodoSelect = -1;
    public int nodoorigen = -1;
    public int nodoDestino = -1;
    Fondo fondo;
    public boolean marcando = false;

    void a() {
        alfabeto.add("a");
        alfabeto.add("b");
        Nodo n1 = new Nodo(10, 10, 0, this);
        n1.estadoInicial = true;
        n1.addTrancicion(new Trancicion("a", 1));
        n1.addTrancicion(new Trancicion("a", 2));
        nodos.add(n1);

        Nodo n2 = new Nodo(20, 20, 1, this);
        n2.addTrancicion(new Trancicion("a", 1));
        n2.addTrancicion(new Trancicion("a", 2));
        nodos.add(n2);

        Nodo n3 = new Nodo(30, 30, 2, this);
        n3.addTrancicion(new Trancicion("a", 1));
        n3.addTrancicion(new Trancicion("b", 3));
        n3.addTrancicion(new Trancicion("b", 4));
        nodos.add(n3);

        Nodo n4 = new Nodo(40, 40, 3, this);
        n4.addTrancicion(new Trancicion("b", 3));
        n4.addTrancicion(new Trancicion("b", 4));
        n4.addTrancicion(new Trancicion("a", 1));
        nodos.add(n4);
        Nodo n5 = new Nodo(40, 40, 4, this);

        nodos.add(n5);
    }

    void menu() {
        String[] opciones = {"Evaluar cadena", "Conversion AFND a AFD", "Reduccion", "Salir"};

        int seleccion = 0;
        do {
            seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Selecciona una función:",
                    "Menú de opciones",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    funcion1();
                    break;
                case 1:
                    AFND_to_AFD();
                    break;
                case 2:
                    Desminucion();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (seleccion != 3);

    }

    public Main() {
        initComponents();
        fondo = new Fondo();
        alfabeto = new HashSet<>();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Enter");

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    menu();
                }
                if (e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()) {
                    if (nodoSelect > -1) {
                        nodos.get(nodoSelect).estadoFinal = !nodos.get(nodoSelect).estadoFinal;
                        if (nodos.get(nodoSelect).estadoFinal) {
                            nodos.get(nodoSelect).setBackground(Color.BLUE);
                        } else {
                            nodos.get(nodoSelect).setBackground(Color.RED);
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()) {
                    if (nodoSelect > -1) {
                        nodos.remove(nodoSelect);
                        nodoSelect = -1;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        nodos = new ArrayList<>();
        this.setLayout(null); // Desactivar el diseño predeterminado para colocar componentes manualmente
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println(e);
                    if (isPanel == false) {
                        nuevoNodo(e.getX() - Nodo.radio / 2, e.getY() - 30 - Nodo.radio / 2);

                    } else {
                        if (isPanel && nodoSelect >= 0) {
                            int x = e.getX();
                            int y = e.getY() - 30;
                            nodos.get(nodoSelect).mover(x, y);
                            nodos.get(nodoSelect).getY();
                            System.out.println(nodos.get(nodoSelect).getY());
                            nodos.get(nodoSelect).desmarcar();
                        }
                        isPanel = false;
                    }

                } else {
                    marcando = false;
                    nodoDestino = -1;
                    nodoorigen = -1;

                }
                actualizar();
            }

        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {

            }
        });
        //    a();
    }

    public void unir() {
        if (nodoorigen >= 0 && nodoDestino >= 0) {
            System.out.println("vhbvshvs");
            String inputValue = JOptionPane.showInputDialog("Ingrese su nombre:");
            if (inputValue != null) {
                Trancicion t = new Trancicion(inputValue, nodoDestino);
                alfabeto.add(inputValue);
                Point inicio = nodos.get(nodoorigen).getLocation();
                Point fin = nodos.get(nodoDestino).getLocation();
                t.setInicio(inicio);
                t.setFin(fin);
                nodos.get(nodoorigen).addTrancicion(t);
                nodoDestino = -1;
                nodoorigen = -1;
                this.repaint();
                this.update(this.getGraphics());
                System.out.println("tancicion");
                marcando = false;

            } else {
                // El usuario ha hecho clic en "Cancelar"
                System.out.println("El usuario ha cancelado la operación");

            }
            marcando = false;
            nodoDestino = -1;
            nodoorigen = -1;
            actualizar();
        }
    }

    void nuevoNodo(int x, int y) {
        Nodo panelNuevo = new Nodo(x, y, nodos.size(), this);
        if (nodos.size() == 0) {
            panelNuevo.estadoInicial = true;
        }
        nodos.add(panelNuevo);
        addNodo(nodos.get(nodos.size() - 1));

    }

    public void actualizar() {
        System.out.println("Actualizar");
        update(this.getGraphics());
        repaint();
        getContentPane().removeAll();
        for (Nodo nodo : nodos) {
            nodo.desmarcar();
            this.add(nodo);
        }
    }

    void addNodo(Nodo nodo) {
        this.add(nodo);
        update(this.getGraphics());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        int desface = 30 + Nodo.radio / 2;
        super.paint(g);
        System.out.println("hola adios");
        Graphics2D g2d = (Graphics2D) g;
        for (Nodo nodo : nodos) {
            int nodosDiferentes = 0;
            int nodosDiferentesOpuestos = 0;
            int nodosIguales = 0;
            int regreso = -1;
            for (Trancicion t : nodo.getApunta()) {
                if ((t.getNodo() != nodo.getEstado())) {
                    if (nodos.get(t.getNodo()) == null) {
                        nodo.getApunta().remove(t);

                    } else {

                        if (t.getNodo() > nodo.getEstado()) {
                            int posx1 = nodo.getX() + Nodo.radio / 2;
                            int posy1 = nodo.getY() + desface;
                            int posx2 = nodos.get(t.getNodo()).getX() + Nodo.radio / 2;
                            int posy2 = nodos.get(t.getNodo()).getY() + desface;
                            posy1 = posy1 - Nodo.radio / 2;
                            posy2 = posy2 - Nodo.radio / 2;
                            int xMax = Math.max(posx2, posx1);
                            int xmin = Math.min(posx2, posx1);
                            int yMax = Math.max(posy2, posy1);
                            int ymin = Math.min(posy2, posy1);
                            nodosDiferentesOpuestos=0;
                            for (Trancicion t2 : nodo.getApunta()) {
                                if (t2.getNodo() == t.getNodo()) {
                                    nodosDiferentesOpuestos++;
                                }

                            }
                            if (nodosDiferentesOpuestos == 1) {
                                int x = (xMax - ((xMax - xmin) / 2));
                                int y = (yMax - ((yMax - ymin) / 2));

                                g2d.drawLine(posx1, posy1, posx2, posy2);
                                g2d.fillOval(posx2, posy2, 5, 5);
                                Font font = new Font("Serif", Font.PLAIN, 24);
                                g2d.setFont(font);
                                g2d.drawString(t.getValor(), x, y);
                                //nodosDiferentesOpuestos++;
                            } else if (nodosDiferentesOpuestos >= 2) {

                                // Dibuja la letra en el medio de la línea
                                Font font = new Font("Serif", Font.PLAIN, 24);
                                FontMetrics metrics = g2d.getFontMetrics(font);
                                int x = (xMax - ((xMax - xmin) / 2) + metrics.stringWidth("," + t.getValor()));
                                int y = (yMax - ((yMax - ymin) / 2));
                                g2d.setFont(font);
                                g2d.drawString(" ," + t.getValor(), x, y);

                            }
                        } else if (t.getNodo() < nodo.getEstado()) {
                            int posx1 = nodo.getX() + Nodo.radio / 2;
                            int posy1 = nodo.getY() + desface;
                            int posx2 = nodos.get(t.getNodo()).getX() + Nodo.radio / 2;
                            int posy2 = nodos.get(t.getNodo()).getY() + desface;
                            posy1 = posy1 + Nodo.radio / 2;
                            posy2 = posy2 + Nodo.radio / 2;
                            int xMax = Math.max(posx2, posx1);
                            int xmin = Math.min(posx2, posx1);
                            int yMax = Math.max(posy2, posy1);
                            int ymin = Math.min(posy2, posy1);
                            nodosDiferentes=0;
                            for (Trancicion t2 : nodo.getApunta()) {
                                if (t2.getNodo() == t.getNodo()) {
                                    nodosDiferentes++;
                                }

                            }
                            if (nodosDiferentes == 1) {
                                int x = (xMax - ((xMax - xmin) / 2));
                                int y = (yMax - ((yMax - ymin) / 2));

                                // Dibuja la letra en el medio de la línea
                                g2d.drawLine(posx1, posy1, posx2, posy2);
                                g2d.fillOval(posx2, posy2, 5, 5);
                                Font font = new Font("Serif", Font.PLAIN, 24);
                                FontMetrics metrics = g2d.getFontMetrics(font);
                                g2d.setFont(font);
                                g2d.drawString(t.getValor(), x, y);
                                //nodosDiferentes++;
                            } else if (nodosDiferentes >= 2) {
                                Font font = new Font("Serif", Font.PLAIN, 24);
                                FontMetrics metrics = g2d.getFontMetrics(font);
                                g2d.setFont(font);
                                int x = (xMax - ((xMax - xmin) / 2) + metrics.stringWidth("," + t.getValor()));
                                int y = (yMax - ((yMax - ymin) / 2));
                                // Dibuja la letra en el medio de la línea     
                                g2d.drawString("," + t.getValor(), x, y);

                            }
                        }

                    }
                }
                System.out.println(t.getNodo() == nodo.getEstado());
                System.out.println(t.getNodo() + "No es igual" + nodo.getEstado());
                if ((t.getNodo() == nodo.getEstado())) {
                    if (nodosIguales == 0) {
                        int posx1 = nodo.getX() + Nodo.radio / 2 - Nodo.radio / 4;
                        int posy1 = nodo.getY() + Nodo.radio / 4;
                        g2d.drawArc(posx1, posy1, Nodo.radio / 2 + Nodo.radio / 4, Nodo.radio + Nodo.radio / 4, 0, 180);
                        // Dibuja la letra en el medio de la línea
                        Font font = new Font("Serif", Font.PLAIN, 24);

                        g2d.setFont(font);
                        g2d.drawString(t.getValor(), posx1, nodo.getY());
                        nodosIguales++;
                    } else if (nodosIguales == 1) {

                        int posx1 = nodo.getX() + Nodo.radio / 2;
                        int posy1 = nodo.getY() + desface;
                        Font font = new Font("Serif", Font.PLAIN, 24);
                        g2d.setFont(font);
                        g2d.drawString(t.getValor(), posx1, posy1);
                        nodosIguales++;

                    }
                }

            }
        }

    }

    private void AFND_to_AFD() {
        Tabla tabla = new Tabla(nodos, alfabeto);
        nodos = tabla.get(this);
        actualizar();

    }

    private void Desminucion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void funcion1() {
        String a = JOptionPane.showInputDialog("ingresa Cadena");
        boolean noOk = true;
        if (a != null && !a.isEmpty()) {
            char[] arr = a.toCharArray();

            for (int i = 0; i < arr.length; i++) {
                if (!alfabeto.contains("" + arr[i])) {
                    JOptionPane.showMessageDialog(this, "La cadena contiene elementos no evaluables");
                    noOk = false;
                }
            }
            if (noOk == true) {
                analizarCadena analizador = new analizarCadena(nodos, arr);

                JOptionPane.showMessageDialog(this, analizador.analizar(0, 0), "resultadio", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
