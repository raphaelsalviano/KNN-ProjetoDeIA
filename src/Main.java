import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		// Entrada de dados
		scanner = new Scanner(System.in);

		System.out.print("Insira um valor para K: ");
		
		try {
			int k = scanner.nextInt(); // Pego o numero de K
			System.out.println(
					"O algoritimo teve " + new KNNClass(new CSVRead().read()).classificar(k) + "% de sucesso.");
			// Exibo a porcentagem de acertos
		} catch (IOException e) {
			e.getMessage(); // Lanço minha Excessão
		}

	}

}
