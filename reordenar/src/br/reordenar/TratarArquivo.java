package br.reordenar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TratarArquivo {

	public static Set<String> getDistinctComONomeDasTabelas(String nomeArquivo) {
		Set<String> nomeDasTabelas = new HashSet<String>();

		
		try {

			FileReader arq = new FileReader(nomeArquivo);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha;

			linha = lerArq.readLine();

			while (linha != null) {


				String novaLinha = linha.substring(12);
				int ultimoEspaco = novaLinha.indexOf(" ");
				String novaTabela = novaLinha.substring(0, ultimoEspaco);

				nomeDasTabelas.add(novaTabela);

				linha = lerArq.readLine();
			}
			lerArq.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nomeDasTabelas;

	}

	public static String[][] getMatrizFromFileComTabelasDesordenadas(Map<String, String> mapComTabelasOrdenadas, String nomeArqOriginal) {

		// TODO pegar a quantidade de linhas do arquivo e substituir por esta constant
		int tamanhoMaximo = 9; 
		
		FileReader arq;

		String[][] matrix = new String[tamanhoMaximo][2];
		try {

			arq = new FileReader(nomeArqOriginal);
			BufferedReader lerArq = new BufferedReader(arq);
			int i = 0;
			String linha = lerArq.readLine();

			while (linha != null) {

				String novaLinha = linha.substring(12);
				int ultimoEspaco = novaLinha.indexOf(" ");
				String tabelaLinha = novaLinha.substring(0, ultimoEspaco);

				String ordemTabela = null;
				ordemTabela = mapComTabelasOrdenadas.get(tabelaLinha);

				matrix[i][0] = ordemTabela;
				matrix[i][1] = linha;

				i++;

				linha = lerArq.readLine();
			}
			arq.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return matrix;
	}

	public static String[][] getMatrizReordenadaFromMatrizDesordenada(String[][] matriz) {
		String aux;
		for (int linha = 0; linha < matriz.length; linha++) {
			for (int i = linha; i < matriz.length; i++) {
				if (Integer.valueOf(matriz[linha][0]) > Integer.valueOf(matriz[i][0])) {

					aux = matriz[linha][0];
					matriz[linha][0] = matriz[i][0];
					matriz[i][0] = aux;

					aux = matriz[linha][1];
					matriz[linha][1] = matriz[i][1];
					matriz[i][1] = aux;

				}

			}
		}

		return matriz;
	}

	public static void generateFileFromMatriz(String[][] matriz, String nomeNovoArquivo) {

		try {

			for (int i = 0; i < matriz.length; i++) {

				FileWriter fw = new FileWriter(nomeNovoArquivo, true);

				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(matriz[i][1]);

				bw.newLine();

				bw.close();
				fw.close();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
