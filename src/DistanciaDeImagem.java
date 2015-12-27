// Classe para a distancia de imagens que implementa a interface Comparable
// O sort so organiza a lista por padrão se houver valores primitivos, 
// no vaso de objetos a classe devera implementar essa interface
public class DistanciaDeImagem implements Comparable<DistanciaDeImagem> {

	private double distancia;
	private Imagem imagem;

	public DistanciaDeImagem(double distancia, Imagem imagem) {
		this.distancia = distancia;
		this.imagem = imagem;
	}

	public double getDistancia() {
		return distancia;
	}

	public Imagem getImagem() {
		return imagem;
	}

	// Método da interface Comparable para comparar quem é menor ou maior
	@Override
	public int compareTo(DistanciaDeImagem d) {
		if (this.distancia > d.getDistancia())
			return 1;
		else if (this.distancia < d.getDistancia())
			return -1;
		else {
			return 0;
		}
	}

}
