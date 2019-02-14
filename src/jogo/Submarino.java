/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Anna Milani
 */
public class Submarino {

    private int x;
    private int y;
    private int dx, dy;
    private int altura;
    private int largura;
    private boolean isVisivel;
    private Image imagem;
    private List<Tiro> tiros;
    private AudioClip audio;
    private static final int VELOCIDADE = 2;

    public Submarino() {

        //ImageIcon pic = new ImageIcon(getClass().getResource("/imagens/ois.gif")); 
        ImageIcon pic = new ImageIcon(getClass().getResource("/imagens/nave.png"));
        imagem = pic.getImage();

        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);

        tiros = new ArrayList<Tiro>();

        this.x = 800;
        this.y = 100;

    }

    public void movimento() {

        //System.out.println(x + "," + y); //foi usado para descobrir os limites da borda
        if (!(x + dx < 0 || x + dx > 800)) { //não passar dos limites da tela
            x += dx;
        }

        if (!(y + dy < 0 || y + dy > 475)) {
            y += dy;
        }
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public void atira() {
        this.tiros.add(new Tiro(x - largura, y + altura / 2));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void play(String nomeDoAudio) {
        //URL url = getClass().getResource("gameOver.wav");
        // URL url = getClass().getResource(nomeDoAudio+".wav");
        //audio = Applet.newAudioClip(url);
        // audio.play();
    }

    public void keyPressed(KeyEvent tecla) {

        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE) {
            atira();
            play("tiro");
            Thread thread = new Thread("tiro");
            thread.start();
        }
        if (codigo == KeyEvent.VK_UP) {
            dy = -1 * VELOCIDADE;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 1 * VELOCIDADE;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = -1 * VELOCIDADE;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 1 * VELOCIDADE;
        }

    }

    public void keyReleased(KeyEvent tecla) {

        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

}
