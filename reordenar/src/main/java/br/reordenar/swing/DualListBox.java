package br.reordenar.swing;
/*
Definitive Guide to Swing for Java 2, Second Edition
By John Zukowski     
ISBN: 1-893115-78-X
Publisher: APress
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import br.reordenar.TratarArquivo;

public class DualListBox extends JPanel {

  private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);

  private static final String ADD_BUTTON_LABEL = "Adicionar >>";

  private static final String REMOVE_BUTTON_LABEL = "<< Remover";

  private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Campos das Tabelas";

  private static final String DEFAULT_DEST_CHOICE_LABEL = "Lista Ordenada";

  private JLabel sourceLabel;

  private JList sourceList;

  private SortedListModel sourceListModel;

  private JList destList;

  private SortedListModel destListModel;

  private JLabel destLabel;

  private JButton addButton;

  private JButton regerarButton;
  
  private JButton escolherButton;
  
  
  private JButton removeButton;

  public DualListBox() {
    initScreen();
  }

  public String getSourceChoicesTitle() {
    return sourceLabel.getText();
  }

  public void setSourceChoicesTitle(String newValue) {
    sourceLabel.setText(newValue);
  }

  public String getDestinationChoicesTitle() {
    return destLabel.getText();
  }

  public void setDestinationChoicesTitle(String newValue) {
    destLabel.setText(newValue);
  }

  public void clearSourceListModel() {
    sourceListModel.clear();
  }

  public void clearDestinationListModel() {
    destListModel.clear();
  }

  public void addSourceElements(ListModel newValue) {
    fillListModel(sourceListModel, newValue);
  }

  public void setSourceElements(ListModel newValue) {
    clearSourceListModel();
    addSourceElements(newValue);
  }

  public void addDestinationElements(ListModel newValue) {
    fillListModel(destListModel, newValue);
  }

  private void fillListModel(SortedListModel model, ListModel newValues) {
    int size = newValues.getSize();
    for (int i = 0; i < size; i++) {
      model.add(newValues.getElementAt(i));
    }
  }

  public void addSourceElements(Object newValue[]) {
    fillListModel(sourceListModel, newValue);
  }

  public void setSourceElements(Object newValue[]) {
    clearSourceListModel();
    addSourceElements(newValue);
  }

  public void addDestinationElements(Object newValue[]) {
    fillListModel(destListModel, newValue);
  }

  private void fillListModel(SortedListModel model, Object newValues[]) {
    
	  
	  model.addAll(newValues);
  }

  public Iterator sourceIterator() {
    return sourceListModel.iterator();
  }

  public Iterator destinationIterator() {
    return destListModel.iterator();
  }

  public void setSourceCellRenderer(ListCellRenderer newValue) {
    sourceList.setCellRenderer(newValue);
  }

  public ListCellRenderer getSourceCellRenderer() {
    return sourceList.getCellRenderer();
  }

  public void setDestinationCellRenderer(ListCellRenderer newValue) {
    destList.setCellRenderer(newValue);
  }

  public ListCellRenderer getDestinationCellRenderer() {
    return destList.getCellRenderer();
  }

  public void setVisibleRowCount(int newValue) {
    sourceList.setVisibleRowCount(newValue);
    destList.setVisibleRowCount(newValue);
  }

  public int getVisibleRowCount() {
    return sourceList.getVisibleRowCount();
  }

  public void setSelectionBackground(Color newValue) {
    sourceList.setSelectionBackground(newValue);
    destList.setSelectionBackground(newValue);
  }

  public Color getSelectionBackground() {
    return sourceList.getSelectionBackground();
  }

  public void setSelectionForeground(Color newValue) {
    sourceList.setSelectionForeground(newValue);
    destList.setSelectionForeground(newValue);
  }

  public Color getSelectionForeground() {
    return sourceList.getSelectionForeground();
  }

  private void clearSourceSelected() {
    Object selected[] = sourceList.getSelectedValues();
    for (int i = selected.length - 1; i >= 0; --i) {
      sourceListModel.removeElement(selected[i]);
    }
    sourceList.getSelectionModel().clearSelection();
  }

  private void clearDestinationSelected() {
    Object selected[] = destList.getSelectedValues();
    for (int i = selected.length - 1; i >= 0; --i) {
      destListModel.removeElement(selected[i]);
    }
    destList.getSelectionModel().clearSelection();
  }

  private void initScreen() {
    setBorder(BorderFactory.createEtchedBorder());
    setLayout(new GridBagLayout());
    sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
    sourceListModel = new SortedListModel();
    sourceList = new JList(sourceListModel);
    add(sourceLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
        GridBagConstraints.CENTER, GridBagConstraints.NONE,
        EMPTY_INSETS, 0, 0));
    add(new JScrollPane(sourceList), new GridBagConstraints(0, 1, 1, 5, .5,
        1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        EMPTY_INSETS, 0, 0));

      
    addButton = new JButton(ADD_BUTTON_LABEL);
    add(addButton, new GridBagConstraints(1, 2, 1, 2, 0, .25,
        GridBagConstraints.CENTER, GridBagConstraints.NONE,
        EMPTY_INSETS, 0, 0));
    addButton.addActionListener(new AddListener());
    
    removeButton = new JButton(REMOVE_BUTTON_LABEL);
    add(removeButton, new GridBagConstraints(1, 4, 1, 2, 0, .25,
        GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
            0, 5, 0, 5), 0, 0));
    removeButton.addActionListener(new RemoveListener());

    // TODO RODRIGO regerar buttao
    regerarButton = new JButton(" REORDENAR" );
    add(regerarButton, new GridBagConstraints(1, 7, 1, 2, 0, .25,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                    0, 5, 0, 5), 0, 0));
    regerarButton.addActionListener(new reordenadListener());
    
    
    
    // TODO RODRIGO regerar buttao
    escolherButton = new JButton(" ESCOLHER" );
    add(escolherButton, new GridBagConstraints(1, 10, 1, 2, 0, .25,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                    0, 5, 0, 5), 0, 0));
    escolherButton.addActionListener(new escolherListner());
    
    destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
    destListModel = new SortedListModel();
    destList = new JList(destListModel);
    
    
    add(destLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0,
        GridBagConstraints.CENTER, GridBagConstraints.NONE,
        EMPTY_INSETS, 0, 0));
    add(new JScrollPane(destList), new GridBagConstraints(2, 1, 1, 5, .5,
        1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        EMPTY_INSETS, 0, 0));
  }

 
  static DualListBox dual = new DualListBox();
  
  public static void main(String args[]) {
    JFrame f = new JFrame("Dual List Box Tester");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    DefaultListModel listModel = new DefaultListModel();

    dual.addSourceElements(listModel);
  
    f.getContentPane().add(dual, BorderLayout.CENTER);
    f.setSize(400, 300);
    f.setVisible(true);
  }


  // TODO RODRIGO
  private class reordenadListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	Object selected[] = sourceList.getSelectedValues();
	      
			
			Map<String, String> tabelasOrdenadas = new HashMap<String, String>();
			Set<String> nomeDasTabelas = new HashSet<String>();
			
			for (int i = 0; i < destListModel.getSize(); i++) {
				tabelasOrdenadas.put(String.valueOf(destListModel.getElementAt(i)),String.valueOf(i));
			}
			
			String[][] matrix = TratarArquivo.getMatrizFromFileComTabelasDesordenadas(tabelasOrdenadas, nomeArquivoOriginal);
			TratarArquivo.getMatrizReordenadaFromMatrizDesordenada(matrix);
			TratarArquivo.generateFileFromMatriz(matrix, nomeArquivoNovo);
			JOptionPane.showMessageDialog(null,"Arquivo Reordenado Gerado");
	      
	    }
	  }

  
  private class AddListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object selected[] = sourceList.getSelectedValues();
      addDestinationElements(selected);
      clearSourceSelected();
    }
  }

  private class RemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object selected[] = destList.getSelectedValues();
      addSourceElements(selected);
      clearDestinationSelected();
    }
  }
  
  JFileChooser fc = new JFileChooser();
  String nomeArquivoOriginal;
  String nomeArquivoNovo;
  private class escolherListner implements ActionListener {

	  
		@Override
		public void actionPerformed(ActionEvent e) {
			int returnVal = fc.showOpenDialog(DualListBox.this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
				try {
					nomeArquivoOriginal = file.getCanonicalPath();
					nomeArquivoNovo = nomeArquivoOriginal.replace(".txt", "_Reordenado.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// TODO RODRIGO MUDAR NOME DO ARQUIVO
			    DefaultListModel listModel = new DefaultListModel();
			 
			    Set<String> nomeDasTabelas = new HashSet<String>();
			    nomeDasTabelas = TratarArquivo.getDistinctComONomeDasTabelas(nomeArquivoOriginal);
			    for (String nomeTabela : nomeDasTabelas) {
			    	listModel.addElement(nomeTabela);
			    }
			    dual.clearSourceListModel();
			    dual.clearDestinationListModel();
			    dual.addSourceElements(listModel);
				
				
			}
			
		}

  }



}

class SortedListModel extends AbstractListModel {

  List model;

  
  public SortedListModel() {
    model = new ArrayList();
    
  }

  public int getSize() {
    return model.size();
  }

  public Object getElementAt(int index) {
    return model.toArray()[index];
  }

  public void add(Object element) {
    if (model.add(element)) {
      fireContentsChanged(this, 0, getSize());
    }
  }

  public void addAll(Object elements[]) {
    Collection c = Arrays.asList(elements);
    model.addAll(c);
    fireContentsChanged(this, 0, getSize());
  }

  public void clear() {
    model.clear();
    fireContentsChanged(this, 0, getSize());
  }

  public boolean contains(Object element) {
    return model.contains(element);
  }

 

  public Iterator iterator() {
    return model.iterator();
  }

 

  public boolean removeElement(Object element) {
    boolean removed = model.remove(element);
    if (removed) {
      fireContentsChanged(this, 0, getSize());
    }
    return removed;
  }
  
 
  
  
}

