package jogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author Anna Milani
 */
public class Menu extends JFrame {
    private Jogo fase= new Jogo();

    public Menu() {
        JMenuBar barraMenu = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        JMenuItem sobre = new JMenuItem("Voltar");
        
        JMenuItem sair = new JMenuItem("Sair");
        
        sobre.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Inicio();
                setVisible(false);
            }
        });

        
        sair.addActionListener(new ActionListener() {
         
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
       

        menu.add(sobre);
        menu.add(new JSeparator());
        menu.add(sair);
       

        barraMenu.add(menu);

        setJMenuBar(barraMenu);

        add(fase);
        setTitle("FISH ATTACK"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Quando apertar em fechar, o programa para
        setSize(900, 600);//tamanho da tela
        setLocationRelativeTo(null);//posição em que a tela aparece quando roda
        setResizable(false);//não deixa o usuario redimencionar a tela
        setVisible(true);//Visibilidade
       
    }
}
