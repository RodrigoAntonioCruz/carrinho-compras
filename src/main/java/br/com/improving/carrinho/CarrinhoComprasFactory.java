package br.com.improving.carrinho;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Collection;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {

	private Collection<CarrinhoCompras> carrinhosCompras = new ArrayList<>();

	public CarrinhoComprasFactory() {
	}

	/**
	 * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
	 *
	 * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho
	 * deverá ser retornado.
	 *
	 * @param identificacaoCliente
	 * @return CarrinhoCompras
	 */
	public CarrinhoCompras criar(String identificacaoCliente) {

		Optional<CarrinhoCompras> carrinhoExiste =
				carrinhosCompras.stream().filter(carrinhoCompras -> carrinhoCompras
								.getIdentificacaoCliente().equals(identificacaoCliente))
						        .findFirst();

		if (carrinhoExiste.isPresent()) {
			return carrinhoExiste.get();
		} else {
			CarrinhoCompras novoCarrinho = new CarrinhoCompras(identificacaoCliente);
			carrinhosCompras.add(novoCarrinho);
			return novoCarrinho;
		}
	}

	/**
	 * Retorna o valor do ticket médio no momento da chamada ao método. O valor do ticket médio é a
	 * soma do valor total de todos os carrinhos de compra dividido pela quantidade de carrinhos de
	 * compra. O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
	 * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
	 *
	 * @return BigDecimal
	 */
    public BigDecimal getValorTicketMedio() {
        return BigDecimal.valueOf(carrinhosCompras.stream()
                         .mapToDouble(carrinhosCompras -> carrinhosCompras.getValorTotal().doubleValue()).sum())
                         .divide(BigDecimal.valueOf(carrinhosCompras.stream().count()))
                         .setScale(2, RoundingMode.HALF_EVEN);
    }

	/**
	 * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar. Deve
	 * ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos
	 * de compras.
	 *
	 * @param identificacaoCliente
	 * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um
	 *         carrinho de compras e e false caso o cliente não possua um carrinho.
	 */
    public boolean invalidar(String identificacaoCliente) {
        return getCarrinhosCompras().removeIf(carrinhoCompras -> carrinhoCompras.getIdentificacaoCliente().equals(identificacaoCliente));
    }

    public Collection<CarrinhoCompras> getCarrinhosCompras() {
        return carrinhosCompras;
    }
}
