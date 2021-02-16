package br.com.loja;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LojaController {

	private Scanner tec;

	public LojaController() {
		tec = new Scanner(System.in);

	}

	public int leOpcao() {
		System.out.print("> ");
		return tec.nextInt();

	}

	public void menu() {
		System.out.println("\n--- MENU ---");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Consultar estoque");
		System.out.println("3) Editar itens cadastrados");
		System.out.println("4) Remover itens do estoque");
		System.out.println("5) Realizar vendas");
		System.out.println("6) Historico de compras");
		System.out.println("9) Sair do sistema");
		System.out.println("----------------------------");

	}

	public LojaModel cadastrar() {
		LojaModel loja = new LojaModel();

		System.out.println("--- CADASTRAR ITENS ---");
		System.out.print("Produto: ");
		tec.nextLine(); // limpa o buffer
		loja.setProduto(tec.nextLine());
		System.out.print("Preco: ");
		loja.setPreco(tec.nextFloat());
		System.out.print("Quantidade: ");
		loja.setQtd(tec.nextInt());
		loja.setTotEstoque(loja.getPreco() * loja.getQtd());

		return loja;

	}

	public void consultar(List<LojaModel> produto) {
		System.out.println("--- DADOS CADASTRADOS ---");

		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "Id", "Produto", "Preco", "Qtd", "R$ Total");

		for (int i = 0; i < produto.size(); i++) {
			System.out.printf("| %2d | %10s | R$%6.2f | %4d | R$%7.2f |\n", (i + 1), produto.get(i).getProduto(),
					produto.get(i).getPreco(), produto.get(i).getQtd(), produto.get(i).getTotEstoque());
		}

	}

	public void editar(List<LojaModel> produto) {
		LojaModel prod = new LojaModel();
		int aux, opc;
		this.consultar(produto);

		if (produto.size() <= 0) {
			System.out.println("Estoque zerado.");

		} else {

			System.out.println("--- EDITAR DADOS DE ITENS ---");
			System.out.print("Informe o Id do produto: ");
			aux = tec.nextInt();

			if (aux > produto.size() || aux <= 0) {
				System.out.println("Opcao invalida.");

			} else {

				System.out.println("Qual campo deseja editar?");
				System.out.println("1) Nome do produto");
				System.out.println("2) Valor unitario");
				System.out.println("3) Quantidade");
				opc = tec.nextInt();

				switch (opc) {
				case 1:
					prod.setQtd(produto.get(aux - 1).getQtd());
					prod.setPreco(produto.get(aux - 1).getPreco());
					prod.setTotEstoque(produto.get(aux - 1).getTotEstoque());

					System.out.print("Informe o novo nome para o produto " + produto.get(aux - 1).getProduto() + ": ");
					prod.setProduto(tec.next());
					produto.set(aux - 1, prod);
					break;

				case 2:
					prod.setProduto(produto.get(aux - 1).getProduto());
					prod.setQtd(produto.get(aux - 1).getQtd());

					System.out.print("Informe o novo valor - R$ - para " + produto.get(aux - 1).getProduto() + ": R$");
					prod.setPreco(tec.nextFloat());
					prod.setTotEstoque(prod.getPreco() * produto.get(aux - 1).getQtd());
					produto.set(aux - 1, prod);
					break;

				case 3:
					prod.setProduto(produto.get(aux - 1).getProduto());
					prod.setPreco(produto.get(aux - 1).getPreco());

					System.out.print("Informe a quantidade de " + produto.get(aux - 1).getProduto() + ", em estoque: ");
					prod.setQtd(tec.nextInt());
					prod.setTotEstoque(produto.get(aux - 1).getPreco() * prod.getQtd());
					produto.set(aux - 1, prod);
					break;

				default:
					System.out.println("Opcao invalida!!!");
					break;
				}
			}
		}
	}

	public void remover(List<LojaModel> itens) {
		int aux;
		this.consultar(itens);

		if (itens.size() <= 0) {
			System.out.println("Estoque zerado.");

		} else {

			System.out.println("--- REMOVER ITENS ---");

			System.out.print("Informe o Id do produto a ser removido: ");
			aux = tec.nextInt();

			if (aux > itens.size() || aux < itens.size()) {
				System.out.println("Opcao invalida.");

			} else {
				itens.remove(aux - 1);
				System.out.println("Item removido!!!");

			}

		}
	}

	public void carrinho(List<LojaModel> produtos, List<HistoricoModel> historico) {
		int prod;
		int qtd;
		String opc;
		float total = 0;
		List<LojaModel> aux = new ArrayList<>();
		LojaModel loja;

		this.consultar(produtos);

		System.out.println("--- CARRINHO DE COMPRAS ---");
		do {
			loja = new LojaModel();

			System.out.print("Informe o cod do produto: ");
			prod = tec.nextInt();
			System.out.print("Informe a quantidade de itens: ");
			qtd = tec.nextInt();

			if (qtd > produtos.get(prod - 1).getQtd()) {
				System.out.println("Quantidade invalida.\nValor superior a quantidade em estoque!!!");

			} else {
				loja.setProduto(produtos.get(prod - 1).getProduto());
				loja.setQtd(qtd);
				loja.setPreco(produtos.get(prod - 1).getPreco());
				loja.setTotEstoque(produtos.get(prod - 1).getPreco() * qtd);

				total = total + loja.getTotEstoque();

				aux.add(loja);

			}

			System.out.print("Deseja inserir outro item? [s/n] ");
			opc = tec.next();
		} while (opc.equals("s"));

		// RESCREVE ITENS REPETIDOS
		for (int i = 0; i < aux.size(); i++) {
			for (int j = 0; j < aux.size(); j++) {
				loja = new LojaModel();
				if (aux.get(j).getProduto().equalsIgnoreCase(aux.get(i).getProduto()) && i != j) {
					loja.setProduto(aux.get(i).getProduto());
					loja.setQtd(aux.get(i).getQtd() + aux.get(j).getQtd());
					loja.setPreco(aux.get(i).getPreco());
					loja.setTotEstoque(aux.get(i).getPreco() * loja.getQtd());
					
					aux.set(i, loja);
					aux.remove(j);
				}
			}
		}

		System.out.println("--- CUPOM FISCAL ---");

		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "Id", "Produto", "Preco", "Qtd", "R$ Total");

		for (int i = 0; i < aux.size(); i++) {
			System.out.printf("| %2d | %10s | R$%6.2f | %4d | R$%7.2f |\n", (i + 1), aux.get(i).getProduto(),
					aux.get(i).getPreco(), aux.get(i).getQtd(), aux.get(i).getTotEstoque());
		}

		System.out.printf("\n%37s R$%7.2f\n", "Valor total:", total);

		System.out.print("Finalizar compras? [s/n] ");
		opc = tec.next();

		if (opc.equals("s")) {
			// ATUALIZA LISTA
			for (int i = 0; i < produtos.size(); i++) {
				for (int j = 0; j < aux.size(); j++) {
					if (produtos.get(i).getProduto() == aux.get(j).getProduto()) {
						produtos.set(i, produtos.get(i)).setQtd(produtos.get(i).getQtd() - aux.get(j).getQtd());
						produtos.set(i, produtos.get(i))
								.setTotEstoque(produtos.get(i).getPreco() * produtos.get(i).getQtd());

					}
				}
			}

			// ADD HISTORICO
			Date data = new Date();
			String hora = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(data);

			HistoricoModel hs;

			for (int i = 0; i < aux.size(); i++) {
				hs = new HistoricoModel();

				hs.setProduto(aux.get(i).getProduto());
				hs.setPreco(aux.get(i).getPreco());
				hs.setQtd(aux.get(i).getQtd());
				hs.setTotEstoque(aux.get(i).getPreco() * aux.get(i).getQtd());

				hs.setData(hora);

				historico.add(hs);
			}
		}

		// LIMPA A LISTA
		aux.removeAll(aux);

	}

	public void historico(List<HistoricoModel> historico) {
		float total = 0;
		int qtd = 0;
		System.out.println("--- ITENS ADQUIRIDOS ---");

		System.out.printf("| %2s | %10s | %8s | %4s | %9s | %18s |\n", "Id", "Produto", "Preco", "Qtd", "R$ Total",
				"Hora da Venda");

		for (int i = 0; i < historico.size(); i++) {
			System.out.printf("| %2d | %10s | R$%6.2f | %4d | R$%7.2f | %18s |\n", (i + 1),
					historico.get(i).getProduto(), historico.get(i).getPreco(), historico.get(i).getQtd(),
					historico.get(i).getTotEstoque(), historico.get(i).getData());

			total = total + historico.get(i).getTotEstoque();
			qtd = qtd + historico.get(i).getQtd();
		}

		System.out.printf("\n%37s R$%7.2f\n", "R$ total de vendas:", total);
		System.out.printf("%37s %7d\n", "Qtd de itens vendidos:", qtd);
	}

}
