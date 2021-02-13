package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	
	private static String identificacaoCliente = "Cliente Anônimo";
	private static CarrinhoCompras carrinhoCompras = new CarrinhoCompras(identificacaoCliente);
	private static CarrinhoComprasFactory carrinhoComprasFactory = new CarrinhoComprasFactory();
	
	private final static BigDecimal valor_cafe = BigDecimal.valueOf(6.00);
	private final static BigDecimal valor_leite = BigDecimal.valueOf(3.00);
	private final static BigDecimal valor_pao = BigDecimal.valueOf(0.50);
	private final static BigDecimal valor_presunto = BigDecimal.valueOf(5.50);
	
	private final static Produto cafe = new Produto(1L, "Café"); 
	private final static Produto leite = new Produto(2L, "Leite");
	private final static Produto presunto = new Produto(3L, "Presunto"); 
	private final static Produto pao = new Produto(4L, "Pão"); 
	private final static NumberFormat moeda = NumberFormat.getCurrencyInstance();
	
    public void menuView() {
		System.out.println();
		System.out.println("+-------------------------------------------+");
		System.out.println("|              Menu de Opções               |");
		System.out.println("+-------------------------------------------+");
		System.out.println("| 01 - Adicionar itens ao carrinho          |");
		System.out.println("| 02 - Remover itens do carrinho            |");
		System.out.println("| 03 - Sair                                 |");
		System.out.println("+-------------------------------------------+");
		System.out.println("Selecione uma opção do menu: ");
		System.out.println();   
    }
	
    public void listaProdutosView() {
		System.out.println();
		System.out.println("+-------------------------------------------+");
		System.out.println("|              Lista de Produtos            |");
		System.out.println("+-------------------------------------------+");
		System.out.println("| 10 - Café ----------------------- " + moeda.format(valor_cafe) + " |");
		System.out.println("| 20 - Leite ---------------------- " + moeda.format(valor_leite) + " |");
		System.out.println("| 30 - Presunto ------------------- " + moeda.format(valor_presunto) + " |");
		System.out.println("| 40 - Pão ------------------------ " + moeda.format(valor_pao) + " |");
		System.out.println("| 91 - Fazer checkout                       |");
		System.out.println("| 95 - Voltar ao Menu                       |");
		System.out.println("+-------------------------------------------+");
    }
    
    public void checkOutView() {
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|              CheckOut realizado com sucesso - volte sempre            |");
		System.out.println("+-----------------------------------------------------------------------+");
    }
    
    public void zerarCarrinho() {
		carrinhoCompras.removerTodosItensCarrinho();
		carrinhoComprasFactory.invalidar(identificacaoCliente);
    }
    
    public void removeItensCarrinho() {

 		System.out.println("+-----------------------------------------------------------------------+");
 		System.out.println("|                      Remover Itens do Carrinho                        |");
 		System.out.println("+-----------------------------------------------------------------------+");
 		System.out.println("Digite o código do produto:");

		Scanner r = new Scanner(System.in);
		short op = 5;
		
		do{	
			op = r.nextShort();
			switch(op){
			case 10:
				carrinhoCompras.removerItem(cafe);
				removeItensCarrinho();
				break;
			case 20:
				carrinhoCompras.removerItem(leite);
				removeItensCarrinho();
				break;
			case 30:
				carrinhoCompras.removerItem(presunto);
				removeItensCarrinho();
				break;
			case 40:
				carrinhoCompras.removerItem(pao);
				removeItensCarrinho();
			    break;
			default:
				totalItensCarrinho();
		    }
		}while(op != 5);
 		r.close();
     }
    
	public void totalItensCarrinho() {
		List<String> itens = carrinhoCompras.getItens().stream().map(item -> item.getProduto().getDescricao()).collect(Collectors.toList());
		BigDecimal total = carrinhoCompras.getValorTotal();
		
		listaProdutosView();
		System.out.println("+-------------------------------------------+");
		System.out.println("|              Itens do carrinho            |");
		System.out.println("+-------------------------------------------+");
		System.out.println("Itens:                                      |");
		System.out.println(itens                                          );
		System.out.println("|                                           |");
		System.out.println("|Total:                                     |");
		System.out.println("+-------------------------------------------+");
		System.out.println(moeda.format(total)                            );
		System.out.println("+-------------------------------------------+");
		System.out.println("Selecione um produto na lista: ");
		System.out.println();
	}
	
	public void adicionaItens() {
		carrinhoComprasFactory.criar(identificacaoCliente);
		listaProdutosView();
		System.out.println("Selecione um produto na lista: ");
		System.out.println();
		Scanner l = new Scanner(System.in);
		short op = 5;
		
		do{	
			op = l.nextShort();
				switch(op){
					case 10:
						carrinhoCompras.adicionarItem(cafe, valor_cafe, 1);
						totalItensCarrinho();
						break;
					case 20:
						carrinhoCompras.adicionarItem(leite, valor_leite, 1);
						totalItensCarrinho();
						break;
					case 30:
						carrinhoCompras.adicionarItem(presunto, valor_presunto, 1);
						totalItensCarrinho();
						break;
					case 40:
						carrinhoCompras.adicionarItem(pao, valor_pao, 1);
						totalItensCarrinho();
						break;
					case 45:
						totalItensCarrinho();
						break;
					case 55:
						removeItensCarrinho();
						break;
					case 91:
						checkOutView();
						zerarCarrinho();
						//System.out.println(carrinhoComprasFactory.getCarrinhosCompras().size());
						//System.out.println(carrinhoCompras.getItens().size());
						
						break;
					case 5:
						menuView();
						break;
					default:
						menuView();
			    }
			}while(op != 5);
	   l.close();
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		short opcao = 5;
		Scanner leitor = new Scanner(System.in);
		Application app = new Application();
	
		
		app.menuView();
		
		do{		
			opcao = leitor.nextShort();

			switch(opcao){
			
				case 1:
					app.adicionaItens();
					break;
				case 2:
					app.removeItensCarrinho();
					break;
				case 3:
					app.zerarCarrinho();
					break;
				case 5:
					app.menuView();
					break;
				default:
					app.menuView();
					
			}
		}while(opcao != 99);
		
		leitor.close();
	
	}
}
