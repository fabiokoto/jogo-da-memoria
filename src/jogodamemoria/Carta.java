package jogodamemoria;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Carta extends JButton {
    private boolean aberta;
    private boolean encontrada;
    private ImageIcon imagemAberta;
    private String localImagemAberta;
    
    private final ImageIcon imagemFechada;
    
    public Carta(String localImagem) {
        localImagemAberta = localImagem;
        imagemFechada = new ImageIcon("imgs/img.png");
        imagemFechada.setImage(imagemFechada.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        imagemAberta = new ImageIcon(localImagem, "Carta");
        imagemAberta.setImage(imagemAberta.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        aberta = false;
        setIcon(imagemFechada);
        setBounds(0, 0, 50, 50);
    }
    
    public boolean isAberta() {
        return aberta;
    }
    
    public void setAberta(boolean aberta) {
        this.aberta = aberta;
        atualiza();
    }

    public boolean isEncontrada() {
        return encontrada;
    }

    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }
    
    public ImageIcon getImagem() {
        return imagemAberta;
    }
    
    public void setImagem(ImageIcon imagem) {
        imagemAberta = imagem;
    }

    public String getLocalImagemAberta() {
        return localImagemAberta;
    }

    public void setLocalImagemAberta(String localImagemAberta) {
        this.localImagemAberta = localImagemAberta;
    }
    
    public void abre() {
        aberta = true;
        atualiza();
    }
    
    public void fecha() {
        aberta = false;
        atualiza();
    }
    
    public void vira() {
        if(aberta) fecha();
        else abre();
    }
    
    public void atualiza() {
        if(aberta) setIcon(imagemAberta);
        else setIcon(imagemFechada);
    }
}
