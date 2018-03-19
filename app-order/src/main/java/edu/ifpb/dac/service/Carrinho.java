
package edu.ifpb.dac.service;

import edu.ifpb.dac.entidade.Pedido;
import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.infra.PedidoDao;
import java.math.BigDecimal;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 * @author alexalins
 */
@Stateful
public class Carrinho {
    
    @Inject
    private ProcessaPedido processaPedido;
    @Inject
    private PedidoDao pedidoDao;
    private Pedido pedido = new Pedido();
    
    public BigDecimal valorCarrinho() {
        return pedido.valorPedido();
    }
    
    public void adicionarProduto(Produto produto) {
        pedido.add(produto);
    }
    
    public void removerProduto(Produto produto) {
        pedido.remove(produto);
    }
    
    public Pedido verCarrinho() {
        return pedido;
    }
    
    public String finalizarCarrinho() {
        pedidoDao.adicionar(pedido);
        processaPedido.enviarParaProcessamento(pedido);
        return null;
    }
}
