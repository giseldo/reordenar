package br.reordenar.swing.lixo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * Exemplo de uso de Swing utilizando um JFrame com um JPanel.
 * 
 * Este JFrame possui um JPanel.
 * 
 * @author Juliano D. Carniel ( <a
 *         href="mailto:juliano@portaljava.com">juliano@portaljava.com </a>)
 */
public class MyJFrame extends JFrame {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * Cosntrutor que isntancia um JPanel e o adiciona a este JFrame.
   */
  public MyJFrame() {

    this.setTitle("Minha primeira Tela!");

    this.setSize(800, 600);
    
    //Adiciona a capacidade de fechar a janela
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    //Instancia um novo JPanel
    MyJPanel panel = new MyJPanel(); 
    
    //adicona o JPanel a este JFrame
    this.getContentPane().add( panel ); 
    
    //manda mostrar o JFrame
    this.show(); 
  }

  /**
   * Método para rodar o aplicativo de exemplo.
   * 
   * @param args
   */
  public static void main(String args[])  {
    new MyJFrame();
  }

}

