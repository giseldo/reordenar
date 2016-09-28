package br.reordenar.test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import br.reordenar.TratarArquivo;

public class TesPrincipal {

	@Test
	public void testGerarMapOrdenado() {
		
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
			
			//tabelasOrdenadas.put("PESSOASM", "2");
			//tabelasOrdenadas.put("PESSOAS","1");

		}
				
		String[][] matrix = TratarArquivo.getMatrizFromFileComTabelasDesordenadas(tabelasOrdenadas, nomeArquivoOriginal);
		
		TratarArquivo.getMatrizReordenadaFromMatrizDesordenada(matrix);
		TratarArquivo.generateFileFromMatriz(matrix, nomeNovoArquivo);
		
		assertTrue(true);
		
	}
	
	
	
}
