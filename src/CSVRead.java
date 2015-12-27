import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVRead {
	
	private final String FILE = "mnisttrain.csv"; //Pego o meu arquivo
	
	private final String split = ","; // Pego o simbolo que separa os valores no arquivo

	private BufferedReader reader; // Meu leitor de arquivo na memoria
	
	public List<Imagem> read() throws IOException {
		
		reader = new BufferedReader(new FileReader(FILE)); // Instancio o leitor de arquivos na memoria passando o meu arquivo
		String conteudoLinha = ""; // Crio uma variavel para guardar o conteudo da linha
		
		int contLinha = 1; // Conto em qual linha esta
		
		List<Imagem> imagens = new ArrayList<>(); // Crio a minha lista de imagens
		
		while((conteudoLinha = reader.readLine()) != null){ // Verifico se existe conteudo na linha
			if(contLinha > 1){ // Verifico se não é a primeira linha do arquivo
				String[] line = conteudoLinha.split(split); // Quebro a minha linha em um array de valores
				int rotulo = Integer.parseInt(line[0]); // Converto o rotulo para inteiro
				int[] matriz = new int[28*28]; // Crio uma lista(matriz) com 784 casas
				int m = 0; // indices da minha linha
				
				for(int i = 0; i < matriz.length; i++){ // Percorro minha matriz para inserir os valores da linha na matriz
					matriz[i] = Integer.parseInt(line[m]); // Converto o valor da linha para inteiro
					//System.err.println(matriz[i]);
					m++; // incremento o indice de casas da linha
				}
				imagens.add(new Imagem(rotulo, matriz)); // crio e adiciono as imagens na minha lista de imagens
			}
			contLinha++; // incremento o indice da linha toda
			
		}
		
		return imagens; // retorno a lista de imagens
		
	}

}
