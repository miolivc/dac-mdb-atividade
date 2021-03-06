
package edu.ifpb.dac.service;

import edu.ifpb.dac.entidade.Cliente;
import edu.ifpb.dac.entidade.ItemPedido;
import edu.ifpb.dac.entidade.Pedido;
import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.infra.PedidoDao;
import java.math.BigDecimal;
import java.util.List;
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
    
    public void adicionarCliente(Cliente cliente) {
        pedido.setCliente(cliente);
    }

    public List<ItemPedido> verItensCarrinho() {
        return pedido.getProdutos();
    }
    
    public String finalizarCarrinho() {
        pedidoDao.adicionar(pedido);
        processaPedido.enviarParaProcessamento(pedido);
        this.pedido = new Pedido();
        return null;
    }
}
