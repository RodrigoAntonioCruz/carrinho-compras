package br.com.improving.carrinho;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CarrinhoComprasFactoryTest {

    private CarrinhoComprasFactory carrinhoComprasFactory;
    private final String idCliente = "Cliente1";
    private final String idCliente2 = "Cliente2";
    private final BigDecimal valor = BigDecimal.valueOf(6000.335);
    private final BigDecimal valor2 = BigDecimal.valueOf(2000.636);


    @BeforeEach
    void setUp() {
        carrinhoComprasFactory = new CarrinhoComprasFactory();
    }


    @Test
    void criar() {

        carrinhoComprasFactory.criar(idCliente);
        assertEquals(carrinhoComprasFactory.getCarrinhosCompras().stream().filter(carrinhoCompras -> carrinhoCompras.getIdentificacaoCliente().equals(idCliente)).findFirst().get().getIdentificacaoCliente(), idCliente);

    }

    @Test
    void getValorTicketMedio() {

        carrinhoComprasFactory.criar(idCliente);

        Produto produto = new Produto(1L, "Produto1");
        carrinhoComprasFactory.getCarrinhosCompras()
                .stream().filter(carrinhoCompras -> carrinhoCompras.getIdentificacaoCliente().equals(idCliente))
                .findFirst()
                .get()
                .adicionarItem(produto, valor, 1);

        carrinhoComprasFactory.criar(idCliente2);

        Produto produto2 = new Produto(2L, "Produto2");
        carrinhoComprasFactory.getCarrinhosCompras()
                .stream()
                .filter(carrinhoCompras -> carrinhoCompras.getIdentificacaoCliente().equals(idCliente2))
                .findFirst()
                .get()
                .adicionarItem(produto2, valor2, 1);

        assertEquals(carrinhoComprasFactory.getValorTicketMedio(), BigDecimal.valueOf(4000.49));

    }

    @Test
    void invalidar() {

        carrinhoComprasFactory.criar(idCliente);
        assertEquals(carrinhoComprasFactory.getCarrinhosCompras()
                .stream()
                .filter(carrinhoCompras -> carrinhoCompras.getIdentificacaoCliente().equals(idCliente))
                .findFirst()
                .get()
                .getIdentificacaoCliente(), idCliente);

        carrinhoComprasFactory.invalidar(idCliente);
        assertEquals(carrinhoComprasFactory.getCarrinhosCompras().size(), 0);

    }
}