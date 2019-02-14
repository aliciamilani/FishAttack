/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Anna Milani
 */
public class Inicio extends JFrame implements ActionListener{
    private ImageIcon fundo = new ImageIcon(getClass().getResource("/imagens/tela.png")); // MUDEIII
    private JLabel lfundo = new JLabel(fundo);
    private JButton j = new JButton("JOGAR");
    private JButton s = new JButton("SAIR");
    private JButton i = new JButton("INSTRUÇÕES");

    public Inicio() {
        this.add(lfundo);
        lfundo.add(j);
        lfundo.add(s);
        lfundo.add(i);

        j.addActionListener(this);
        j.setBounds(725,380, 110, 40);
       
        
        i.addActionListener(this);
        i.setBounds(725,430, 110, 40);

        s.addActionListener(this);
        s.setBounds(725, 480, 110, 40);
       
        setTitle("FISH ATTACK!"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Quando apertar em fechar, o programa para
        setSize(900,600);
        setLocationRelativeTo(null);//posição em que a tela aparece quando roda
        setResizable(false);//não deixa o usuario redimencionar a tela
        setVisible(true);//Visibilidade

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == j) {
            new Menu();
            setVisible(false);
        }
        if (e.getSource() == i) {
             JOptionPane.showMessageDialog(null, "O objetivo do jogo é acertar o máximo de inimigos que conseguir sem tocá-los.\n" 
                     +"Para atirar, aperte espaço.\n"
                     +"Para reiniciar o jogo, aperte enter.\n"
                     +"Durante o jogo, é possível movimentar a nave com as setas do teclado.\n "
                     +"Para sair, aperte S.\n"
                     +"Divirta-se!",
                     "INSTRUÇÕES",JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == s) {
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        Inicio inicio = new Inicio();
    }
}

