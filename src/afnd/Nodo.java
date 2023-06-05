/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afnd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author netom
 */
public class Nodo extends JPanel {

    private Set<Trancicion> apunta;
    int estado;
    public boolean estadoFinal;
    public boolean estadoInicial;
    
    public static int radio = 50;
    public boolean ismoved = false;
    int x, y;
    Main main;

    public Nodo(int x, int y, int estado, Main main) {
        this.main = main;
        this.apunta = new HashSet<>();
        this.x = x;
        this.y = y;
        this.estado = estado;
        estadoFinal = false;
        estadoInicial = false;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("q" + estado);
        label.setVisible(true);
        this.add(label, c);

        this.setBounds(x, y, radio, radio);
        this.setVisible(true);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    main.isPanel = true;
                    System.out.println("sse tocvo");
                    main.nodoSelect = estado;
                    marcar();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("dkndkjndjkndkjndijsfbjiefbjwefbiedwsfhv fedhuvwefuhv");

                    if (main.nodoorigen >= 0) {
                        main.nodoDestino = estado;
                        if (main.marcando) {
                            marcar();
                            main.unir();
                        } else {
                            main.nodoorigen = -1;
                            main.nodoDestino = -1;
                        }

                    }
                    if (main.nodoorigen == -1 && main.marcando == false) {
                        main.nodoorigen = estado;
                        System.out.println("hola");
                        marcar();
                        main.marcando = true;
                    }

                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.update(main.getGraphics());
        this.updateUI();
        repaint();
    }
public Nodo(int estado) {
        this.main = null;
        this.apunta = new HashSet<>();
        this.x = 0;
        this.y = 0;
        this.estado = estado;
        estadoFinal = false;
        estadoInicial = false;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("q" + estado);
        label.setVisible(true);
        this.add(label, c);

        this.setBounds(x, y, radio, radio);
        this.setVisible(true);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    main.isPanel = true;
                    System.out.println("sse tocvo");
                    main.nodoSelect = estado;
                    marcar();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("dkndkjndjkndkjndijsfbjiefbjwefbiedwsfhv fedhuvwefuhv");

                    if (main.nodoorigen >= 0) {
                        main.nodoDestino = estado;
                        if (main.marcando) {
                            marcar();
                            main.unir();
                        } else {
                            main.nodoorigen = -1;
                            main.nodoDestino = -1;
                        }

                    }
                    if (main.nodoorigen == -1 && main.marcando == false) {
                        main.nodoorigen = estado;
                        System.out.println("hola");
                        marcar();
                        main.marcando = true;
                    }

                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.update(main.getGraphics());
        this.updateUI();
        repaint();
    }
    public void mover(int x, int y) {
        setBounds(x, y, radio, radio);
        this.x = x;
        this.y = y;
    }

    public void marcar() {
        setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
    }

    public void desmarcar() {
        setBorder(BorderFactory.createLineBorder(Color.RED, 0));
    }

    public void addTrancicion(Trancicion a) {
        apunta.add(a);
    }

    public void removerTrancicion(int nodo) {
        for (Trancicion trancicion : apunta) {
            if (trancicion.getNodo() == nodo) {
                apunta.remove(trancicion);
            }
        }
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public Set<Trancicion> getApunta() {
       
        return apunta;
    }

    public void setApunta(Set<Trancicion> apunta) {
        this.apunta = apunta;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("ksnksn");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
