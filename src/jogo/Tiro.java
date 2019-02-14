/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Anna Milani
 */
public class Tiro {
    private int dano;
    private Image imagem;
    private int x;
    private int y;
    private int largura;
    private int altura;
    private boolean isVisivel;
    
    private static final int LARGURA_TELA = 900; 
    private static final int INICIO_TELA = 0;
    private static final int VELOCIDADE = 3;
    
    public Tiro(int x, int y){
        this.x = x;
        this.y = y;
        
        ImageIcon pic = new ImageIcon(getClass().getResource("/imagens/tiro.png"));
        imagem = pic.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        
        isVisivel = true;
        
    }
    public void movimenta(){
         this.x -= VELOCIDADE; //somar a posição com a velocidade
         
         if(this.x < INICIO_TELA){
             isVisivel = false;
         }
    }
    
    public int getDano(){
        return dano;
    }
    public boolean isVisivel(){
        return isVisivel;
    }
    
    public void setVisivel(boolean isVisivel){
        this.isVisivel = isVisivel;
    }
    
    public Image getImagem(){
        return imagem;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }
    
}
