package br.com.loja;

import java.util.ArrayList;
import java.util.List;

public class LojaView {

	public static void main(String[] args) {
		
		//Armazena dados cadastrados
		List<LojaModel> produto = new ArrayList<>();
		List<HistoricoModel> historico = new ArrayList<>();
		
		//Cria um objeto do Controller
		LojaController loja = new LojaController();
		
		//Controle do loop de saida
		boolean sair = false;
		
		do {
			//chama metodo MENU()
			loja.menu();
			
			// Le uma opcao para o switch case
			int opc = loja.leOpcao();
			System.out.println("");
			
			switch (opc) {
			case 1:
				//cadastrar
				produto.add(loja.cadastrar());
				break;
				
			case 2:
				//consultar
				loja.consultar(produto);
				break;
				
			case 3:
				//editar
				loja.editar(produto);
				break;
				
			case 4:
				//remover
				loja.remover(produto);
				break;
				
			case 5:
				//realizar vendas
				loja.carrinho(produto, historico);
				break;
				
			case 6:
				//consulta historico
				loja.historico(historico);
				break;
				
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opcao invalida!!!");
				break;
			}
			
		}while(!sair);
		
		System.out.println("Sistema encerrado!!!");

	}

}
