package br.reordenar.swing.lixo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.reordenar.TratarArquivo;

/**
 * Exemplo de uso de Swing utilizando um JFrame com um JPanel.
 * 
 * Este JPanel faz parte do objeto MyJPanel.
 *  
 * @author Juliano D. Carniel ( <a
 *         href="mailto:juliano@portaljava.com">juliano@portaljava.com</a>)
 *  
 */
public class MyJPanel  extends JPanel implements ActionListener{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * Componente de label
   */
  private JLabel label;

  private JButton botao;

  public MyJPanel()  {
    //Instancia um novo label e um novo botão
    label = new JLabel("Aperte aqui ->");
    botao = new JButton("Botão");
    
    //seta os limites do labbel e do botão
    this.label.setBounds(100,100,100,20);
    this.botao.setBounds(200,100,100,20);

    //Adiciona o label e o botão a este Panel
    this.add(this.label);
    this.add(this.botao);

    //adiciona ao um botão um "escutador", responsável por tratar seus cliques.
    this.botao.addActionListener(this);
  }

  /**
   * Método que trata quando uma ação é executada 
   */
  public void actionPerformed(ActionEvent e)  {
    //Verifica se o objeto onde a ação foi executa é o botão desejado.
    if(e.getSource()==this.botao){
    	
      
    	
      	
      String nomeArquivoOriginal = "c:/temp/ARQ.txt";
      
      // TODO aqui precisa colocar dois 
      
      Set<String> nomeDasTabelas = new HashSet<String>();
      nomeDasTabelas = TratarArquivo.getDistinctComONomeDasTabelas(nomeArquivoOriginal);
      for (String nomeTabela : nomeDasTabelas) {
    	  JOptionPane.showMessageDialog(null, nomeTabela);
      }
    	
      //JOptionPane.showMessageDialog(null,"Você apertou no Botão!");
    }
  }

}
