package loja;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loja {

	static Scanner in = new Scanner(System.in);
	static int opc;
	static boolean sair = false;
	static List<String> produto = new ArrayList<>();
	static List<Double> preco = new ArrayList<>();
	static List<Integer> quantidade = new ArrayList<>();

	public static void menu() {

		System.out.println("--- MENU ---");
		System.out.println("1) Cadastrar produtos");
		System.out.println("2) Editar produtos");
		System.out.println("3) Consultar estoque");
		System.out.println("4) Remover item do estoque");
		System.out.println("5) Realizar venda");
		System.out.println("9) Encerrar aplicacao");

		System.out.print("\nInforme a opcao desejada: ");
		opc = in.nextInt();

	}

	public static void cadastrar() {

		consultar();

		System.out.println("--- CADASTRAR ITENS ---");
		System.out.print("Informe o produto: ");
		produto.add(in.next());
		System.out.print("Informe o preco unitario: ");
		preco.add(in.nextDouble());
		System.out.print("Informe a quantidade: ");
		quantidade.add(in.nextInt());

		System.out.println();

	}

	public static void editar() {

		int item;
		int edit;
		consultar();

		System.out.println("--- EDITAR ITENS ---");

		if (produto.size() <= 0) {
			System.out.println("Estoque zerado!!!");

		} else {
			System.out.print("Informe o Cod do item: ");
			item = in.nextInt();

			if (item < produto.size() || item > produto.size()) {
				System.out.println("Codigo invalido!!!");

			} else {
				System.out.println("Qual dado deseja editar?");
				System.out.println("1) Nome do produto");
				System.out.println("2) Preco do produto");
				System.out.println("3) Quantidade do produto em estoque");
				edit = in.nextInt();

				switch (edit) {
				case 1:
					System.out.print("Informe o novo nome para " + produto.get(item - 1) + ": ");
					produto.set((item - 1), in.next());
					break;

				case 2:
					System.out.print("Informe o novo valor para " + produto.get(item - 1) + ": R$");
					preco.set((item - 1), in.nextDouble());
					break;

				case 3:
					System.out.print("Informe a quantia de " + produto.get(item - 1) + " em estoque: ");
					quantidade.set((item - 1), in.nextInt());
					break;

				default:
					System.out.println("Opcao invalida!!!");
					break;
				}
			}

		}

		System.out.println();

	}

	public static void consultar() {

		System.out.println("--- PRODUTOS CADASTRADOS ---");
		System.out.printf("| %3s | %8s | %5s | %3s |\n", "Cod", "Itens", "Preco", "Qtd");

		for (int i = 0; i < produto.size(); i++) {
			System.out.printf("| %3s | %8s | %5s | %3s |\n", (i + 1), produto.get(i), preco.get(i), quantidade.get(i));
		}

		System.out.println();

	}

	public static void remover() {

		int item;
		consultar();

		System.out.println("--- REMOVER PRODUTOS ---");

		if (produto.size() <= 0) {
			System.out.println("Estoque zerado!!!");

		} else {
			System.out.print("Informe o Cod do item: ");
			item = in.nextInt();

			if (item > produto.size()) {
				System.out.println("Codigo invalido!!!");

			} else {
				produto.remove(item - 1);
				preco.remove(item - 1);
				quantidade.remove(item - 1);
			}

		}

		System.out.println();

	}

	public static void vender() {

		int item;
		int qtd;
		String finalizar;
		consultar();

		System.out.println("--- REALIZAR VENDAS ---");

		if (produto.size() <= 0) {
			System.out.println("Estoque zerado!!!");

		} else {
			do {
				System.out.print("Informe o Cod do item: ");
				item = in.nextInt();

				if (item > produto.size()) {
					System.out.println("Codigo invalido!!!");

				} else {
					System.out.print("Qual a quantia de " + produto.get(item - 1) + " que esta saindo? ");
					qtd = in.nextInt();

					if (qtd > quantidade.get(item - 1)) {
						System.out.println("Quantidade invalida!!!");

					} else {
						quantidade.set((item - 1), (quantidade.get(item - 1) - qtd));
					}

				}

				System.out.print("Finalizar compra? [s/n]");
				finalizar = in.next();

			} while (finalizar.equalsIgnoreCase("s"));

		}

		System.out.println();

	}

	public static void main(String[] args) {
		do {
			// chama o metodo MENU
			menu();

			switch (opc) {
			case 1:
				cadastrar();
				break;

			case 2:
				editar();
				break;

			case 3:
				consultar();
				break;

			case 4:
				remover();
				break;

			case 5:
				vender();
				break;

			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opcao invalida!!!\n\n");

			}
		} while (!sair == true);

	}

}
