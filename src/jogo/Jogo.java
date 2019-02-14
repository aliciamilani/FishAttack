/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Anna Milani
 */
public class Jogo extends JPanel implements ActionListener {

    private boolean vitoria;
    private Random aleatorio;
    private Image fundo;
    private Submarino sub;
    private Timer timer; //trata de repintar as images, atualizando
    private int x;
    private int y = 0;
    private int cont = 0;
    private int fase = 0;
    private int qtdInimigo = 0;
    private boolean jogando;
    private static int vida = 3;
    private AudioClip audio;
    private List<Inimigo> inimigos;
    private int[][] coordenada = new int[400][2];
    private ImageIcon imagem;
    private ImageIcon fimJogo;
    private ImageIcon zerado;
    private URL url = getClass().getResource("gameOver.wav");
   

    public Jogo() {
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(new TecladoAdapter());

        play("musica");
        imagem = new ImageIcon(getClass().getResource("/imagens/fundo.jpg"));
        fundo = imagem.getImage();

        sub = new Submarino();

        jogando = true;
        qtdInimigo = 15;
        fase = 1;
        InicializaInimigos(fase, qtdInimigo);

        timer = new Timer(5, this); // a cada 5 mil segundos, essa classe será chamada para ser atualizada
        timer.start(); //metodo que inicia o timer

    }

    public void InicializaInimigos(int fase, int qtdInimigo) {
        aleatorio = new Random();
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < qtdInimigo; i++) {
            coordenada[i][0] = aleatorio.nextInt(2000) * -1; //y dos inimigos
            //if (coordenada[i][0] < 900) { //Coordenadas x
                //i++;
           //}
        }

        for (int i = 0; i < qtdInimigo; i++) {
            coordenada[i][1] = aleatorio.nextInt(510);//Recebe a posição de y de forma aleatoria
        }

        for (int i = 0; i < qtdInimigo; i++) {
            inimigos.add(new Inimigo(coordenada[i][0], coordenada[i][1], fase));
        }

    }

    public void play(String nomeDoAudio) {
        //URL url = getClass().getResource("gameOver.wav");
        URL url = getClass().getResource(nomeDoAudio + ".wav");
        audio = Applet.newAudioClip(url);
        audio.play();
    }

    public void paintComponent(Graphics g) {

        Graphics2D tela = (Graphics2D) g;
        tela.drawImage(fundo, 0, 0, null);

        if ((jogando == true) && (vida <= 3) && (vida > 0)) {

            tela.drawImage(sub.getImagem(), sub.getX(), sub.getY(), this); //Desenha o submarino

            List<Tiro> tiros = sub.getTiros(); //instancia os tiros

            for (int i = 0; i < tiros.size(); i++) {
                Tiro t = (Tiro) tiros.get(i);
                tela.drawImage(t.getImagem(), t.getX(), t.getY(), this);
            }

            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo in = (Inimigo) inimigos.get(i);
                tela.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            setFont(new Font("TimesRoman", Font.BOLD, 20));

            tela.setColor(Color.WHITE);
            tela.drawString("Mortos:" + cont, 400, 20);

            tela.setColor(Color.WHITE);
            tela.drawString("Fase:" + fase, 10, 20);

            tela.setColor(Color.WHITE);
            tela.drawString("Vidas:" + vida, 820, 20);

            if (vitoria == true) {
                zerado = new ImageIcon(getClass().getResource("/imagens/zerado.png"));
                tela.drawImage(zerado.getImage(), 0, 0, null);
            }

        } else if ((vitoria == false) || (vida == 0)) {
            audio.stop();
            fimJogo = new ImageIcon(getClass().getResource("/imagens/gameover.png"));
            tela.drawImage(fimJogo.getImage(), 0, 0, null);

        }
        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Tiro> misseis = sub.getTiros();

        for (int i = 0; i < misseis.size(); i++) {

            Tiro m = (Tiro) misseis.get(i);

            if (m.isVisivel()) {
                m.movimenta();
            } else {
                misseis.remove(i);
            }

        }

        for (int i = 0; i < inimigos.size(); i++) {

            Inimigo in = (Inimigo) inimigos.get(i);

            if (in.isVisivel()) {
                in.movimenta();
            } else {
                inimigos.remove(i);
            }

        }

        sub.movimento();
        checarColisoes();
        repaint();
    }

    public void checarColisoes() {

        Rectangle formaNave = sub.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;

        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();

            if (formaNave.intersects(formaInimigo)) {
                vida--;
                tempInimigo.setVisivel(false);
                tempInimigo.setX();

                jogando = true;

                if (vida == 0) {
                    sub.setVisivel(false);
                    tempInimigo.setVisivel(false);
                    jogando = false;
                    vitoria = false;
                }
            }
        }

        List<Tiro> misseis = sub.getTiros();

        for (int i = 0; i < misseis.size(); i++) {

            Tiro tempMissel = misseis.get(i);
            formaMissel = tempMissel.getBounds();

            for (int j = 0; j < inimigos.size(); j++) {

                Inimigo tempInimigo = inimigos.get(j);
                formaInimigo = tempInimigo.getBounds();

                if (formaMissel.intersects(formaInimigo)) {
                    tempInimigo.setDanoRecebido(1);

                    if (tempInimigo.getDanoRecebido() == 0) {

                        tempInimigo.setVisivel(false);
                        tempMissel.setVisivel(false);
                        cont++;
                    } else {
                        tempInimigo.setVisivel(true);
                        tempMissel.setVisivel(false);

                    }

                    if ((cont == 15) && (fase == 1) && (vida <= 3) && (vida > 0)) {
                        cont = 0;
                        jogando = true;
                        fase++;
                        sub = new Submarino();
                        InicializaInimigos(fase, 20);
                    }
                    if ((cont == 20) && (fase == 2) && (vida <= 3) && (vida > 0)) {
                        cont = 0;
                        jogando = true;
                        fase++;
                        sub = new Submarino();
                        InicializaInimigos(fase, 25);
                    }
                    if ((cont == 25) && (fase == 3) && (vida <= 3) && (vida > 0)) {
                        cont = 0;
                        jogando = true;
                        fase++;
                        sub = new Submarino();
                        InicializaInimigos(fase, 30);
                    }
                    if ((cont == 30) && (fase == 4) && (vida <= 3) && (vida > 0)) {
                        cont = 0;
                        jogando = true;
                        fase++;
                        sub = new Submarino();
                        InicializaInimigos(fase, 1);
                    }
                    if ((cont == 1) && (fase == 5) && (vida <= 3) && (vida > 0)) {
                        vitoria = true;
                    }
                }
            }
        }
    }

    private void drawImage(Image image, int i, int i0, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_S) {
                System.exit(0);
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                cont = 0;
                fase = 1;
                jogando = true;
                vida = 3;
                sub = new Submarino();
                InicializaInimigos(1, 15);

            }
            sub.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            sub.keyReleased(e);
        }
    }

    public boolean getvitoria() {
        return vitoria;
    }
}
 