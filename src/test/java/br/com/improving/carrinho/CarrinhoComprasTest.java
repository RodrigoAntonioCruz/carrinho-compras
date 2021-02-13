package br.com.improving.carrinho;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CarrinhoComprasTest {

    private CarrinhoCompras carrinhoCompras;
    private final BigDecimal valor = BigDecimal.valueOf(6000);
    private final BigDecimal valor2 = BigDecimal.valueOf(2000);
    private final String idCliente = "Cliente1";

    @BeforeEach
    void setUp() {
        carrinhoCompras = new CarrinhoCompras(idCliente);
    }

    @Test
    void adicionartesteItem() {

        Produto produto = new Produto(1L, "Produto1");
        carrinhoCompras.adicionarItem(produto, valor, 1);
        carrinhoCompras.adicionarItem(produto, valor2, 3);

        assertEquals(carrinhoCompras.getItens().size(), 1);
        assertEquals(carrinhoCompras.getIdentificacaoCliente(),idCliente);
        assertEquals(carrinhoCompras.getValorTotal(),BigDecimal.valueOf(8000.0));

    }

    @Test
    void removerItem() {

        Produto produto = new Produto(1L, "Produto1");
        carrinhoCompras.adicionarItem(produto, valor, 1);
        assertEquals(carrinhoCompras.getItens().size(), 1);
        carrinhoCompras.removerItem(produto);
        assertEquals(carrinhoCompras.getItens().size(),0);

    }

    @Test
    void removerItemPosition() {

        Produto produto = new Produto(1L, "Produto1");
        carrinhoCompras.adicionarItem(produto, valor, 1);
        assertEquals(carrinhoCompras.getItens().size(), 1);
        carrinhoCompras.removerItem(0);
        assertEquals(carrinhoCompras.getItens().size(),0);

    }


}