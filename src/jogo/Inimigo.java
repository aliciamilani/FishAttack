/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Anna Milani
 */
public class Inimigo {

    private int danoRecebido;

    private Random aleatorio;
    private Image imagem;
    private int x;
    private int y;
    private int largura;
    private int altura;
    private int i = 0;
    private boolean isVisivel;
    private ImageIcon inimigo;
    private ArrayList<Tiro> tiros;
    private Timer timer; //trata de repintar as images, atualizando

    private static final int LARGURA_TELA = 900; //MUDEI
    private static final int INICIO_TELA = 0;
    private double velocidade = 0;

    private static int cont = 0;

    public Inimigo(int x, int y, int level) {
        tiros = new ArrayList<Tiro>();
        this.x = x;
        this.y = y;
        
        if (level == 1) {
            danoRecebido = 1;
            velocidade=1;
            inimigo = new ImageIcon(getClass().getResource("/imagens/peixe1.gif"));

        } else {
            if (level == 2) {
                velocidade=1;
                danoRecebido = 2;
                if (cont++ % 3 == 0) {
                    danoRecebido = 2;
                    inimigo = new ImageIcon(getClass().getResource("/imagens/peixe2.gif"));
                } else {
                    danoRecebido = 2;
                    inimigo = new ImageIcon(getClass().getResource("/imagens/inimigo2.gif"));
                }

            } else if (level == 3) {
                velocidade=2;
                danoRecebido = 3;
                if (cont++ % 3 == 0) {
                    danoRecebido = 3;
                    inimigo = new ImageIcon(getClass().getResource("/imagens/peixe3.gif"));
                } else {
                    danoRecebido = 3;
                    inimigo = new ImageIcon(getClass().getResource("/imagens/inimigo3.gif"));
                }

            } else if (level == 4) {
                velocidade=2;
                danoRecebido = 4;
                inimigo = new ImageIcon(getClass().getResource("/imagens/peixe4.gif")); //MUDAR

            } else if (level == 5) {
                velocidade= 1;
                danoRecebido = 20;
                inimigo = new ImageIcon(getClass().getResource("/imagens/inimigo5.gif"));
            }
        }
        imagem = inimigo.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisivel = true;

    }

    public void movimenta() {

        aleatorio = new Random();
        if ((this.x > 900)) { //quando chegou no final da tela
            this.x = INICIO_TELA;

            this.y = aleatorio.nextInt(505);
            //System.out.println(this.y);  /
        } else {
            this.x += velocidade; //inimigo anda de acordo com a velocidade
        }

    }

    public void setDanoRecebido(int dano) {
        danoRecebido = danoRecebido - dano;
    }

    public int getDanoRecebido() {
        return danoRecebido;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void setX() {
        this.x = LARGURA_TELA;
        this.isVisivel = true;
    }

    public void setY(int y) {
        this.y = y;
    }

}
