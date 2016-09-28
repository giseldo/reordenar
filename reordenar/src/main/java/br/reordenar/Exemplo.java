package br.reordenar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Exemplo {
	public static void main(String[] args) {
		
		String nomeArquivoOriginal = "C:/Temp/ARQ.txt";
		String nomeNovoArquivo = "C:/Temp/ARQ_NOVO.txt";
		
		Map<String, String> tabelasOrdenadas = new HashMap<String, String>();
		Set<String> nomeDasTabelas = new HashSet<String>();

		nomeDasTabelas = TratarArquivo.getDistinctComONomeDasTabelas(nomeArquivoOriginal); 
		
		// TODO Aqui estou reordenando as tabelas, aqui precisa de uma tela.
		int i = 1;
		for (String nomeTabela : nomeDasTabelas) {
			tabelasOrdenadas.put(nomeTabela,String.valueOf(i));
			i ++;
		}
				
		String[][] matrix = TratarArquivo.getMatrizFromFileComTabelasDesordenadas(tabelasOrdenadas, nomeArquivoOriginal);
		
		TratarArquivo.getMatrizReordenadaFromMatrizDesordenada(matrix);
		TratarArquivo.generateFileFromMatriz(matrix, nomeNovoArquivo);
		
	}
}
