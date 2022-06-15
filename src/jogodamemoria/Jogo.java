package jogodamemoria;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jogo extends JPanel {
    private final Carta[] cartas;
    private Carta ultimaCartaAberta;
    private int paresEncontrados;
    private int tentativas;
    
    public Jogo() {
        cartas = new Carta[20];
        ultimaCartaAberta = null;
        paresEncontrados = 0;
        tentativas = 0;
        Evento evento = new Evento();
        for(int i = 0; i < 20; i++) {
            cartas[i] = new Carta("imgs/img" + (i / 2) + ".png");
        }
        embaralha();
        for(int i = 0; i < 20; i++) {
            cartas[i].addActionListener(evento);
            add(cartas[i]);
        }
        
        setBackground(Color.white);
        setLayout(new GridLayout(4, 5, 5, 5));
        setSize(500, 400);
    }
    
    public int getParesEncontrados() {
        return paresEncontrados;
    }
    
    public int getTentativas() {
        return tentativas;
    }
    
    public void embaralha() {
        for(int i = 0; i < 1000; i++) {
            int x = (int) (Math.random() * 20.0), y = (int) (Math.random() * 20.0);
            Carta aux = cartas[x];
            cartas[x] = cartas[y];
            cartas[y] = aux;
        }
    }
    
    public void fechaCartasNaoEncontradas() {
        for(Carta carta : cartas) {
            if(carta.isAberta() && !carta.isEncontrada())
                carta.fecha();
        }
    }
    
    private class Evento implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento) {
            for(Carta carta : cartas) {
                if(carta == evento.getSource() && !carta.isEncontrada() && carta != ultimaCartaAberta) {
                    carta.abre();
                    if(ultimaCartaAberta == null) {
                        tentativas++;
                        fechaCartasNaoEncontradas();
                        carta.abre();
                        ultimaCartaAberta = carta;
                    } else {
                        if(carta.getLocalImagemAberta().equals(ultimaCartaAberta.getLocalImagemAberta())) {
                            carta.setEncontrada(true);
                            ultimaCartaAberta.setEncontrada(true);
                            paresEncontrados++;
                        }
                        ultimaCartaAberta = null;
                    }
                }
            }
        }
    }
}
