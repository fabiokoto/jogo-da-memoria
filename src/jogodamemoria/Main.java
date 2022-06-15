package jogodamemoria;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DecimalFormat;

public class Main {
    private static double tempo;
    private static boolean incrementa;
    private static Jogo jogo;
    private static Timer cronometro;
    public static void main(String[] args) {
        DecimalFormat formato = new DecimalFormat("0.0");
        tempo = 0;
        incrementa = true;
        
        JFrame janela = new JFrame("Jogo da Memória");
        
        JPanel painel = new JPanel();
        
        JLabel labelPares = new JLabel("Pares encontrados: 0");
        painel.add(labelPares);
        
        JLabel labelTentativas = new JLabel("Tentativas: 0");
        painel.add(labelTentativas);
        
        JLabel labelTempo = new JLabel("Tempo: 0.0s");
        painel.add(labelTempo);
        
        painel.setLayout(new GridLayout(1, 3));
        painel.setBounds(10, 0, 630, 50);
        janela.add(painel);
        
        JButton reiniciar = new JButton("Reiniciar");
        
        reiniciar.setBounds(210, 50, 100, 30);
        janela.add(reiniciar);
        
        reiniciar.addActionListener(ActionListener -> {
            janela.remove(jogo);
            jogo = new Jogo();
            jogo.setBounds(10, 100, 500, 400);
            janela.add(jogo);
            tempo = 0;
            incrementa = true;
        });
        
        jogo = new Jogo();
        jogo.setBounds(10, 100, 500, 400);
        janela.add(jogo);
        
        TimerTask taskCronometro = new TimerTask() {
            @Override
            public void run() {
                if(incrementa) tempo += 0.1;
            }
        };
        
        cronometro = new Timer();
        cronometro.schedule(taskCronometro, 100L, 100L);
        
        Timer timerUpdate = new Timer();
        TimerTask taskUpdate = new TimerTask() {
            @Override
            public void run() {
                int pares = jogo.getParesEncontrados();
                
                if(pares == 10) {
                    labelPares.setText("Você ganhou!");
                    incrementa = false;
                } else labelPares.setText("Pares encontrados: " + pares);
                
                labelTentativas.setText("Tentativas: " + jogo.getTentativas());
                labelTempo.setText("Tempo: " + formato.format(tempo) + "s");
            }
        };
        timerUpdate.schedule(taskUpdate, 100L, 100L);
        
        janela.setSize(536, 548);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}