import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Classe do KNN
public class KNNClass {
	
	private List<Imagem> mTreinamento; //Lista de treinamento
	private List<Imagem> mTeste; // Lista de Teste
	
	private int[] mFrequencia; // Lista de frequencia

	public KNNClass(List<Imagem> imagens) {
		// Pego a minha quantidade de treinamento
		int treinamento = (int) (imagens.size() * 0.80); 
		// Da lista total de imagens pego apenas 80% do total para o treinamento
		// que é uma subLista da minha lista total
		this.mTreinamento = imagens.subList(0, treinamento);
		// Da lista total de imagens pego apenas os 20% restantes para a minha lista de Testes
		// que é uma subLista da minha lista total
		this.mTeste = imagens.subList(treinamento, imagens.size());
	}
	
	// Método que classifca as imagens
	public double classificar(int K){
		
		int acertos = 0; // Minha quantidade de acertos
		int m = 0; // A posição da imagem que estou classificando atualmente
		
		for(Imagem imgTeste : mTeste){ // Percorro minha lista de testes
			System.out.println("Aguarde... Classificando a imagem " + m); // Exibo em qual Imagem estou no momento
			m++;
			
			List<DistanciaDeImagem> distanciaDeImagems = new ArrayList<>(); // Crio minha lista de distancias
			
			for (Imagem imgTreino : mTreinamento){ // Percorro a lista de treinamento
				double distancia = distanciaEuclidiana(imgTreino, imgTeste); // Calculo a distancia entre o teste e treinamento
				distanciaDeImagems.add(new DistanciaDeImagem(distancia, imgTreino)); // Adiciono a distancia na minha lista de distancias
			}
			
			Collections.sort(distanciaDeImagems); // Organizo a lista em ordem crescente
			
			this.mFrequencia = new int[]{0,0,0,0,0,0,0,0,0,0}; // Instacio minha lista de frequencia de valores
			
			for(int i = 0; i < K; i++){
				incrementarRotulos(distanciaDeImagems.get(i).getImagem().getmRotulo());
				// Incremento os rotulos na lista de frequencia de acordo com a quantidade de K enviada
			}
			
			//System.err.println(categorizar());
			
			if(categorizar() == imgTeste.getmRotulo()){ // Verifico a quantidade de acertos
				acertos++; // Se acertou incrementa mais um
			}
			
			//System.err.println(acertos);
			
		}
		
		return ((acertos*100)/mTeste.size()); // retorna a porcentagem de acertos no classificador
	}
	
	private void incrementarRotulos(int rotulo){ // Método para incrementar os rotulos
		for(int i = 0; i < mFrequencia.length; i++){ // Percorro minha lista de frequencia
			if(rotulo == i){ // verifico se o rotulo enviado é igual ao rotulo da frequencia
				mFrequencia[i]++; // se sim incremento na lista de frequencia o rotulo correspondente
			}
		}
	}
	
	private int categorizar(){ // Método que categoriza as frequencias com o rotulo da imagem
		int maior = 0; // para salvar o maior valor que aparece
		for(int i = 0; i < mFrequencia.length; i++){ // percorro a lista
			if(mFrequencia[i] > maior){ // verifico se o valor na posição tal é maior que o atual
				maior = i; // salvo o numero do rotulo
			}
		}
		return maior; // retorno o rotulo com maior frequencia que aparece
	}
	
	private double distanciaEuclidiana(Imagem img1, Imagem img2){ // calculo da distancia euclidiana
		double somatorio = 0; // Para salvar o somatorio
		for(int i=0; i < img1.getmMatriz().length; i++){ // Percorro a lista de matriz da imagem
				int diferenca = img1.getmMatriz()[i] - img2.getmMatriz()[i]; // calculo a diferença entre eles
				somatorio += Math.pow(diferenca, 2); // faço a elevação a pontencia e acrescento ao somatorio
		}
		return Math.sqrt(somatorio); // retorno a raiz quadrada do somatorio
	}

}
